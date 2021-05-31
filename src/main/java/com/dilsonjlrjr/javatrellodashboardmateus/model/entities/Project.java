package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import com.dilsonjlrjr.javatrellodashboardmateus.model.annotation.FieldName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @FieldName("id")
    private Long id;
    @FieldName("name")
    private String name;
    private User owner;
    private String trelloToken;
    private String trelloKey;
}
