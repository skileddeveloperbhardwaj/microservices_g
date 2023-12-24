package com.example.account.service.impl;

import com.example.account.dto.AccountsDto;
import com.example.account.dto.CustomerDetailsDto;
import com.example.account.dto.LoansDto;
import com.example.account.entity.Account;
import com.example.account.entity.Customer;
import com.example.account.exception.ResourceNotFoundException;
import com.example.account.mapper.AccountsMapper;
import com.example.account.mapper.CustomerMapper;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.ICustomerService;
import com.example.account.service.client.CardsFeignClient;
import com.example.account.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found")
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not found")
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoan(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        customerDetailsDto.setCardsDto(cardsFeignClient.fetchCards(mobileNumber).getBody());
        return customerDetailsDto;
    }
}
