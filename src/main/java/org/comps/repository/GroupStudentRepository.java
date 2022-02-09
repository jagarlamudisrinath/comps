package org.comps.repository;

import org.comps.model.GroupStudent;
import org.comps.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupStudentRepository extends CrudRepository<GroupStudent, String> {

    @Query("SELECT * FROM users WHERE id in (select student_id from group_students where group_id = :groupId)")
    List<User> findUsersByGroupId(String groupId);

    @Query("SELECT * FROM users WHERE id in (select student_id from class_students where class_id = :classId)" +
            " and id not in (select student_id from group_students where group_id in (select id from groups where assignment_id = :assignmentId))")
    List<User> findUsersNotInAnyGroup(String classId, String assignmentId);

}
