package org.comps.repository;

import org.comps.model.Group;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, String> {

    @Query("SELECT * FROM groups WHERE assignment_id = :assignmentId")
    List<Group> findAllByAssignmentId(String assignmentId);

    @Query("SELECT * FROM groups WHERE assignment_id = :assignmentId and group_id = :groupId")
    List<Group> findAllByAssignmentIdAndGroupid(String assignmentId, String groupId);
}
