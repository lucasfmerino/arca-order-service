package com.arca.order.modules.order.service;

import com.arca.order.modules.order.domain.dto.OrderDisplayDto;
import com.arca.order.modules.order.domain.dto.OrderRegistrationDto;
import com.arca.order.modules.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;


    /*
     *  CREATE ORDER
     *
     */
//    public OrderDisplayDto create(OrderRegistrationDto orderRegistrationDto)
//    {
//
//    }
}
