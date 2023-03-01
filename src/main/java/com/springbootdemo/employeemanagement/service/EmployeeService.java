package com.springbootdemo.employeemanagement.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootdemo.employeemanagement.model.Employee;
import com.springbootdemo.employeemanagement.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	public Employee getEmployeeByIdOrName(Long id, String firstName, String lastName) {
		Optional<Employee> employee = employeeRepository.findByEmpIdOrFirstNameOrLastName(id,firstName,lastName);
		System.out.println("Employee::"+employee+"::"+firstName);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Employee not found for id : "+id);
		}
	}
	
public Employee getEmployeeByMaxSalary() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		Comparator<Employee> comparator = Comparator.comparing( Employee::getSalary );
		
		Employee maxSalaryEmployee = employees.stream().max(comparator).get();

		return maxSalaryEmployee;
	}

	public Employee getEmployeeByMinSalary() {
		List<Employee> employees = employeeRepository.findAll();
		
		Comparator<Employee> comparator = Comparator.comparing( Employee::getSalary );
		
		Employee minSalaryEmployee = employees.stream().min(comparator).get();

		return minSalaryEmployee;
	}


	/*public List<Employee> getEmployeesByMaxSalary() {
		
		List<Employee> employees = employeeRepository.findAll();
		
		Map<Long,Employee> mapEmployeesByDep = employees.stream()
							.collect(Collectors.groupingBy(e -> e.getDepartment().getDepId(),
									Collectors.collectingAndThen(Collectors.maxBy(
									Comparator.comparingInt(e -> e.getSalary().intValue())), Optional::get)));
		
		System.out.println("mapEmployeesByDep::"+mapEmployeesByDep.);
									
									
		
		List<Employee> maxSalaryEmployees = new ArrayList<Employee>();

		return maxSalaryEmployees;
	}*/

	public List<Employee> getEmployeesByMinSalary() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
