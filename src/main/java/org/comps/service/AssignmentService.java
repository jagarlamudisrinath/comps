package org.comps.service;

import org.comps.model.Assignment;
import org.comps.model.User;
import org.comps.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AssignmentService {
    @Autowired private AssignmentRepository assignmentRepository;

    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    public List<Assignment> findAllByClassId(String classId) {
        return assignmentRepository.findAllByClassId(classId);
    }

    public Assignment findById(String id) {
        return assignmentRepository.findById(id).orElse(null);
    }
}
