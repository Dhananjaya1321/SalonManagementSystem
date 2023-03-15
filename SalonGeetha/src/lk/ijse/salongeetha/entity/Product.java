package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private String proId;
    private String description;
    private String category;
    private String brand;
    private String supId;
    private double unitPrice;
    private int qtyOnHand;
}

