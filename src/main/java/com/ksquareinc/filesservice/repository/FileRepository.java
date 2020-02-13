package com.ksquareinc.filesservice.repository;

import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.Office;
import com.ksquareinc.filesservice.model.TimeOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByEmployeesContaining(Employee employees);
    List<File> findAllByOfficesContaining(Office office);
    List<File> findAllByTimeOffsContaining(TimeOff timeOff);

    List<File> findAllByEmployeesContainsOrOfficesContainsOrTimeOffsContains(Employee employee, Office office, TimeOff timeOff);
}
