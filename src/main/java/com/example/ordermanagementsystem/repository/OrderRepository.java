package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<DomainOrder, UUID> {
    @Query(value = "" +
            "SELECT o FROM DomainOrder o " +
            "left join fetch o.orderLines " +
            "where o.id = :id")
    DomainOrder findByIdIncludeOrderLines(@Param("id") UUID id);

    @Query(value = "select o from DomainOrder o " +
            "left join fetch o.orderLines " +
            "where o.submittedDate >= :date1 and o.submittedDate < :date2")
    List<DomainOrder> findAllBetweenIncludeOrderLines(@Param("date1") OffsetDateTime date1, @Param("date2") OffsetDateTime date2);
}
