package com.ksquareinc.filesservice.service;

import com.ksquareinc.filesservice.model.Company;
import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.Office;
import com.ksquareinc.filesservice.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    OfficeRepository officeRepository;

    public List<Office> findAll(Optional<Office> example, Optional<File> file){
        List<Office> offices;
        offices = example.map(office -> officeRepository.findAll(Example.of(office))).orElseGet(() -> officeRepository.findAll());
        file.ifPresent(value -> offices.retainAll(officeRepository.findAllByFilesContains(value)));
        return offices;
    }


    public Office save(Office office){
        return officeRepository.save(office);
    }

    public void delete(Office office){
        officeRepository.delete(office);
    }

    public Office update(Office office){
        return save(office);
    }

    public Optional<Office> findOne(long id){
        return officeRepository.findById(id);
    }

    public List<Office> findAllById(List<Long> ids){
        return officeRepository.findAllById(ids);
    }

    public List<Office> saveAll(List<Office> offices) {
        List<Office> result = officeRepository.saveAll(offices);
        return result;
    }

    public void verifyOffices(List<Office> offices){
        List<Long> officesIds = new ArrayList<>();
        for (Office e : offices){
            officesIds.add(e.getId());
        }
        offices = officeRepository.findAllById(officesIds);
        for (Office e : offices){
            if (e.getFiles().isEmpty()){
                delete(e);
            }
        }
    }

}
