package com.personal.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.springboot.web.model.Employee;

@Service
public class EmployeeService {
	private static List<Employee> emps = new ArrayList<Employee>();
	private static int empCount = 2;

	static {
		emps.add(new Employee(1, "iftekhar", new Date(), "M", "Programmer"));
		emps.add(new Employee(2, "mahmud", new Date(), "M", "Developer")); 
	}

	public List<Employee> retrieveEmp() {
		List<Employee> filteredEmp = new ArrayList<Employee>();
		for (Employee e : emps) {
			filteredEmp.add(e);
		}
		return filteredEmp;
	}

	public void addEmployee(String name, Date dob, String gender, String note) {
		emps.add(new Employee(++empCount, name, dob, gender, note));
	}

	public void updateEmployee(Employee emp) {
		emps.remove(emp);
		emps.add(emp);
	}
	
	public void deleteEmployee(Employee emp) {
		emps.remove(emp);
	}

	public Employee retrieveEmployee(int id) {
		for (Employee e : emps) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}

}