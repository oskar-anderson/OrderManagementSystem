package com.example.ordermanagementsystem.service;

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
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;
    private final OrderLineRepository repoOrderLine;

    private final SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public DomainOrder save(DomainOrder item) {
        repoOrderLine.saveAll(item.getOrderLines());
        val savedOrder = repo.save(item);
        return repo.findByIdIncludeOrderLines(savedOrder.getId());
    }

    public DomainOrder get(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    public List<DomainOrder> getAll() {
        return repo.findAll();
    }

    public List<DomainOrder> getAllBetweenIncludeOrderLines(OffsetDateTime date1, OffsetDateTime date2) {
        return repo.findAllBetweenIncludeOrderLines(date1, date2);
    }
}
