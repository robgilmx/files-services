package com.ksquareinc.filesservice.service;

import com.ksquareinc.filesservice.model.Company;
import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.TimeOff;
import com.ksquareinc.filesservice.repository.TimeOffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeOffService {

    @Autowired
    TimeOffRepository timeOffRepository;

    public List<TimeOff> findAll(Optional<TimeOff> example, Optional<File> file){
        List<TimeOff> timeOffs;
        timeOffs = example.map(timeOff -> timeOffRepository.findAll(Example.of(timeOff))).orElseGet(() -> timeOffRepository.findAll());
        file.ifPresent(value -> timeOffs.retainAll(timeOffRepository.findAllByFilesContains(value)));
        return timeOffs;
    }

    public TimeOff save(TimeOff timeOff){
        return timeOffRepository.save(timeOff);
    }

    public void delete(TimeOff timeOff){
        timeOffRepository.delete(timeOff);
    }

    public TimeOff update(TimeOff timeOff){
        return save(timeOff);
    }

    public Optional<TimeOff> findOne(long id){
        return timeOffRepository.findById(id);
    }

    public List<TimeOff> findAllById(List<Long> ids){
        return timeOffRepository.findAllById(ids);
    }

    public List<TimeOff> saveAll(List<TimeOff> timeOffs) {
        List<TimeOff> result = timeOffRepository.saveAll(timeOffs);
        return result;
    }

    public void verifyTimeOffs(List<TimeOff> timeOffs){
        List<Long> timeOffsIds = new ArrayList<>();
        for (TimeOff e : timeOffs){
            timeOffsIds.add(e.getId());
        }
        timeOffs = timeOffRepository.findAllById(timeOffsIds);
        for (TimeOff e : timeOffs){
            if (e.getFiles().isEmpty()){
                delete(e);
            }
        }
    }

}
