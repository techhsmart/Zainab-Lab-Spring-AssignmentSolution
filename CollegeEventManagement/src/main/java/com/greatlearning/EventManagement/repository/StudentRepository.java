package com.greatlearning.EventManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.EventManagement.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
