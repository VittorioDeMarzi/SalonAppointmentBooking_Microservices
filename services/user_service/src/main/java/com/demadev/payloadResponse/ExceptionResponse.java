package com.demadev.payloadResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExceptionResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;
}
