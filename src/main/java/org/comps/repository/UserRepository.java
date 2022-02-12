package org.comps.repository;

import org.comps.model.User;
import org.comps.model.UserType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("SELECT * FROM users where id in (select student_id FROM class_students WHERE class_id = :classId)")
    List<User> findUsersByClassId(String classId);

    @Query("SELECT * FROM users where type = :userType")
    List<User> findUsersByType(UserType userType);
}
