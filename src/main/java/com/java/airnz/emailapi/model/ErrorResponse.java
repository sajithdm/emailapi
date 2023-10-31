package com.java.airnz.emailapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private String errorMsg;
}
