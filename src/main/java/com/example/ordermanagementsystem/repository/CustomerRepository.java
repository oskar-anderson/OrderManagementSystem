package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<DomainCustomer, UUID> {
}
