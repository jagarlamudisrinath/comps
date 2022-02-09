package org.comps.repository;

import org.comps.model.ClassStudent;
import org.comps.model.GroupStudent;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupStudentRepository extends CrudRepository<GroupStudent, String> {

    @Query("SELECT * FROM class_students WHERE class_id = :classId and student_id = :studentId")
    List<ClassStudent> findAllByClassIdAndStudentId(String classId, String studentId);

}
