package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository repo;
    private final EntityManager em;

    @Transactional
    public DomainCustomer save(DomainCustomer item) {
        return repo.save(item);
    }

    public DomainCustomer get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public DomainCustomer getIncludeOrderThenIncludeOrderLine(UUID id) {
        return repo.getIncludeOrderThenIncludeOrderLine(id);
    }

    public List<DomainCustomer> getAll() {
        return repo.findAll();
    }
}
