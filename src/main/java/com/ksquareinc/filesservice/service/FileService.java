package com.ksquareinc.filesservice.service;

import com.ksquareinc.filesservice.controller.EmployeeController;
import com.ksquareinc.filesservice.model.*;
import com.ksquareinc.filesservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Value("${mircroservices.employees}")
    String employeeSystem;
    @Value("${microservices.time}")
    String timeManagementSystem;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OfficeService officeService;

    @Autowired
    TimeOffService timeOffService;

    @Autowired
    CompanyService companyService;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Optional<File> findOne(Long id){
        Optional<File> result = fileRepository.findById(id);
        return result;
    }

    public File save(File file){
        if (file.getCompany().equals(null)){

        }
        if (!file.getEmployees().isEmpty()){
            employeeService.saveAll(file.getEmployees());
        }
        if (!file.getTimeOffs().isEmpty()){
            timeOffService.saveAll(file.getTimeOffs());
        }
        if (!file.getOffices().isEmpty()){
            officeService.saveAll(file.getOffices());
        }

        return file;
    }

    public ResponseEntity<?> findAllById(Iterable<Long> ids){
        return ResponseEntity.ok(fileRepository.findAllById(ids));
    }

    public List<File> findAll(
                                       File example,
                                       Optional<Employee> employee,
                                       Optional<TimeOff> timeOff,
                                       Optional<Office> office
    ){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        List<File> files = fileRepository.findAll(Example.of(example,exampleMatcher));
        employee.ifPresent(value -> files.retainAll(fileRepository.findAllByEmployeesContaining(value)));
        timeOff.ifPresent(value -> files.retainAll(fileRepository.findAllByTimeOffsContaining(value)));
        office.ifPresent(value -> files.retainAll(fileRepository.findAllByOfficesContaining(value)));

        return files;
    }

    public File update(File file){
        if (!file.getEmployees().isEmpty()){
            List<Employee> employeeList = fileRepository.findById(file.getId()).orElse(new File()).getEmployees();
            employeeService.saveAll(file.getEmployees());
            file = fileRepository.save(file);
            employeeService.verifyEmployees(employeeList);
        }
        return file;
    }

    public void delete(File file){
        List<Employee> employeeList = fileRepository.findById(file.getId()).orElse(new File()).getEmployees();
        List<TimeOff> timeOffList = fileRepository.findById(file.getId()).orElse(new File()).getTimeOffs();
        List<Office> officeList = fileRepository.findById(file.getId()).orElse(new File()).getOffices();
        fileRepository.delete(file);
        employeeService.verifyEmployees(employeeList);
        timeOffService.verifyTimeOffs(timeOffList);
        officeService.verifyOffices(officeList);
    }


}
