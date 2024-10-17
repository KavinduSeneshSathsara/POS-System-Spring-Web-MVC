package lk.ijse.gdse68.aad.posbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name ="orders")
@Entity
public class Order implements SuperEntity{
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;
    private LocalDateTime orderTimeDate;
    private double total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
