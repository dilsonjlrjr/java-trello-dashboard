package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SprintDtoRequest {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("goal")
    private String goal;

    @NotNull
    @JsonProperty("start")
    private LocalDate start;

    @NotNull
    @JsonProperty("end")
    private LocalDate end;

    @JsonProperty("delivery")
    private LocalDate delivery;

    @NotNull
    @JsonProperty("number_of_work")
    private Integer numberWork;

    @NotNull
    @JsonProperty("hours_per_day")
    private Integer hoursPerDay;
}
