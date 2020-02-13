package com.ksquareinc.filesservice.repository;

    import com.ksquareinc.filesservice.model.File;
    import com.ksquareinc.filesservice.model.TimeOff;
    import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    import java.util.List;

@Repository
public interface TimeOffRepository extends JpaRepository<TimeOff, Long> {

    List<TimeOff> findAllByFilesContains(File file);

}
