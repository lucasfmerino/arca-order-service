package com.arca.order.client;

import com.arca.order.modules.order.domain.dto.OrderDisplayDto;
import com.arca.order.modules.orderProduct.domain.dto.ProductToUpdateClientDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("product-service")  // Eureka application name
public interface ProductClient
{
    @RequestMapping(method = RequestMethod.PUT, value = "/api/products/order")
    void updateProductByOrder(@RequestBody @Valid List<ProductToUpdateClientDto> updatedProductList);
}