package org.comps.repository;

import org.comps.model.Class;
import org.comps.model.ClassStudent;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassStudentRepository extends CrudRepository<ClassStudent, String> {

    @Query("SELECT * FROM class_students WHERE class_id = :classId and student_id = :studentId")
    List<ClassStudent> findAllByClassIdAndStudentId(String classId, String studentId);

    @Query("select * from classes where id in (SELECT class_id FROM class_students WHERE student_id = :studentId)")
    List<Class> findClassesByStudentId(String studentId);
}
