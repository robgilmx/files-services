package com.ksquareinc.filesservice.service;


import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        Employee result = employeeRepository.save(employee);
        return result;
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public List<Employee> saveAll(List<Employee> employees) {
        List<Employee> result = employeeRepository.saveAll(employees);
        return result;
    }

    public List<Employee> findAll() {
        List<Employee> findAll = employeeRepository.findAll();
        return findAll;
    }

    public void verifyEmployees(List<Employee> employees){
        List<Long> employeesIds = new ArrayList<>();
        for (Employee e : employees){
            employeesIds.add(e.getId());
        }
        employees = employeeRepository.findAllById(employeesIds);
        for (Employee e : employees){
            if (e.getFiles().isEmpty()){
                delete(e);
            }
        }
    }
}