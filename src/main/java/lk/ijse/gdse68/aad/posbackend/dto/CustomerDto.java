package lk.ijse.gdse68.aad.posbackend.dto;

import lk.ijse.gdse68.aad.posbackend.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements SuperDto, CustomerResponse {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}
