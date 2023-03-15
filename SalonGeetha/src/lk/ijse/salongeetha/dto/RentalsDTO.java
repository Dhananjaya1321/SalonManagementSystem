package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsDTO {
    private String rntId;
    private String name;
    private String description;
    private int avaliableCount;
    private double pricePreDay;
    private double discount;
}
