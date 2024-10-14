package lk.ijse.gdse68.aad.posbackend.dto;

import lk.ijse.gdse68.aad.posbackend.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto implements SuperDto, ItemResponse {
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;
}
