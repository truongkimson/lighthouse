package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.Admin;
import com.nus.lighthouse.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {


}


