package com.tomcat.Loans.controller;

import com.tomcat.Loans.dto.ResponseDto;
import com.tomcat.Loans.service.ILoansService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "EazyBank Loans Service",
        description = "EazyBank Loans Microservices Restful WebServices Documentation"
)
@RestController
@RequestMapping(path = "/api/loans",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@AllArgsConstructor
public class LoansController {

    private ILoansService loansService;

    @PostMapping(value = "/create-loan")
    public ResponseEntity<ResponseDto> createLoan(@PathVariable String mobileNumber){
        boolean isLoanCreated = loansService.createNewLoan(mobileNumber);
        if(isLoanCreated){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(HttpStatus.CREATED,
                            "loan created successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,
                            "something went wrong, please try again later or contact with dev team"));
        }
    }
}
