package org.comps.controller;

import org.comps.errors.AppExceptions;
import org.comps.model.Group;
import org.comps.model.GroupStudent;
import org.comps.model.User;
import org.comps.model.UserType;
import org.comps.service.GroupService;
import org.comps.service.GroupStudentService;
import org.comps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class GroupStudentsController {
    private static Logger logger = LoggerFactory.getLogger(GroupStudentsController.class);
    @Autowired private GroupStudentService groupStudentService;
    @Autowired private GroupService groupService;
    @Autowired private UserService userService;

    @PostMapping(value="/group-students")
    public Map<String, String> processUpload(@RequestBody List<GroupStudent> groupStudents) {
        int i = 0;
        StringBuilder errorMsg = new StringBuilder();
        for(GroupStudent groupStudent : groupStudents) {
            i++;
            try {
                User student = userService.findById(groupStudent.getStudentId());
                if(student == null || student.getType() != UserType.STUDENT) {
                    throw AppExceptions.resourceNotFound(String.format("Student %s does not exist", groupStudent.getStudentId()));
                }
                groupStudent.setId(UUID.randomUUID().toString());
                groupStudent.setNew(true);

                Group group = groupService.findById(groupStudent.getGroupId());
                if(group == null) {
                    throw AppExceptions.resourceNotFound(String.format("Group with id %s does not exist", groupStudent.getGroupId()));
                }

                groupStudentService.save(groupStudent);
            } catch (Exception ex) {
                errorMsg.append("Correct line no: ").append(i).append(", error: ").append(ex.getMessage()).append("\n");
                logger.error("Unable to save student : {}, group : {}", groupStudent.getStudentId(), groupStudent.getGroupId(), ex);
            }
        }

        if(errorMsg.length() > 0) {
            return Map.of("error", errorMsg.toString(), "msg", "Some of the records cannot be saved, please check error messages");
        } else {
            return Map.of("msg", "Successfully uploaded users in the file");
        }
    }

    @DeleteMapping(value = "/group-students")
    public void deleteGroupStudent(@RequestBody List<GroupStudent> groupStudents) {
        for(GroupStudent groupStudent : groupStudents) {
            GroupStudent persistedGroupStudent = groupStudentService.findGroupStudent(groupStudent.getGroupId(), groupStudent.getStudentId());
            if (persistedGroupStudent == null) {
                throw AppExceptions.resourceNotFound(String.format("No Resource with groupId: %s and studentId: %s", groupStudent.getGroupId(), groupStudent.getStudentId()));
            }
            groupStudentService.delete(persistedGroupStudent);
        }
    }

    @GetMapping(value = "/group-students", params = {"classId", "assignmentId"})
    public List<User> findUsersNotInAnyGroup(@RequestParam String classId, @RequestParam String assignmentId) {
        return groupStudentService.findUsersNotInAnyGroup(classId, assignmentId);
    }

    @GetMapping(value = "/group-students", params = {"assignmentId", "studentId"})
    public List<Group> findGroupsByAssignmentIdAndStudentId(@RequestParam String assignmentId, @RequestParam String studentId) {
        return groupStudentService.findGroupsByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    @GetMapping(value = "/group-students", params = {"groupId"})
    public List<User> findUsersInGroup(@RequestParam String groupId) {
        return groupStudentService.findUsersInGroup(groupId);
    }
}
