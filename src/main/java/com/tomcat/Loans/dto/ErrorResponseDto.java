package com.tomcat.Loans.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String serverResponse;
    private LocalDateTime errorTime;
}
