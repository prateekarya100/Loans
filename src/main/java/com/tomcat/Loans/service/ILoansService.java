package com.tomcat.Loans.service;

import com.tomcat.Loans.dto.LoansDto;
import com.tomcat.Loans.exception.LoanAlreadyExistsException;

public interface ILoansService {
    boolean createNewLoan(String mobileNumber) throws LoanAlreadyExistsException;

    LoansDto fetchLoanDetails(String mobileNumber);
}
