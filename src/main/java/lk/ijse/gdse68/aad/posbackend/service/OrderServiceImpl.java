package lk.ijse.gdse68.aad.posbackend.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.posbackend.dao.ItemDao;
import lk.ijse.gdse68.aad.posbackend.dao.OrdersDao;
import lk.ijse.gdse68.aad.posbackend.dto.OrderDetailDto;
import lk.ijse.gdse68.aad.posbackend.dto.OrderDto;
import lk.ijse.gdse68.aad.posbackend.entity.Item;
import lk.ijse.gdse68.aad.posbackend.entity.Order;
import lk.ijse.gdse68.aad.posbackend.entity.OrderDetail;
import lk.ijse.gdse68.aad.posbackend.excexption.DataPersistFailedException;
import lk.ijse.gdse68.aad.posbackend.excexption.ItemNotFoundException;
import lk.ijse.gdse68.aad.posbackend.util.AppUtil;
import lk.ijse.gdse68.aad.posbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Autowired
    private final OrdersDao ordersDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private ItemDao itemDao;

    @Override
    public String saveOrder(OrderDto orderDto) {
        orderDto.setOrderId(AppUtil.createOrderId());
        orderDto.setOrderDateTime(AppUtil.getCurrentTime());
        orderDto.setTotal(orderDto.getOrderDetails().stream().mapToDouble(detail ->detail.getQty() * detail.getUnitPrice()).sum());

        Order orderEntity = mapping.convertToOrderEntity(orderDto);

        List<OrderDetail> orderDetailEntities = orderDto.getOrderDetails().stream().map(detail -> {

            OrderDetail orderDetailEntity = mapping.convertToOrderDetailEntity(detail);
            orderDetailEntity.setOrderDetailsId(AppUtil.createOrderDetailsId());
            orderDetailEntity.setDescription("Payed");
            orderDetailEntity.setOrder(orderEntity);
            return orderDetailEntity;
        })
        .collect(Collectors.toList());
        orderEntity.setOrderDetails(orderDetailEntities);
        boolean allItemsUpdated = orderDto.getOrderDetails().stream().allMatch(this::updateItemQty);

        if (allItemsUpdated) {
            ordersDao.save(orderEntity);
            return "Order placed successfully";
        } else {
            throw new DataPersistFailedException("place order failed");
        }
    }

    private boolean updateItemQty(OrderDetailDto orderDetailsDto) {
        Item item = itemDao.findById(orderDetailsDto.getItemCode()).orElse(null);
        if (item == null) {
            throw new ItemNotFoundException("Item not found");
        }

        if (item.getItemQty() < orderDetailsDto.getQty()) {
            throw new ItemNotFoundException("Item qty not enough");
        }

        item.setItemQty(item.getItemQty() - orderDetailsDto.getQty());
        itemDao.save(item);
        return true;
    }
}
