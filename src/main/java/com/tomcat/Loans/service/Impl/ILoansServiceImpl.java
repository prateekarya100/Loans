package com.tomcat.Loans.service.Impl;

import com.tomcat.Loans.exception.ResourceAlreadyAvailable;
import com.tomcat.Loans.model.Loans;
import com.tomcat.Loans.repository.LoansRepository;
import com.tomcat.Loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ILoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public boolean createNewLoan(String mobileNumber) {
        boolean loanCreated = false;
        Optional<Loans> loansOptional = Optional.of(loansRepository.findByMobileNumber(mobileNumber).get());
        if (loansOptional.isPresent()) {
            throw new ResourceAlreadyAvailable("loan already exists with mobile number " + mobileNumber);
        }else{
//            loansRepository.save(createNewLoanAccount());
        }
        return false;
    }


}
