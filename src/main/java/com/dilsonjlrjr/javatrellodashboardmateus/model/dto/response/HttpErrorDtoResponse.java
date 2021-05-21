package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorDtoResponse {
    private Integer httpStatus;
    private String code;
    private String message;
    private Object data;
}
