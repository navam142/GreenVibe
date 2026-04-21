package com.app.greenvibe.service;

import com.app.greenvibe.dto.request.CustomerRegisterRequestDto;
import com.app.greenvibe.dto.request.LoginRequestDto;
import com.app.greenvibe.dto.response.CustomerResponseDto;
import com.app.greenvibe.entity.Customer;
import com.app.greenvibe.exception.DuplicateResourceException;
import com.app.greenvibe.exception.InvalidCredentialsException;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.CustomerMapper;
import com.app.greenvibe.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerResponseDto register(CustomerRegisterRequestDto request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Customer", "email", request.getEmail());
        }

        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerResponseDto login(LoginRequestDto request) {

        return customerRepository.findByEmail(request.getEmail()).
                filter(customer -> passwordEncoder.matches(request.getPassword(), customer.getPassword())).
                map(customerMapper::toDto)
                .orElseThrow(InvalidCredentialsException::new);
    }

    public CustomerResponseDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }
}
