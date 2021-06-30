package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    @Query("SELECT l FROM Lecturer l WHERE l.firstName LIKE CONCAT('%',:query,'%')" +
            "OR l.lastName LIKE CONCAT('%',:query,'%')" +
            "OR l.email LIKE CONCAT('%',:query,'%')")
    Collection<Lecturer> findLecturersByQuery(@Param("query") String query);
    
    
}
