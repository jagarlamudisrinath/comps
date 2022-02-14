package org.comps.controller;

import org.comps.errors.AppExceptions;
import org.comps.model.Assignment;
import org.comps.service.AssignmentService;
import org.comps.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
public class AssignmentController {
    @Autowired private ClassService classService;
    @Autowired private AssignmentService assignmentService;

    @PostMapping("/assignments")
    public Assignment createAssignment(@RequestPart Assignment assignment, @RequestParam(required = false) MultipartFile file) {
        boolean assignmentExists = classService.existsById(assignment.getClassId());

        if(!assignmentExists) {
            throw AppExceptions.resourceNotFound(String.format("No Class found with Id: %s", assignment.getClassId()));
        }

        if(StringUtils.hasText(assignment.getId())) {
            Assignment persistedAssignment = assignmentService.findById(assignment.getId());
            if(persistedAssignment == null) {
                assignment.setNew(true);
            } else {
                if(!persistedAssignment.getClassId().equals(assignment.getClassId())) {
                    throw AppExceptions.badRequest("Class Id cannot be updated");
                }

            }
        } else {
            assignment.setNew(true);
            assignment.setId(UUID.randomUUID().toString());
        }

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assignment.setCreatedBy(userDetails.getUsername());

        assignmentService.save(assignment, file);
        return assignment;
    }

    @GetMapping("/assignments/{assignmentId}/file")
    @ResponseBody
    public ResponseEntity<Resource> assignmentFile(@PathVariable String assignmentId) {
        Resource file = assignmentService.getAssignmentFile(assignmentId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping(value = "/assignments", params = "classId")
    public List<Assignment> findAssignmentByClassId(@RequestParam String classId) {
        return assignmentService.findAllByClassId(classId);
    }
}
