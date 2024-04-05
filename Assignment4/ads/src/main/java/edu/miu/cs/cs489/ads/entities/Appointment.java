package edu.miu.cs.cs489.ads.entities;

import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private Long id;
    private Dentist dentist; // @ManyToOne
    private Patient patient; // @ManyToOne
    private LocalDate date;
    private Time time;
    private String status;
}
