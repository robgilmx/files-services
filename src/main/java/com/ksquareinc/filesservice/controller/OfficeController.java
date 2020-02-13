package com.ksquareinc.filesservice.controller;


import com.ksquareinc.filesservice.model.Office;
import com.ksquareinc.filesservice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/office")
public class OfficeController {

    @Autowired
    OfficeService officeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Office office) {
        Office result = officeService.save(office);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Office office) {
        officeService.delete(office);
        return ResponseEntity.ok().body("Deleted office: " + office);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Office> findAll = officeService.findAll(Optional.empty(), Optional.empty());
        return ResponseEntity.ok(findAll);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Office office){
        return ResponseEntity.ok(officeService.save(office));
    }

}