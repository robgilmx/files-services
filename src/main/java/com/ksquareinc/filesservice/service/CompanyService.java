package com.ksquareinc.filesservice.service;

import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.Company;
import com.ksquareinc.filesservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> findAll(Optional<Company> example, Optional<File> file){
        List<Company> companys;
        companys = example.map(company -> companyRepository.findAll(Example.of(company))).orElseGet(() -> companyRepository.findAll());
        file.ifPresent(value -> companys.retainAll(companyRepository.findAllByFilesContains(value)));
        return companys;
    }

    public Company save(Company company){
        return companyRepository.save(company);
    }

    public void delete(Company company){
        companyRepository.delete(company);
    }

    public Company update(Company company){
        return save(company);
    }

    public Optional<Company> findOne(long id){
        return companyRepository.findById(id);
    }

    public List<Company> findAllById(List<Long> ids){
        return companyRepository.findAllById(ids);
    }



}
