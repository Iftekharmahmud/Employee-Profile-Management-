package com.personal.springboot.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee {

	private int id;
	private String empName;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	
	private String gender;
	private String note;

	public Employee() {
		super();
	}

	public Employee(int id, String empName, Date dob, String gender, String note) {
		super();
		this.id = id;
		this.empName = empName;
		this.dob = dob;
		this.gender = gender;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", dob=" + dob + ", gender=" + gender + ", note=" + note
				+ "]";
	}

}
