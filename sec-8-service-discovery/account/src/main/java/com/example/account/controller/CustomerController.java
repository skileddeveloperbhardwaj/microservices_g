package com.example.account.controller;

import com.example.account.dto.CustomerDetailsDto;
import com.example.account.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private final ICustomerService iCustomerServiceImpl;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerServiceImpl = iCustomerService;
    }
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "[0-9]{10}", message = "Mobile number must contain at least 10 digits")
                                                                   String mobileNumber) {

        CustomerDetailsDto customerDetailsDto = iCustomerServiceImpl.fetchCustomerDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);

    }
}
