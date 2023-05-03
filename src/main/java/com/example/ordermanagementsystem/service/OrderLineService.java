package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import com.example.ordermanagementsystem.repository.OrderLineRepository;
import com.example.ordermanagementsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderLineService {

    private final OrderLineRepository repo;
    private final ProductService productService;

    public DomainOrderLine getOrderLine(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Transactional
    public DomainOrderLine setQuantity(UUID id, int quantity) {
        log.info("setQuantity");
        val order = repo.getByIdIncludeOrder(id);
        if (order.getOrder().getSubmittedDate() != null) {
            throw new IllegalStateException("Cannot modify order that has been submitted!");
        }
        order.setQuantity(quantity);
        return this.save(order);
    }

    @Transactional
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Transactional
    public DomainOrderLine save(DomainOrderLine orderLine) {
        log.info("save orderline");
        if (! productService.get(orderLine.getProductId()).getEndDate().isAfter(OffsetDateTime.now())) {
            throw new IllegalStateException("This product is not for sale anymore!");
        }
        return repo.save(orderLine);
    }
}
