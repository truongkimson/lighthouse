package com.nus.lighthouse.repo;

import com.nus.lighthouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//If we intend to do the login methods, we might want to do it here
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	@Query(value="Select u from User u where u.email=:email")
    User findUserByEmail(@Param("email")String email);
}
