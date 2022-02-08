package org.comps.service;

import org.comps.model.Class;
import org.comps.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClassService {
    @Autowired private ClassRepository classRepository;

    public void save(Class class1) {
        classRepository.save(class1);
    }

    public boolean existsById(String id) {
        return classRepository.existsById(id);
    }

    public List<Class> findAllByGaId(String gaId) {
        return classRepository.findAllByGaId(gaId);
    }

    public List<Class> findAllByProfId(String profId) {
        return classRepository.findAllByProfId(profId);
    }
}
