package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Employee;

public interface IEmployeeService {
	
	public void saveEmployee(Employee e);
	public Employee getEmployeeById(Integer id);
	public List<Employee> getAllEmployees();
	public void deleteEmployeeById(Integer id);
	public boolean isExist(Integer id);

}
