package com.dilsonjlrjr.javatrellodashboardmateus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyTemp {
    private String id;
    private String creationDate;
    private String messageId;
    private String data;
}
