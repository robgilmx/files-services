package com.ksquareinc.filesservice.repository;

import com.ksquareinc.filesservice.model.File;
import com.ksquareinc.filesservice.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

    List<Office> findAllByFilesContains(File file);

}
