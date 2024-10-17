package lk.ijse.gdse68.aad.posbackend.dao;

import lk.ijse.gdse68.aad.posbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDao extends JpaRepository<Order, String> {
}
