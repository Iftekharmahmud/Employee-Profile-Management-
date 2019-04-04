package com.personal.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.personal.springboot.web.model.Employee;
import com.personal.springboot.web.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	RestConsumer consumer;

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
	public String addEmployee(ModelMap model, @Valid Employee emp, BindingResult result) {

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

	@RequestMapping(value = "/employeeReport", method = RequestMethod.GET)
	public String showReport(ModelMap modelMap) {
		Map<String, Integer> employeeMap = new HashMap<String, Integer>();
		List<Employee> emps = service.retrieveEmp();
		int countM = 0, countF = 0, countO = 0;
		for (Employee e : emps) {
			switch (e.getGender()) {
			case "M":
				countM++;
				break;
			case "F":
				countF++;
				break;
			default:
				countO++;
				break;
			}
		}
		employeeMap.put("Male", countM);
		employeeMap.put("Female", countF);
		employeeMap.put("Other", countO);

		modelMap.put("employeeMap", employeeMap);
		return "employeeReport";
	}
	
	@RequestMapping(value = "/interaction", method = RequestMethod.GET)
	public String showInteraction(ModelMap modelMap) {
		modelMap.put("HEADER", consumer.getHeader());
		
		Map<String, String> dataMap = consumer.getDataMap();
		modelMap.put("dataMap", dataMap);
		//consumer.getDataMap();
		return "interaction";
	}
	

}
