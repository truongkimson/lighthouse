package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//If we intend to do the login methods, we might want to do it here
public interface UserRepository extends JpaRepository<User,Integer>{

}
