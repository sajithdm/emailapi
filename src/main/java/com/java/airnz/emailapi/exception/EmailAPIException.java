package com.java.airnz.emailapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Custom exception as a placeholder for HTTP 400 Bad Request.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailAPIException extends RuntimeException {
    private int code;
    private String message;
}
