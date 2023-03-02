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
	
	@GetMapping
	public Employee getEmployeeById(@RequestParam Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/findemployeeByName")
	public List<Employee> getEmployeeByName(@RequestParam String name) {
		return employeeService.getEmployeeByName(name);
	}
	
	@GetMapping("/findemployeeByMaxSalary")
	public List<Employee> getEmployeeByMaxSalary() {
		return employeeService.getEmployeeByMaxSalary();
	}
	
	@GetMapping("/findemployeeByMinSalary")
	public List<Employee> getEmployeeByMinSalary() {
		return employeeService.getEmployeeByMinSalary();
	}

}
