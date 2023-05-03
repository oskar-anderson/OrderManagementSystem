package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDal.DalOrder;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import com.example.ordermanagementsystem.repository.OrderLineRepository;
import com.example.ordermanagementsystem.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository repo;
    private final OrderLineRepository repoOrderLine;

    @Transactional
    public DomainOrder save(DomainOrder item) {
        val orderLines = item.getOrderLines();
        item.setOrderLines(new HashSet<>());
        val savedOrder = repo.save(item);
        repoOrderLine.saveAll(orderLines);
        return repo.findByIdIncludeOrderLines(savedOrder.getId());
    }

    public DomainOrder get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public DomainOrder getIncludeOrderLines(UUID id) {
        return repo.findByIdIncludeOrderLines(id);
    }

    public List<DomainOrder> getAll() {
        return repo.findAll();
    }

    public List<DomainOrder> getAllBetweenIncludeOrderLines(OffsetDateTime date1, OffsetDateTime date2) {
        return repo.findAllBetweenIncludeOrderLines(date1, date2);
    }

    @Transactional
    public DomainOrder markSubmitted(UUID id, OffsetDateTime date) {
        val domainOrder = this.get(id);
        domainOrder.setSubmittedDate(date);
        return this.save(domainOrder);
    }
}
