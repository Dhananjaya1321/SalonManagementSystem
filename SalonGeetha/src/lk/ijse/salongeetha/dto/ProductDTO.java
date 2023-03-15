package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String proId;
    private String description;
    private String category;
    private String brand;
    private String supId;
    private double unitPrice;
    private int qtyOnHand;

}
