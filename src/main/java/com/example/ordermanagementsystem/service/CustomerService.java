package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import com.example.ordermanagementsystem.repository.OrderLineRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;
    private final OrderLineRepository orderLineRepo;
    private final EntityManager em;

    public DomainCustomer save(DomainCustomer item) {
        return repo.save(item);
    }

    public DomainCustomer get(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public DomainCustomer getIncludeOrderThenIncludeOrderLine2(UUID id) {
        return em.createQuery("select c from DomainCustomer c " +
                "left join fetch c.customerOrders co " +
                "left join fetch co.orderLines " +
                "where c.id = :id", DomainCustomer.class
        ).setParameter("id", id).getSingleResult();
    }

    public DomainCustomer getIncludeOrderThenIncludeOrderLine(UUID id) {
        val result = em.createQuery("select c from DomainCustomer c " +
                "join fetch c.customerOrders co " +
                "where c.id = :id", DomainCustomer.class
        ).setParameter("id", id).getSingleResult();;
        val orderIds = result.getCustomerOrders().stream().map(DomainOrder::getId).toList();
        val orderLines = orderLineRepo.getOrderLinesByOrder(orderIds);
        result.getCustomerOrders().forEach(o -> o.setOrderLines(orderLines.stream().filter(ol -> ol.getOrderId().equals(o.getId())).collect(Collectors.toSet())));
        return result;
    }

    public List<DomainCustomer> getAll() {
        return repo.findAll();
    }
}
