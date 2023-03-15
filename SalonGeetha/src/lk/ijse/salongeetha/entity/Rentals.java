package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rentals {
    private String rntId;
    private String name;
    private String description;
    private int avaliableCount;
    private double pricePreDay;
    private double discount;
}
