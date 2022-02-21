package org.comps.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.comps.errors.AppExceptions;
import org.comps.model.Class;
import org.comps.model.ClassStudent;
import org.comps.model.User;
import org.comps.service.ClassService;
import org.comps.service.ClassStudentService;
import org.comps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ClassStudentsController {
    @Autowired private ClassStudentService classStudentService;
    @Autowired private ClassService classService;
    @Autowired private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(ClassStudentsController.class);

    @PostMapping(value="/class-students/{classId}")
    public Map<String, String> processUpload(@PathVariable String classId, @RequestParam MultipartFile file) throws IOException {
        logger.info("Received file [{}] for upload class students", file.getName());
        boolean classExists = classService.existsById(classId);
        if(!classExists) {
            throw AppExceptions.resourceNotFound(String.format("Class: %s not found", classId));
        }

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = CSVFormat.DEFAULT.builder().setCommentMarker('#').setHeader("student_id").build().parse(fileReader);
        List<CSVRecord> records = csvParser.getRecords();
        StringBuilder errorMsg = new StringBuilder();
        int i = 0;
        for(CSVRecord record : records) {
            try {
                i++;
                String studentId = record.get("student_id");
                boolean studentExists = userService.existsById(studentId);
                if(!studentExists) {
                    throw AppExceptions.resourceNotFound(String.format("Student: %s not found", studentId));
                }
                ClassStudent classStudent = classStudentService.findByClassIdAndStudentId(classId, studentId);
                if(classStudent != null) {
                    logger.warn(String.format("Student: %s exists in Class: %s", studentId, classId));
                    continue;
                }
                classStudent = new ClassStudent();
                classStudent.setId(UUID.randomUUID().toString());
                classStudent.setClassId(classId);
                classStudent.setStudentId(studentId);
                classStudent.setNew(true);
                classStudentService.save(classStudent);
            } catch (Exception e) {
                errorMsg.append("Correct line no: ").append(i).append(", error: ").append(e.getMessage()).append("\n");
                logger.error("Unable to process line no: {} from file : {}", i, file.getName(), e);
            }
        }
        if(errorMsg.length() > 0) {
            return Map.of("error", errorMsg.toString(), "msg", "Please correct records in the error message and reupload");
        } else {
            return Map.of("msg", "Successfully uploaded users in the file");
        }
    }

    @GetMapping(value = "/class-students", params = {"classId"})
    public List<User> findUsersByClassId(@RequestParam String classId) {
        return userService.findUsersByClassId(classId);
    }

    @GetMapping(value = "/classes", params = {"studentId"})
    public List<Class> findClassesByStudentId(@RequestParam String studentId) {
        return classStudentService.findClassesByStudentId(studentId);
    }

    @DeleteMapping(value = "/class-students")
    public void findUsersByClassId(@RequestParam String classId, @RequestParam String studentId) {
        classStudentService.delete(classId, studentId);
    }
}
