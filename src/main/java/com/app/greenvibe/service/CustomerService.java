package com.app.greenvibe.service;

import com.app.greenvibe.dto.request.CustomerRegisterRequestDto;
import com.app.greenvibe.dto.request.LoginRequestDto;
import com.app.greenvibe.dto.response.AuthenticationResponse;
import com.app.greenvibe.dto.response.CustomerResponseDto;
import com.app.greenvibe.entity.Customer;
import com.app.greenvibe.exception.DuplicateResourceException;
import com.app.greenvibe.exception.InvalidCredentialsException;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.CustomerMapper;
import com.app.greenvibe.repository.CustomerRepository;
import com.app.greenvibe.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Transactional
    public CustomerResponseDto register(CustomerRegisterRequestDto request) {
        log.info("Registration attempt for email={}, fullName={}", request.getEmail(), request.getFullName());

        if (customerRepository.existsByEmail(request.getEmail())) {
            log.warn("Registration blocked: duplicate email={}", request.getEmail());
            throw new DuplicateResourceException("Customer", "email", request.getEmail());
        }

        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer registered successfully with customerId={}", savedCustomer.getId());
        return customerMapper.toDto(savedCustomer);
    }

    public AuthenticationResponse login(LoginRequestDto request) {
        log.info("Login attempt for email={}", request.getEmail());
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("Login failed: email not found email={}", request.getEmail());
                    return new InvalidCredentialsException();
                });

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            log.warn("Login failed: invalid password for customerId={}", customer.getId());
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(customer);
        log.info("Login successful for customerId={}", customer.getId());
        return AuthenticationResponse.builder()
                .token(token)
                .message("login successful")
                .build();
    }

    public CustomerResponseDto getCustomerById(Long id) {
        log.debug("Fetching customer by id={}", id);
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }
}
