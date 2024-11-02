package com.tomcat.Loans.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor
public class Response {
    private HttpStatus status;
    private String response;
}
