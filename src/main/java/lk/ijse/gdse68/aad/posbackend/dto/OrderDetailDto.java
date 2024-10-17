package lk.ijse.gdse68.aad.posbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double total;
}
