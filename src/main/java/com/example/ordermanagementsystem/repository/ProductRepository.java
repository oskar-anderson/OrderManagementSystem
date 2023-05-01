package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<DomainProduct, UUID> {

    @Query(value = "select p from DomainProduct p " +
            "left join fetch p.orderLines ol " +
            "left join fetch ol.order o " +
            "where p.id = :id")
    DomainProduct getProductOrderLines(@Param("id") UUID id);
}
