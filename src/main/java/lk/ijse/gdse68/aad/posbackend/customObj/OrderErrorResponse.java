package lk.ijse.gdse68.aad.posbackend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderErrorResponse {
    private int errorCode;
    private String errorMessage;
}
