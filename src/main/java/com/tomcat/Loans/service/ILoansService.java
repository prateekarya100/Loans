package com.tomcat.Loans.service;

import com.tomcat.Loans.dto.LoansDto;
import com.tomcat.Loans.exception.LoanAlreadyExistsException;
import jakarta.validation.Valid;

public interface ILoansService {
    boolean createNewLoan(String mobileNumber);

    LoansDto fetchLoanDetails(String mobileNumber);

    boolean updateLoanDetails(LoansDto loansDto);
}
