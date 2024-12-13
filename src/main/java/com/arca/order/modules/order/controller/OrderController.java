package com.arca.order.modules.order.controller;

import com.arca.order.modules.order.domain.dto.OrderDisplayDto;
import com.arca.order.modules.order.domain.dto.OrderDisplayToListDto;
import com.arca.order.modules.order.domain.dto.OrderUpdateDto;
import com.arca.order.modules.order.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    /*
     *  REGISTER ORDER
     *
     */
    @PostMapping()
    @Transactional
    public ResponseEntity<OrderDisplayDto> register(UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        OrderDisplayDto newOrder = orderService.create(request);

        URI uri = uriBuilder.path("/api/orders/{id}").buildAndExpand(newOrder.id()).toUri();

        return ResponseEntity.created(uri).body(newOrder);
    }


    /*
     *  GET ORDERS
     *
     */
    @GetMapping()
    public ResponseEntity<Page<OrderDisplayToListDto>> getAll(
            @PageableDefault(size = 8, page = 0, sort = "description", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return ResponseEntity.ok(orderService.getAll(pageable));
    }


    /*
     *  GET USER ORDERS
     *
     */
    @GetMapping("/user")
    public ResponseEntity<Page<OrderDisplayToListDto>> getUserOrders(
            @PageableDefault(size = 8, page = 0, sort = "description", direction = Sort.Direction.ASC)
            Pageable pageable,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(orderService.getByUserId(request, pageable));
    }


    /*
     *  GET BY ID
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDisplayDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getById(id));
    }


    /*
     *  UPDATE
     *
     */
    @PutMapping()
    @Transactional
    public ResponseEntity<OrderDisplayDto> update(@RequestBody @Valid OrderUpdateDto orderUpdateDto)
    {
        return ResponseEntity.ok(orderService.update(orderUpdateDto));
    }
}
