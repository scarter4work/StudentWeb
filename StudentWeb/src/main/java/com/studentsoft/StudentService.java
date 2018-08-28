package com.studentsoft;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service for student operations.
 * @author Scott Carter
 */
@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;

	/** default data */
	public StudentService() {
	}
	
	/**
	 * Gets all students.
	 * @return List<Student>
	 */
	public List<Student> getAllStudents() {
		return this.studentRepo.findAll();
	}
	
	/**
	 * Gets a student.
	 * @param studentId
	 * @return Student
	 * @throws Exception
	 */
	public Student getStudent(String studentId) {
		return this.studentRepo.findByStudentId(studentId);
	}
	
	/**
	 * Saves a student.
	 * @param student - Student
	 * @return Student (with populated studentId and id)
	 * @throws Exception
	 */
	public Student saveStudent(Student student) {
		// add the student id
		student.setStudentId(UUID.randomUUID().toString());
		return this.studentRepo.save(student);
	}
	
	/**
	 * Updates a student.
	 * @param student - Student
	 * @return Student (with updated information saved)
	 * @throws Exception
	 */
	public Student updateStudent(Student student) {
		Student oldStudent = this.studentRepo.findByStudentId(student.getStudentId());
		oldStudent.setFirstName(student.getFirstName());
		oldStudent.setLastName(student.getLastName());
		oldStudent.setGpa(student.getGpa());
		oldStudent.setGraduationYear(student.getGraduationYear());
		return this.studentRepo.save(oldStudent);
	}
	
	/**
	 * Deletes a student.
	 * @param studentId String
	 */
	public void deleteStudent(String studentId) {
		Student student = this.studentRepo.findByStudentId(studentId);
		this.studentRepo.delete(student);
	}
}