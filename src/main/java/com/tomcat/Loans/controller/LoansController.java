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

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                      @Valid
                                                      @Pattern(regexp = "!$[0-9]{10}",message = "mobile number must be of 10 digit only")
                                                      String mobileNumber){
        loansService.createNewLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(HttpStatus.CREATED,
                            "loan created successfully"));
    }

    @GetMapping(value = "/fetch-loan")
    public ResponseEntity<ResponseDto> fetchLoans(@PathVariable String mobileNumber){
        LoansDto loansDto = loansService.fetchLoanDetails(mobileNumber);
        if(loansDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK,"loan details fetched successfully"));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,HttpStatus.EXPECTATION_FAILED.toString()));
        }
    }

//    @PutMapping(value = "/update-loan")
//    public ResponseEntity<ResponseDto> updateLoans(@RequestBody @Valid LoansDto loansDto){
//        boolean isLoanDetailUpdated = loansService.updateLoanDetails(loansDto);
//        if(isLoanDetailUpdated){
//            return ResponseEntity
//                    .status(HttpStatus.ACCEPTED)
//                    .body(new ResponseDto(HttpStatus.ACCEPTED,"loan details updated successfully"));
//        }else{
//            return ResponseEntity
//                    .status(HttpStatus.EXPECTATION_FAILED)
//                    .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,HttpStatus.EXPECTATION_FAILED.toString()));
//        }
//    }
}
