package org.comps.service;

import org.comps.model.Group;
import org.comps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GroupService {
    @Autowired private GroupRepository groupRepository;

    public void save(Group group) {
        groupRepository.save(group);
    }

    public List<Group> findAllByAssignmentId(String assignmentId) {
        return groupRepository.findAllByAssignmentId(assignmentId);
    }

    public List<Group> findAllByAssignmentIdAndGroupId(String assignmentId, String groupId) {
        return groupRepository.findAllByAssignmentIdAndGroupid(assignmentId, groupId);
    }

    public Group findById(String id) {
        return groupRepository.findById(id).orElse(null);
    }
}
