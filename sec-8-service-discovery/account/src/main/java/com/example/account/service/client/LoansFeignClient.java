package com.example.account.service.client;

import com.example.account.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {
    @GetMapping(value = "/api/fetch",consumes = {"application/json"})
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam String mobileNumber);
}
