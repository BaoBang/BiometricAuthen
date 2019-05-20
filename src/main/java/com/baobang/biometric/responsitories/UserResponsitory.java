package com.baobang.biometric.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.biometric.entities.User;


@Repository
public interface UserResponsitory extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.username = :username")
	public User findByUsername(@Param("username") String username);
	
	@Query("select u from User u where u.username = :username and u.password = :password")
	public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	

}
