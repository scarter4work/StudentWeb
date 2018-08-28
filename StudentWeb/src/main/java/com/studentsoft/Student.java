package com.studentsoft;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student table entity.
 * @author Scott Carter
 */
@Entity
@Table(name="STUDENT")
public class Student implements Serializable {
	/**	serialization id */
	private static final long serialVersionUID = 8587600003800584345L;

	/** id */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/** first name */
	@Column(name="first_name")
	private String firstName;
	
	/** last name */
	@Column(name="last_name")
	private String lastName;
	
	/** student id */
	@Column(name="student_id")
	private String studentId;
	
	/** gpa */
	@Column(name="gpa")
	private String gpa;
	
	/** graduation year */
	@Column(name="graduation_year")
	private String graduationYear;
	
	/** default constructor */
	public Student() {
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", studentId=" + studentId + ", gpa=" + gpa
				+ ", graduationYear=" + graduationYear + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gpa == null) ? 0 : gpa.hashCode());
		result = prime * result
				+ ((graduationYear == null) ? 0 : graduationYear.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gpa == null) {
			if (other.gpa != null)
				return false;
		} else if (!gpa.equals(other.gpa))
			return false;
		if (graduationYear == null) {
			if (other.graduationYear != null)
				return false;
		} else if (!graduationYear.equals(other.graduationYear))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the gpa
	 */
	public String getGpa() {
		return gpa;
	}

	/**
	 * @param gpa the gpa to set
	 */
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	/**
	 * @return the graduationYear
	 */
	public String getGraduationYear() {
		return graduationYear;
	}

	/**
	 * @param graduationYear the graduationYear to set
	 */
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
}