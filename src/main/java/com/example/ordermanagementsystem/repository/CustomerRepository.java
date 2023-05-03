package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<DomainCustomer, UUID> {

    @Query("select c from DomainCustomer c " +
            "left join fetch c.customerOrders co " +
            "left join fetch co.orderLines " +
            "where c.id = :id")
    DomainCustomer getIncludeOrderThenIncludeOrderLine(@Param("id") UUID id);
}
