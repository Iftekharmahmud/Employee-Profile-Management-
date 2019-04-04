package com.personal.springboot.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.springboot.web.model.Employee;
import com.personal.springboot.web.service.EmployeeService;




@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService service;
	
	
	@GetMapping("/employee")
	public List<Employee> retrieveAllUsers(){
		return service.retrieveEmp();
	}

}
