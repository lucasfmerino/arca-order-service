package com.arca.order.modules.orderProduct.controller;

import com.arca.order.modules.orderProduct.domain.dto.OrderProductDisplayDto;
import com.arca.order.modules.orderProduct.domain.dto.OrderProductRegistrationDto;
import com.arca.order.modules.orderProduct.service.OrderProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/order-products")
public class OrderProductController
{
    @Autowired
    private OrderProductService orderProductService;


    /*
     * REGISTER
     *
     */
    @PostMapping()
    public ResponseEntity<List<OrderProductDisplayDto>> registerByList(
            List<OrderProductRegistrationDto> registrationList,
            UriComponentsBuilder uriComponentsBuilder
    )
    {
        List<OrderProductDisplayDto> orderProducts = orderProductService.createByList(registrationList);
        URI uri = uriComponentsBuilder.path("/api/order-products/order/{orderId}")
                .buildAndExpand(registrationList.getFirst().orderId())
                .toUri();

        return ResponseEntity.created(uri).body(orderProducts);
    }


    /*
     * GET BY ORDER
     *
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Page<OrderProductDisplayDto>> getByOrderId(
            @PageableDefault(size = 10, page = 0, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable,
            @PathVariable UUID orderId
    )
    {
        return ResponseEntity.ok(orderProductService.findByOrderId(orderId, pageable));
    }
}
