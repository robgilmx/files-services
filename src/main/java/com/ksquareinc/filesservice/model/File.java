package com.ksquareinc.filesservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "files")
public class File {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    @Column
    private String content;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String fileType;
    @Column(name = "private_file")
    private Boolean privateFile = true;
    @Column
    private LocalDateTime uploadDate = LocalDateTime.now();
    @ManyToOne
    @JsonIgnoreProperties("files")
    private Company company;
    @ManyToMany
    @JoinTable(name = "file_employee",
            joinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "file_office",
            joinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "office_id", referencedColumnName = "id"))
    private List<Office> offices = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "file_time_off",
            joinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "time_off_id", referencedColumnName = "id"))
    private List<TimeOff> timeOffs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPrivateFile() {
        return privateFile;
    }

    public void setPrivateFile(Boolean privateFile) {
        this.privateFile = privateFile;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<TimeOff> getTimeOffs() {
        return timeOffs;
    }

    public void setTimeOffs(List<TimeOff> timeOffs) {
        this.timeOffs = timeOffs;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id.equals(file.id) &&
                Objects.equals(content, file.content) &&
                Objects.equals(name, file.name) &&
                Objects.equals(description, file.description) &&
                Objects.equals(fileType, file.fileType) &&
                Objects.equals(privateFile, file.privateFile) &&
                Objects.equals(uploadDate, file.uploadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, name, description, fileType, privateFile, uploadDate);
    }
}
