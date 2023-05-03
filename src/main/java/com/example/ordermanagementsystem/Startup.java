package com.example.ordermanagementsystem;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import com.example.ordermanagementsystem.repository.OrderLineRepository;
import com.example.ordermanagementsystem.repository.OrderRepository;
import com.example.ordermanagementsystem.repository.ProductRepository;
import com.example.ordermanagementsystem.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.val;
import org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent>  {
    private static final Logger log = LoggerFactory.getLogger(Startup.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private final Environment env;

    private final ResourceLoader resourceLoader;

    private final CustomerRepository customerRepository;

    private final OrderLineRepository orderLineRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public Startup(
            @Autowired Environment env,
            @Autowired ResourceLoader resourceLoader,
            @Autowired CustomerRepository customerRepository,
            @Autowired OrderLineRepository orderLineRepository,
            @Autowired OrderRepository orderRepository,
            @Autowired ProductRepository productRepository
    ) {
        this.env = env;
        this.resourceLoader = resourceLoader;
        this.customerRepository = customerRepository;
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @SneakyThrows
    @Transactional
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        val willSeedData = env.getProperty("MyApp.AppDataInitialization.SeedData", Boolean.class, false);
        log.info("SeedData: " + willSeedData);
        log.info("Working Directory = " + System.getProperty("user.dir"));
        if (willSeedData) {
            seedData(true);
        }
    }

    public void seedData(boolean willLog) throws IOException {
        List<CSVRecord> csvCustomerRecords;
        try (final CSVParser p = CSVParser.parse(
                resourceLoader.getResource("classpath:data/customer.csv").getFile(),
                StandardCharsets.UTF_8,
                CSVFormat.RFC4180)) {
            csvCustomerRecords = p.getRecords();
        }
        List<DomainCustomer> customers = csvCustomerRecords.stream()
                .skip(1)
                .map(record ->
                        DomainCustomer.builder()
                                .id(UUID.fromString(record.get(0)))
                                .registrationCode(record.get(1))
                                .fullName(record.get(2))
                                .email(record.get(3))
                                .phoneNumber(record.get(4))
                                .build())
                .toList();

        if (willLog) log.info(Json.Serialize(customers));
        customerRepository.saveAll(customers);
        if (willLog) log.info("customers added");

        final List<CSVRecord> csvProductRecords;
        try (final CSVParser p = CSVParser.parse(
                resourceLoader.getResource("classpath:data/product.csv").getFile(),
                StandardCharsets.UTF_8,
                CSVFormat.RFC4180)) {
            csvProductRecords = p.getRecords();
        }
        List<DomainProduct> products = csvProductRecords.stream()
                .skip(1)
                .map(record -> DomainProduct.builder()
                        .id(UUID.fromString(record.get(0)))
                        .name(record.get(1))
                        .skuCode(record.get(2))
                        .unitPrice(Float.parseFloat(record.get(3)))
                        .startDate(OffsetDateTime.parse(record.get(4)))
                        .endDate(OffsetDateTime.parse(record.get(5)))
                        .build()
                )
                .toList();

        if (willLog) log.info(Json.Serialize(products));
        productRepository.saveAll(products);
        if (willLog) log.info("products added");


        final List<CSVRecord> csvOrderRecords;
        try (final CSVParser p = CSVParser.parse(
                resourceLoader.getResource("classpath:data/order.csv").getFile(),
                StandardCharsets.UTF_8,
                CSVFormat.RFC4180)) {
            csvOrderRecords = p.getRecords();
        }
        List<DomainOrder> orders = csvOrderRecords.stream()
                .skip(1)
                .map(record -> DomainOrder.builder()
                        .id(UUID.fromString(record.get(0)))
                        .submittedDate(Objects.equals(record.get(1), "") ? null : OffsetDateTime.parse(record.get(1)))
                        .customerId(Objects.equals(record.get(2), "") ? null : UUID.fromString(record.get(2)))
                        .build())
                .toList();

        if (willLog) log.info(Json.Serialize(orders));
        orderRepository.saveAll(orders);
        if (willLog) log.info("orders added");

        final List<CSVRecord> csvOrderLineRecords;
        try (final CSVParser p = CSVParser.parse(
                resourceLoader.getResource("classpath:data/orderLine.csv").getFile(),
                StandardCharsets.UTF_8,
                CSVFormat.RFC4180)) {
            csvOrderLineRecords = p.getRecords();
        }
        List<DomainOrderLine> orderLines = csvOrderLineRecords.stream()
                .skip(1)
                .map(record -> DomainOrderLine.builder()
                        .id(UUID.fromString(record.get(0)))
                        .quantity(Integer.parseInt(record.get(1)))
                        .productUnitPrice(Float.parseFloat(record.get(2)))
                        .productSKU(record.get(3))
                        .orderId(UUID.fromString(record.get(4)))
                        .productId(UUID.fromString(record.get(5)))
                        .build()
                )
                .toList();

        if (willLog) log.info(Json.Serialize(orderLines));
        orderLineRepository.saveAll(orderLines);
        if (willLog) log.info("order lines added");

        if (willLog) log.info("Seed data will be committed now!");
    }
}
