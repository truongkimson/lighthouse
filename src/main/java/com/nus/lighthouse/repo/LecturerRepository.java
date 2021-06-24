package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {


}
