package com.project.order.service;

import com.project.order.dto.OrderRequest;
import com.project.order.entity.Orders;
import com.project.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        ordersRepository.save(order);
    }
}
