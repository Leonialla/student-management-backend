package com.example.model.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterErrorVO {

    private String fieldName;
    private String message;
}
