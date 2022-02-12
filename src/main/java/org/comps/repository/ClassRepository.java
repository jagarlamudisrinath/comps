package org.comps.repository;

import org.comps.model.Class;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, String> {

    @Query("SELECT * FROM classes WHERE ga_id = :gaId")
    List<Class> findAllByGaId(String gaId);

    @Query("SELECT * FROM classes WHERE prof_id = :profId")
    List<Class> findAllByProfId(String profId);

    List<Class> findAll();
}
