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
            @Valid
            @RequestParam
            @Pattern(regexp = "^$|[0-9]{10}",message = "mobile number must be of 10 digit only")
            String mobileNumber){
        loansService.createNewLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(HttpStatus.CREATED,
                            "loan granted to customer successfully"));
    }

    @GetMapping(value = "/fetchLoan")
    public ResponseEntity<LoansDto> fetchLoans(@Valid @RequestParam("mobileNumber")
                                                   @Pattern(regexp = "^$|[0-9]{10}",message = "mobile number must be of 10 digit only")
                                                   String mobileNumber) {
        LoansDto loansDto = loansService.fetchLoanDetails(mobileNumber);
        if (loansDto != null){
            return ResponseEntity.ok(loansDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/updateLoan")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){
       boolean isUpdated = loansService.updateLoanDetails(loansDto);
       if (isUpdated){
           return ResponseEntity.status(HttpStatus.OK)
                   .body(new ResponseDto(HttpStatus.OK,"customer loan details updated successfully"));
       }else {
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                   .body(new ResponseDto(HttpStatus.EXPECTATION_FAILED,
                           "something went wrong, during loan details update please contact to development team"));
       }
    }

    @DeleteMapping(value = "/loanClosure")
    public ResponseEntity<ResponseDto> loanClosure(@Valid @RequestParam
                                                       @Pattern(regexp = "^$|[0-9]{10}",message = "mobile number must be of 10 digit only")
                                                       String mobileNumber){
        boolean isloanClosed = loansService.loanClosure(mobileNumber);
        if (isloanClosed){
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResponseDto(HttpStatus.ACCEPTED,
                            "loan closed successfully,now there is no data exist for this customer"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST,
                            "oops, something went wrong, please contact to development team"));
        }
    }
}
