package com.ksquareinc.filesservice.repository;

import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByFilesContains(File file);

}
