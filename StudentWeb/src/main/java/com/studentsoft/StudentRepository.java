package com.studentsoft;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByLastName(@Param("lastName") String lastName);
	Student findByStudentId(@Param("studentId") String studentId);
}