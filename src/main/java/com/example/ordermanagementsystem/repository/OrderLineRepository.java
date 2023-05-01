package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<DomainOrderLine, UUID> {

    @Query("select ol from DomainOrderLine ol " +
            "left join fetch ol.order " +
            "where ol.id = :id")
    DomainOrderLine getByIdIncludeOrder(@Param("id") UUID id);

    @Query("select ol from DomainOrderLine ol " +
            "where ol.id in (:orderIds)")
    List<DomainOrderLine> getOrderLinesByOrder(@Param("orderIds") List<UUID> orderIds);
}
