package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import com.example.ordermanagementsystem.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderLineService {

    private OrderLineRepository repo;

    public DomainOrderLine getOrderLine(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    public DomainOrderLine setQuantity(UUID id, int quantity) {
        val order = repo.getByIdIncludeOrder(id);
        if (order.getOrder().getSubmittedDate() != null) {
            throw new IllegalStateException("Cannot modify order that has been submitted!");
        }
        order.setQuantity(quantity);
        return this.save(order);
    }

    public DomainOrderLine save(DomainOrderLine orderLine) {
        return repo.save(orderLine);
    }
}
