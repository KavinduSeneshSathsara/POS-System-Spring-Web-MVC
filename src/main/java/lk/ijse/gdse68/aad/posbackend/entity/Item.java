package lk.ijse.gdse68.aad.posbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
@Entity
public class Item {
    @Id
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;
}
