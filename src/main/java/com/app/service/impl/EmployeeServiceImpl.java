package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Transactional
	public void saveEmployee(Employee e) {
		repo.save(e);

	}

	@Transactional(readOnly = true)
	public Employee getEmployeeById(Integer id) {
		Optional<Employee> emp = repo.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Transactional
	public void deleteEmployeeById(Integer id) {
		repo.deleteById(id);

	}

	@Transactional
	public boolean isExist(Integer id) {
		return repo.existsById(id);
		
	}

}
