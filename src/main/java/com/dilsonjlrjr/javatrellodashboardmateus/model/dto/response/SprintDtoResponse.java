package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SprintDtoResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("project")
    private ProjectDtoResponse project;
    @JsonProperty("name")
    private String name;
    @JsonProperty("goal")
    private String goal;
    @JsonProperty("start")
    private LocalDateTime start;
    @JsonProperty("end")
    private LocalDateTime end;
    @JsonProperty("delivery")
    private LocalDateTime delivery;
    @JsonProperty("number_of_work")
    private Integer numberWork;
    @JsonProperty("hours_per_day")
    private Integer hoursPerDay;
}
