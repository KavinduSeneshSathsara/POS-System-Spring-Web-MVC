package lk.ijse.gdse68.aad.posbackend.service;

import lk.ijse.gdse68.aad.posbackend.dto.OrderDto;

public interface OrderService {
    String saveOrder(OrderDto orderDto);
}
