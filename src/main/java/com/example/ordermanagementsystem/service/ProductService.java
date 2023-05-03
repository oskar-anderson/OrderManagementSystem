package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import com.example.ordermanagementsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository repo;

    public DomainProduct get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public DomainProduct getIncludeOrderLinesThenIncludeOrder(UUID id) {
        return repo.getProductOrderLines(id);
    }

    public List<DomainProduct> getAll() {
        return repo.findAll();
    }

    @Transactional
    public DomainProduct save(DomainProduct item) {
        return repo.save(item);
    }

    @Transactional
    public DomainProduct softDelete(UUID id, OffsetDateTime endDate) {
        val domain = repo.findById(id).orElseThrow();
        domain.setEndDate(endDate);
        repo.save(domain);
        return domain;
    }
}
