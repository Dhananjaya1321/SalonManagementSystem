package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRentalsDetailDTO {
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    private int qty;
    private double price;
    private double discount;

    public BookRentalsDetailDTO(String rentId, String bokId, int forHowManyDays, int qty) {
        this.rentId = rentId;
        this.bokId = bokId;
        this.forHowManyDays = forHowManyDays;
        this.qty = qty;
    }
}
