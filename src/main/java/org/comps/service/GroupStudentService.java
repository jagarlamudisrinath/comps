package org.comps.service;

import org.comps.model.GroupStudent;
import org.comps.model.User;
import org.comps.repository.GroupStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GroupStudentService {
    @Autowired private GroupStudentRepository groupStudentRepository;

    public void save(GroupStudent groupStudent) {
        groupStudentRepository.save(groupStudent);
    }

    public List<User> findUsersNotInAnyGroup(String classId, String assignmentId) {
        return groupStudentRepository.findUsersNotInAnyGroup(classId, assignmentId);
    }

    public List<User> findUsersInGroup(String groupId) {
        return groupStudentRepository.findUsersByGroupId(groupId);
    }
}
