package lk.ijse.gdse68.aad.posbackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}
