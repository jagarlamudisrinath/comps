package org.comps.service;

import org.comps.errors.AppExceptions;
import org.comps.model.Assignment;
import org.comps.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AssignmentService {
    @Autowired private AssignmentRepository assignmentRepository;
    @Autowired private StorageService storageService;

    public void save(Assignment assignment, MultipartFile file) {
        if(file != null) {
            String fileName = assignment.getId() + "-" +file.getOriginalFilename();
            assignment.setFile(fileName);
            storageService.store(file, fileName);
        }
        assignmentRepository.save(assignment);
    }

    public Resource getAssignmentFile(String assignmentId) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);
        Assignment assignment = assignmentOptional.orElse(null);
        if(assignment == null) {
            throw AppExceptions.resourceNotFound(String.format("Assignment: %s not found", assignmentId));
        }
        if(!StringUtils.hasText(assignment.getFile())) {
            throw AppExceptions.resourceNotFound(String.format("Assignment: %s does not have a file uploaded", assignmentId));
        }
        return storageService.loadAsResource(assignment.getFile());
    }

    public List<Assignment> findAllByClassId(String classId) {
        return assignmentRepository.findAllByClassId(classId);
    }

    public Assignment findById(String id) {
        return assignmentRepository.findById(id).orElse(null);
    }
}
