package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import com.dilsonjlrjr.javatrellodashboardmateus.model.annotation.FieldName;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime delivery;
    private Integer numberWork;
    private Integer hoursPerDay;
}
