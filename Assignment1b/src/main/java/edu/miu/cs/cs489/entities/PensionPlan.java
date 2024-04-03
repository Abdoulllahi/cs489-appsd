package edu.miu.cs.cs489.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PensionPlan {
    
    private String planReferenceNumber;
    private LocalDate enrollmentDate;
    private Double monthlyContribution;
    private Employee employee;

    public PensionPlan(String planReferenceNumber, String enrollmentDate, Double monthlyContribution, Employee employee) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = LocalDate.parse(enrollmentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.monthlyContribution = monthlyContribution;
        this.employee = employee;
    }

    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }

    public void setPlanReferenceNumber(String planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }

    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = LocalDate.parse(enrollmentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
