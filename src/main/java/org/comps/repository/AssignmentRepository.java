package org.comps.repository;

import org.comps.model.Assignment;
import org.comps.model.Class;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, String> {

    @Query("SELECT * FROM assignments WHERE class_id = :classId")
    List<Assignment> findAllByClassId(String classId);

}
