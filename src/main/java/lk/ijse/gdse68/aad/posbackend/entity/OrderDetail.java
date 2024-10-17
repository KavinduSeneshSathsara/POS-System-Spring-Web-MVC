package lk.ijse.gdse68.aad.posbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderDetails")
@Entity
public class OrderDetail implements SuperEntity{
    @Id
    private String orderDetailsId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private Item item;
    private int qty;
    private double unitPrice;
    private String description;
}
