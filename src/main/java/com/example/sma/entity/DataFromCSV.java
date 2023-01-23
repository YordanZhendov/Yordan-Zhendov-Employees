package com.example.sma.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data_csv")
public class DataFromCSV {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String employeeId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private long totalDaysInProject;


}
