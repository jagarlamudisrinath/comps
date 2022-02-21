package org.comps.service;

import org.comps.model.Class;
import org.comps.model.ClassStudent;
import org.comps.repository.ClassStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClassStudentService {
    @Autowired private ClassStudentRepository classStudentRepository;

    public void save(ClassStudent classStudent) {
        classStudentRepository.save(classStudent);
    }

    public ClassStudent findByClassIdAndStudentId(String classId, String studentId) {
        List<ClassStudent> allByClassIdAndStudentId = classStudentRepository.findAllByClassIdAndStudentId(classId, studentId);
        return allByClassIdAndStudentId.isEmpty() ? null : allByClassIdAndStudentId.get(0);
    }

    public List<Class> findClassesByStudentId(String studentId) {
        return classStudentRepository.findClassesByStudentId(studentId);
    }

    public void delete(String classId, String studentId) {
        ClassStudent classStudent = findByClassIdAndStudentId(classId, studentId);
        if(classStudent != null) {
            classStudentRepository.delete(classStudent);
        }
    }
}
