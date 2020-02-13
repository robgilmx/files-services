package com.ksquareinc.filesservice.controller;


import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Employee employee) {
        employeeService.delete(employee);
        return ResponseEntity.ok().body("Deleted employee: " + employee);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Employee> employees) {
        List<Employee> result = employeeService.saveAll(employees);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Employee> findAll = employeeService.findAll();
        return ResponseEntity.ok(findAll);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

}