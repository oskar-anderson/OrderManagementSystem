package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import com.example.ordermanagementsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository repo;

    public DomainProduct get(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    public List<DomainProduct> getAll() {
        return repo.findAll();
    }

    public DomainProduct save(DomainProduct item) {
        return repo.save(item);
    }
}
