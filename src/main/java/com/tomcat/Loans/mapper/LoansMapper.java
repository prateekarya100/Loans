package com.tomcat.Loans.mapper;

import com.tomcat.Loans.dto.LoansDto;
import com.tomcat.Loans.model.Loans;

public class LoansMapper {

    public static Loans mapToLoans(LoansDto loansDto,Loans loans) {
        loans.setLoanAccountNumber(loansDto.getLoanAccountNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }

    public static LoansDto mapToDto(Loans loans,LoansDto loansDto) {
        loansDto.setLoanAccountNumber(loans.getLoanAccountNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }
}
