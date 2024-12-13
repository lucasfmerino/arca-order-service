package com.arca.order.modules.order.service;

import com.arca.order.infra.security.TokenService;
import com.arca.order.modules.order.domain.dto.OrderDisplayDto;
import com.arca.order.modules.order.domain.dto.OrderDisplayToListDto;
import com.arca.order.modules.order.domain.dto.OrderUpdateDto;
import com.arca.order.modules.order.domain.model.Order;
import com.arca.order.modules.order.exception.OrderNotFoundException;
import com.arca.order.modules.order.repository.OrderRepository;
import com.arca.order.modules.orderProduct.domain.model.OrderProduct;
import com.arca.order.modules.orderStatus.OrderStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TokenService tokenService;


    public OrderService(OrderRepository orderRepository, TokenService tokenService)
    {
        this.orderRepository = orderRepository;
        this.tokenService = tokenService;
    }


    /*
     *  CREATE ORDER
     *
     */
    public OrderDisplayDto create(HttpServletRequest request)
    {
        Order newOrder = new Order();
        String token = tokenService.extractTokenFromHeader(request);
        UUID userId = UUID.fromString(tokenService.extractUserIdFromToken(token));
        List<OrderProduct> products = new ArrayList<>();

        newOrder.setUserId(userId);
        newOrder.setOrderStatus(OrderStatus.CREATED);
        newOrder.setOrderProducts(products);

        return new OrderDisplayDto(orderRepository.save(newOrder));
    }


    /*
     *  GET ALL ORDERS
     *
     */
    public Page<OrderDisplayToListDto> getAll(Pageable pageable)
    {
        return orderRepository.findAll(pageable).map(OrderDisplayToListDto::new);
    }


    /*
     *  GET USER ORDERS
     *
     */
    public Page<OrderDisplayToListDto> getByUserId(HttpServletRequest request, Pageable pageable)
    {
        String token = tokenService.extractTokenFromHeader(request);
        UUID userId = UUID.fromString(tokenService.extractUserIdFromToken(token));

        return orderRepository.findByUserId(userId, pageable).map(OrderDisplayToListDto::new);
    }


    /*
     *  GET ORDER BY ID
     *
     */
    public OrderDisplayDto getById(UUID id)
    {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if(orderOptional.isPresent())
        {
            return new OrderDisplayDto(orderOptional.get());
        }
        else
        {
            throw new OrderNotFoundException("Order not found!");
        }
    }


    /*
     *  UPDATE ORDER
     *
     */
    public OrderDisplayDto update(OrderUpdateDto orderUpdateDto)
    {
        Optional<Order> orderOptional = orderRepository.findById(orderUpdateDto.id());

        if(orderOptional.isEmpty())
        {
            throw new OrderNotFoundException("Order not found!");
        }

        Order orderToUpdate = orderOptional.get();

        if(orderUpdateDto.orderStatus() != null)
        {
            orderToUpdate.setOrderStatus(orderUpdateDto.orderStatus());
        }

        return new OrderDisplayDto(orderRepository.save(orderToUpdate));
    }

}
