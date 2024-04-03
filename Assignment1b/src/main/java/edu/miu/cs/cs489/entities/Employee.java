package edu.miu.cs.cs489.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@SuppressWarnings("unused")
public class Employee {
    
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private Double yearlySalary;
    private String planReference;

    public Employee(Long employeeId, String firstName, String lastName, String employmentDate, Double yearlySalary) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = LocalDate.parse(employmentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.yearlySalary = yearlySalary;
    }
    
    public Long getEmployeeId() {
        return this.employeeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getEmploymentDate() {
        return this.employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = LocalDate.parse(employmentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public double getYearlySalary() {
        return this.yearlySalary;
    }
}
