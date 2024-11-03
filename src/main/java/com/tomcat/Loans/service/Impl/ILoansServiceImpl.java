package com.tomcat.Loans.service.Impl;

import com.tomcat.Loans.constants.LoansConstants;
import com.tomcat.Loans.exception.LoanAlreadyExistsException;
import com.tomcat.Loans.model.Loans;
import com.tomcat.Loans.repository.LoansRepository;
import com.tomcat.Loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ILoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public boolean createNewLoan(String mobileNumber) throws LoanAlreadyExistsException {
        boolean loanCreated = false;
        Optional<Loans> loansOptional = loansRepository.findByMobileNumber(mobileNumber);
        if (loansOptional.isPresent()) {
            throw new LoanAlreadyExistsException("loan already exists with mobile number " + mobileNumber);
        }else{
            loansRepository.save(createNewLoanAccount(mobileNumber));
        }
        return false;
    }

    private Loans createNewLoanAccount(String mobileNumber) {
        Loans loans = new Loans();
        Long loanAccountNumber=10000000000L + new Random(900000000).nextInt();
        loans.setLoanId(loanAccountNumber);
        loans.setMobileNumber(mobileNumber);
        loans.setTotalLoan(LoansConstants.TOTAL_LOAN_AMOUNT_DISBURSED);
        loans.setAmountPaid(LoansConstants.LOAN_AMOUNT_PAID);
        loans.setOutstandingAmount(LoansConstants.OUTSTANDING_LOAN_AMOUNT);
        return loans;
    }


}
