package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootdemo.employeemanagement.model.Department;
import com.springbootdemo.employeemanagement.model.Employee;
import com.springbootdemo.employeemanagement.repository.EmployeeRepository;
import com.springbootdemo.employeemanagement.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private Employee employee;
	
	ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeEach
	public void setup() {
		employee = new Employee();
		employee.setFirstName("Puja");
		employee.setLastName("Shah");
		employee.setEmailAddress("puja.shah@gmail.com");
		employee.setDepartment(new Department(1L,"Finance"));
	}
	
	@Test
	@DisplayName("Should save the employee object to database")
	public void saveEmployee() throws JsonProcessingException {
		
		//stub save method
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		//save employee
		Employee newEmployee = employeeService.saveEmployee(employee);
		
		//check if its saved
		assertNotNull(newEmployee);
		assertThat(newEmployee.getFirstName()).isEqualTo("Puja");
		assertThat(newEmployee.getEmailAddress()).isEqualTo("puja.shah@gmail.com");
		assertThat(newEmployee.getDepartment().getDepName()).isEqualTo("Finance");
		
	}
	
	@Test
	@DisplayName("Should returm the employee object")
	public void getEmployeeById() {
		employee = new Employee();
		employee.setFirstName("Kshitij");
		employee.setLastName("Patel");
		employee.setEmailAddress("kshitij.patel@gmail.com");
		employee.setDepartment(new Department(3L,"Production"));
		
		//stub save method
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		Employee newEmployee = employeeService.saveEmployee(employee);
		assertNotNull(newEmployee);
		
		when(employeeRepository.findById(newEmployee.getEmpId())).thenReturn(Optional.of(newEmployee));
		
		Employee existingEmployee = employeeService.getEmployeeById(newEmployee.getEmpId());
		
		assertNotNull(existingEmployee);
		assertThat(existingEmployee.getEmpId()).isEqualTo(newEmployee.getEmpId());
	}
	
	@Test
	@DisplayName("Should throw the Exception")
	public void getEmployeeByIdForException() {
		//stub save method
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
				
		Employee newEmployee = employeeService.saveEmployee(employee);
		
		when(employeeRepository.findById(newEmployee.getEmpId())).thenReturn(Optional.of(newEmployee));
		
		assertThrows(RuntimeException.class,() -> {
			employeeService.getEmployeeById(100L);
		});
		
	}

}
 