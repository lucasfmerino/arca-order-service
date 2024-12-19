package com.arca.order.modules.orderProduct.service;

import com.arca.order.client.ProductClient;
import com.arca.order.modules.order.domain.model.Order;
import com.arca.order.modules.order.exception.OrderNotFoundException;
import com.arca.order.modules.order.repository.OrderRepository;
import com.arca.order.modules.orderProduct.domain.dto.OrderProductDisplayDto;
import com.arca.order.modules.orderProduct.domain.dto.OrderProductRegistrationDto;
import com.arca.order.modules.orderProduct.domain.dto.ProductToUpdateClientDto;
import com.arca.order.modules.orderProduct.domain.model.OrderProduct;
import com.arca.order.modules.orderProduct.repository.OrderProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderProductService
{
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;


    /*
     *  CREATE ORDER PRODUCT
     *
     */
    public OrderProductDisplayDto create(OrderProductRegistrationDto orderProductRegistrationDto)
    {
        Optional<Order> orderOptional = orderRepository.findById(orderProductRegistrationDto.orderId());

        if(orderOptional.isPresent())
        {
            OrderProduct newOrderProduct = new OrderProduct();
            BeanUtils.copyProperties(orderProductRegistrationDto, newOrderProduct);
            newOrderProduct.setOrder(orderOptional.get());

            return new OrderProductDisplayDto(orderProductRepository.save(newOrderProduct));
        }
        else
        {
            throw new OrderNotFoundException("Order not found!");
        }
    }


    /*
     *  CREATE ORDER PRODUCTS
     *
     */
    public List<OrderProductDisplayDto> createByList(List<OrderProductRegistrationDto> registrationList)
    {
        List<OrderProductDisplayDto> newOrderProducts = new ArrayList<>();

        for(OrderProductRegistrationDto orderProductRegistrationDto : registrationList)
        {
            OrderProductDisplayDto newProduct = create(orderProductRegistrationDto);
            newOrderProducts.add(newProduct);
        }

        // UPDATE PRODUCT
        updateProductByOrder(registrationList);

        return newOrderProducts;
    }


    /*
     *  GET ORDER PRODUCTS
     *
     */
    public Page<OrderProductDisplayDto> findByOrderId(UUID orderId, Pageable pageable)
    {
        return orderProductRepository.findByOrderId(orderId, pageable).map(OrderProductDisplayDto::new);
    }


    /*
     *  UPDATE PRODUCT BY ORDER
     *
     */
    public void updateProductByOrder(List<OrderProductRegistrationDto> registrationList)
    {
        List<ProductToUpdateClientDto> updatedList = new ArrayList<>();

        for(OrderProductRegistrationDto product : registrationList)
        {
            updatedList.add(new ProductToUpdateClientDto(product));
        }

        productClient.updateProductByOrder(updatedList);
    }


}
