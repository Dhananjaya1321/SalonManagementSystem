package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private double price;
    private double discount;
    private int qty;
    private int forHowManyDays;
    private int avaliableCount;
    private String date;

    public CustomDTO(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }
    public CustomDTO(int avaliableCount, String date) {
        this.avaliableCount = avaliableCount;
        this.date = date;
    }

    public CustomDTO(int qty, int forHowManyDays, double price, double discount) {
        this.qty=qty;
        this.forHowManyDays=forHowManyDays;
        this.price = price;
        this.discount = discount;
    }
}
