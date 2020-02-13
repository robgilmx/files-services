package com.ksquareinc.filesservice.controller;


import com.ksquareinc.filesservice.model.TimeOff;
import com.ksquareinc.filesservice.service.TimeOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/timeOff")
public class TimeOffController {

    @Autowired
    TimeOffService timeOffService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody TimeOff timeOff) {
        TimeOff result = timeOffService.save(timeOff);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody TimeOff timeOff) {
        timeOffService.delete(timeOff);
        return ResponseEntity.ok().body("Deleted timeOff: " + timeOff);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<TimeOff> findAll = timeOffService.findAll(Optional.empty(), Optional.empty());
        return ResponseEntity.ok(findAll);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody TimeOff timeOff){
        return ResponseEntity.ok(timeOffService.save(timeOff));
    }

}