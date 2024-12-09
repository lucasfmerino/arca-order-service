package com.arca.order.modules.orderProduct.exception;

public class OrderProductNotFoundException extends RuntimeException
{
    public OrderProductNotFoundException(String message)
    {
        super(message);
    }
}
