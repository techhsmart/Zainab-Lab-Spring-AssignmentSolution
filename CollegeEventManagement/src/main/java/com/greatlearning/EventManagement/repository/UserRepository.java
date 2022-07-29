package com.greatlearning.EventManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.EventManagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	
}
