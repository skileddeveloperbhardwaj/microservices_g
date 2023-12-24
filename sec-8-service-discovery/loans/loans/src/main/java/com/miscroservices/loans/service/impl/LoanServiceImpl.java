package com.miscroservices.loans.service.impl;

import com.miscroservices.loans.dto.LoansDto;
import com.miscroservices.loans.entity.Loans;
import com.miscroservices.loans.exception.LoansAlreadyExistingException;
import com.miscroservices.loans.exception.ResourceNotFoundException;
import com.miscroservices.loans.mapper.LoansDtoMapper;
import com.miscroservices.loans.repository.LoansRepository;
import com.miscroservices.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            throw new LoansAlreadyExistingException("Loan already existing. Please check!");
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType("Home Loan");
        newLoan.setTotalLoan(10000000);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(300000);
        newLoan.setCreatedAt(LocalDateTime.now());
        newLoan.setCreatedBy("Admin");
        newLoan.setUpdatedAt(LocalDateTime.now());
        newLoan.setUpdatedBy("Admin");
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan with mobile number " + mobileNumber + " does not exist")
        );

        LoansDto loansDto = LoansDtoMapper.mapToLoansDto(loans, new LoansDto());
        return loansDto;
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        return false;
    }
}
