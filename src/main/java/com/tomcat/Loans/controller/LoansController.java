package com.tomcat.Loans.controller;

import com.tomcat.Loans.dto.LoansDto;
import com.tomcat.Loans.dto.ResponseDto;
import com.tomcat.Loans.service.ILoansService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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

    @PostMapping(value = "/createLoan")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam
            String mobileNumber){
        loansService.createNewLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(HttpStatus.CREATED,
                            "loan granted to customer successfully"));
    }

    @GetMapping(value = "/fetchLoan")
    public ResponseEntity<LoansDto> fetchLoans(@PathParam("mobileNumber")
                                                      String mobileNumber){
        LoansDto loansDto = loansService.fetchLoanDetails(mobileNumber);
        if (loansDto != null){
            return ResponseEntity.ok(loansDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/updateLoan")
    public ResponseEntity<ResponseDto> updateLoans(@RequestBody @Valid LoansDto loansDto){
        boolean isLoanDetailUpdated = loansService.updateLoanDetails(loansDto);
        if(isLoanDetailUpdated){
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResponseDto(HttpStatus.ACCEPTED,"loan details updated successfully"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,HttpStatus.EXPECTATION_FAILED.toString()));
        }
    }
}
