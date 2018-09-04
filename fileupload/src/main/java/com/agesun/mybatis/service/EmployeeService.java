package com.agesun.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agesun.mybatis.bean.Employee;
import com.agesun.mybatis.dao.EmployeeMapper;



public interface EmployeeService {
	
	
	public List<Employee> getEmps();
	
	public Employee getEmpById();
	
	public Integer batchEmp();
	

}
