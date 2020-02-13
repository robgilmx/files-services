package com.ksquareinc.filesservice.controller;

import com.ksquareinc.filesservice.model.*;
import com.ksquareinc.filesservice.model.responses.Wrapper;
import com.ksquareinc.filesservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    FileService fileService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        Optional<File> result = fileService.findOne(id);
        if (!result.isPresent()){
            return ResponseEntity.status(422).body("No entity with that ID");
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody File file){
        return ResponseEntity.ok().body(fileService.save(file));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/byIds")
    public ResponseEntity<?> findAllById(@RequestParam(value = "ids",required = false) Iterable<Long> ids){
        return ResponseEntity.ok(fileService.findAllById(ids));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> searchAll(@RequestParam(value = "id",required = false) Optional<Long> id,
                                     @RequestParam(value = "name", required = false) Optional<String> name,
                                     @RequestParam(value = "privateFile", required = false) Optional<Boolean> privateFile,
                                     @RequestParam(value = "company", required = false) Optional<Company> company,
                                     @RequestParam(value = "office", required = false) Optional<Office> office,
                                     @RequestParam(value = "employee", required = false) Optional<Employee> employee,
                                     @RequestParam(value = "timeOff", required = false) Optional<TimeOff> timeOff,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                     @RequestParam(value = "uploadDate", required = false) Optional<LocalDateTime> uploadDate
                                     ){

        File file = new File();
        file.setId(id.orElse(null));
        file.setName(name.orElse(null));
        file.setPrivateFile(privateFile.orElse(null));
        file.setCompany(company.orElse(null));
        file.setUploadDate(uploadDate.orElse(null));

        List<File> files = fileService.findAll(file, employee, timeOff, office);

        Wrapper<File> response = new Wrapper<>();
        response.setContent(files);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody File file){
        return ResponseEntity.ok().body(fileService.update(file));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody File file){
        fileService.delete(file);
        return ResponseEntity.ok().body("Deleted file: " + file);
    }

}
