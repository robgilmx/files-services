package com.ksquareinc.filesservice.repository;

import com.ksquareinc.filesservice.model.Employee;
import com.ksquareinc.filesservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
