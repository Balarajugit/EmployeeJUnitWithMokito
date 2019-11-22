package com.app.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
	@Autowired
	private IEmployeeService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee e){
		ResponseEntity<String> res=null;
		try {
			service.saveEmployee(e);
			res=new ResponseEntity<String>("record saved sucessfully", HttpStatus.OK);
		} catch (Exception e2) {
			res=new ResponseEntity<String>(e2.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e2.printStackTrace();
			
		}
		return res;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id){
		ResponseEntity<?> res=null;
		try {
			Optional<Employee> emp = service.getEmployeeById(id);
			if(emp.isPresent()) {
				res=new ResponseEntity<Employee>(emp.get(), HttpStatus.OK);
			}
			else {
				res=new ResponseEntity<String>("id was not found", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		ResponseEntity<?> res=null;
		try {
			List<Employee> all = service.getAllEmployees();
			if(all.isEmpty() || all==null) {
				res=new ResponseEntity<String>("No Data", HttpStatus.NO_CONTENT);
			}
			else {
				res=new ResponseEntity<List<Employee>>(all, HttpStatus.OK);
			}
		} catch (Exception e) {
	e.printStackTrace();
	res=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		ResponseEntity<String> res=null;
		try {
			boolean exist = service.isExist(id);
			if(exist) {
				service.deleteEmployeeById(id);
				res=new ResponseEntity<String>("Record deleted sucessfully", HttpStatus.OK);
			}
			else {
				res=new ResponseEntity<String>("product not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
		}
	@PutMapping("/update")
	public ResponseEntity<String> updateRecord(@RequestBody Employee emp){
		ResponseEntity<String> res=null;
		try {
			boolean exist = service.isExist(emp.getId());
			if(exist) {
				service.saveEmployee(emp);
				res=new ResponseEntity<String>("record updated sucessfully", HttpStatus.OK);
			}
			else {
				res=new ResponseEntity<String>("No data found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	
	}

}
