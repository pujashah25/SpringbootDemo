package com.springbootdemo.employeemanagement.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootdemo.employeemanagement.model.Employee;
import com.springbootdemo.employeemanagement.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService{
	
	@Autowired
	private final  EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Employee not found for id : "+id);
		}
	}
	
	public List<Employee> getEmployeeByName(String name) {
		List<Employee> employees = employeeRepository.searchByFirstAndOrLastName(name);
		if(employees.size() > 0) {
			return employees;
		}else {
			throw new RuntimeException("Employee not found for name : "+name);
		}
	}
	
	public List<Employee> getEmployeeByMaxSalary() {		
		List<Employee> employees = employeeRepository.findAll();		
		// Find max salary 
		BigDecimal highestSalary = employees.stream()
					.max(Comparator.comparing(Employee::getSalary))
					.map(Employee::getSalary).get();		
		// Filter the list of employee that matches that salary
		List<Employee> maxSalaryEmployees = employees.stream()
					.filter(e ->e.getSalary() == highestSalary)
					.collect(Collectors.toList());		
		return maxSalaryEmployees;
	}

	public List<Employee> getEmployeeByMinSalary() {
		List<Employee> employees = employeeRepository.findAll();		
		// Find min salary 
		BigDecimal lowestSalary = employees.stream()
					.min(Comparator.comparing(Employee::getSalary))
					.map(Employee::getSalary).get();		
		// Filter the list of employee that matches that salary
		List<Employee> minSalaryEmployees = employees.stream()
					.filter(e ->e.getSalary() == lowestSalary)
					.collect(Collectors.toList());
		return minSalaryEmployees;
	}	
}
