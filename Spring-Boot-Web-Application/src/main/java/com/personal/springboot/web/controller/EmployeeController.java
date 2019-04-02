package com.personal.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.personal.springboot.web.model.Employee;
import com.personal.springboot.web.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		
		
		}

	@RequestMapping(value = "/list-emp", method = RequestMethod.GET)
	public String showEmp(ModelMap model) {
		model.put("employees", service.retrieveEmp());
		return "list-emp";
	}

	@RequestMapping(value = "/add-emp", method = RequestMethod.GET)
	public String showAddEmpPage(ModelMap model) {
		model.addAttribute("emp", new Employee(0, "", new Date(), "M", ""));
		return "emp";
	}

	@RequestMapping(value = "/add-emp", method = RequestMethod.POST)
	public String addEmployee(ModelMap model, @Valid Employee emp, BindingResult result){
	  
		if (result.hasErrors()) {
	       return "emp";
		}
	    service.addEmployee(emp.getEmpName(), emp.getDob(), emp.getGender(), emp.getNote());
		return "redirect:/list-emp";
	}

	@RequestMapping(value = "/update-emp", method = RequestMethod.GET)
	public String showUpdateEmpPage(@RequestParam int id, ModelMap model) {
		Employee e = service.retrieveEmployee(id);
		model.put("emp", e);
		return "emp";
	}
	
	@RequestMapping(value = "/update-emp", method = RequestMethod.POST)
	public String updateEmployee(ModelMap model, @Valid Employee emp, BindingResult result) {
		if (result.hasErrors()) {
		   return "emp";
		}
		service.updateEmployee(emp);
		return "redirect:/list-emp";
	}
	
	@RequestMapping(value = "/delete-emp", method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam int id) {
		Employee emp = service.retrieveEmployee(id);
		service.deleteEmployee(emp);
		return "redirect:/list-emp";
	}

}
