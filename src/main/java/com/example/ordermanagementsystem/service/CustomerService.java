package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;

    public DomainCustomer save(DomainCustomer item) {
        return repo.save(item);
    }

    public DomainCustomer get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public List<DomainCustomer> getAll() {
        return repo.findAll();
    }
}
