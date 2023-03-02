package com.springbootdemo.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootdemo.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query("select e from Employee e where e.firstName= :searchName or e.lastName = :searchName or concat(e.firstName,' ',e.lastName) = :searchName")
	List<Employee> searchByFirstAndOrLastName(@Param("searchName") String name);

}
