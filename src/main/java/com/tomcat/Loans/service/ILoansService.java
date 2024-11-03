package com.tomcat.Loans.service;

import com.tomcat.Loans.exception.LoanAlreadyExistsException;

public interface ILoansService {
    boolean createNewLoan(String mobileNumber) throws LoanAlreadyExistsException;
}
