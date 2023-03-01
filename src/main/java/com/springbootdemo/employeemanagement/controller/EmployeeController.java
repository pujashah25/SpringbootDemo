package com.springbootdemo.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdemo.employeemanagement.model.Employee;
import com.springbootdemo.employeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	@Autowired
	private final EmployeeService employeeService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/findemployee")
	public Employee getEmployeeById(@RequestParam(required =  false) Long id, @RequestParam(required =  false) String firstName, @RequestParam(required =  false) String lastName) {
		return employeeService.getEmployeeByIdOrName(id,firstName,lastName);
	}
	
	@GetMapping("/findemployeeByMaxSalary")
	public Employee getEmployeeByMaxSalary() {
		return employeeService.getEmployeeByMaxSalary();
	}
	
	@GetMapping("/findemployeeByMinSalary")
	public Employee getEmployeeByMinSalary() {
		return employeeService.getEmployeeByMinSalary();
	}

}
