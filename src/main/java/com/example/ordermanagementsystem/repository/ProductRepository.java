package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<DomainProduct, UUID> {
}
