package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import com.dilsonjlrjr.javatrellodashboardmateus.model.annotation.FieldName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {
    private Long id;
    private Project project;
    @FieldName("name")
    private String name;
    private String goal;
    private LocalDate start;
    private LocalDate end;
    private LocalDate delivery;
    private Integer numberWork;
    private Integer hoursPerDay;
}
