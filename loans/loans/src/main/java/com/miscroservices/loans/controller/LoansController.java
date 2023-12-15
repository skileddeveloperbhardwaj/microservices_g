package com.miscroservices.loans.controller;

import com.miscroservices.loans.dto.LoansDto;
import com.miscroservices.loans.dto.ResponseDto;
import com.miscroservices.loans.service.ILoanService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@Validated
public class LoansController {
    private ILoanService iLoanServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must contains at least 10 digits")
                                                  String mobileNumber) {
        iLoanServiceImpl.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Loan created successfullt"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must contains at least 10 digits")
                                              String mobileNumber) {
        LoansDto loansDto = iLoanServiceImpl.fetchLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }
}
