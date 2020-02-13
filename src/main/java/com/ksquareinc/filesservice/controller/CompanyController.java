package com.ksquareinc.filesservice.controller;


import com.ksquareinc.filesservice.model.Company;
import com.ksquareinc.filesservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Company company) {
        Company result = companyService.save(company);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Company company) {
        companyService.delete(company);
        return ResponseEntity.ok().body("Deleted company: " + company);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Company> findAll = companyService.findAll(Optional.empty(), Optional.empty());
        return ResponseEntity.ok(findAll);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Company company){
        return ResponseEntity.ok(companyService.save(company));
    }

}