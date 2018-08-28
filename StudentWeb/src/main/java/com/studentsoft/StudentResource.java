package com.studentsoft;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Student resource in jax-rs format.  Facade pattern for student service
 * to talk to angular front end via AJAX.
 * @author Scott Carter
 */
@RestController
@RequestMapping("/api/student")
public class StudentResource {
	/** logger instance */
	private static final Logger logger = LoggerFactory.getLogger(StudentResource.class);
	
	/** student service instance */
	@Autowired
	private StudentService studentService;
	
	/** default constructor */
	public StudentResource() {
	}
	
	/**
	 * Gets all students.
	 * @return List<Student>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Student> getAllStudents() {
		logger.debug("getAllStudents() entering.");
		List<Student> retVal = this.studentService.getAllStudents();
		logger.debug("getAllStudents() student list size = " + retVal.size());
		logger.debug("getAllStudents() exiting.");
		return retVal;
	}
	
	/**
	 * Get a student by student id.
	 * @param studentId - String UUID of the student id.
	 * @return Student
	 */
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 public Student readStudent(@PathVariable("id") String studentId) {
		logger.debug("readStudent() entering.");
		Student student = this.studentService.getStudent(studentId);
		logger.debug("readStudent() student = " + student.toString());
		logger.debug("readStudent() exiting.");
		return student;
	}
	
	/** 
	 * Updates a student by the student data passed in.
	 * @param studentId - String
	 * @param firstName - String
	 * @param lastName - String
	 * @param graduationYear - String
	 * @param gpa - String
	 * @return Student - updated student
	 */
	 @RequestMapping(value = "/{studentId}/{firstName}/{lastName}/{graduationYear}/{gpa}", method = RequestMethod.PUT)
	 public Student updateStudent(@PathVariable("studentId") String studentId, @PathVariable("firstName") String firstName, 
			 					  @PathVariable("lastName") String lastName, @PathVariable("graduationYear") String graduationYear, 
			 					  @PathVariable("gpa") String gpa) {
		logger.debug("updateStudent() entering.");
		Student oldStudent = this.studentService.getStudent(studentId);
		oldStudent.setFirstName(firstName);
		oldStudent.setLastName(lastName);
		oldStudent.setGraduationYear(graduationYear);
		oldStudent.setGpa(gpa);
		Student retVal = this.studentService.updateStudent(oldStudent);
		logger.debug("updateStudent() student = " + retVal.toString());
		logger.debug("updateStudent() exiting.");
		return retVal;
	}
	
	/**
	 * Creates a new student with the student data passed in.
	 * @param firstName - String
	 * @param lastName - String
	 * @param graduationYear - String
	 * @param gpa - String
	 * @return Student - created student (with student id)
	 */
	@RequestMapping(value="/{firstName}/{lastName}/{graduationYear}/{gpa}", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
								 @PathVariable("graduationYear") String graduationYear, @PathVariable("gpa") String gpa) {
		logger.debug("createStudent() entering.");
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setGraduationYear(graduationYear);
		student.setGpa(gpa);
		Student retVal = this.studentService.saveStudent(student);
		logger.debug("createStudent() student = " + student.toString());
		logger.debug("createStudent() exiting.");
		return retVal;
	}
	
	/**
	 * Deletes a student using the student data passed in.
	 * @param student - Student to delete
	 */
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public List<Student> deleteStudent(@PathVariable("id") String studentId) {
		logger.debug("deleteStudent() entering.");
		this.studentService.deleteStudent(studentId);
		logger.debug("deleteStudent() student: " + studentId + " has been deleted.");
		List<Student> retVal = this.studentService.getAllStudents();
		logger.debug("deleteStudent() exiting.");
		return retVal;
	  }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleStudentException(Exception ex) {
    	logger.error("Handling error with message: {}", ex.getMessage());
    }
}