package org.comps.service;

import org.comps.model.GroupStudent;
import org.comps.repository.GroupStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GroupStudentService {
    @Autowired private GroupStudentRepository groupStudentRepository;

    public void save(GroupStudent groupStudent) {
        groupStudentRepository.save(groupStudent);
    }

}
