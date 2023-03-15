package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRentalsDetail {
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    private int qty;
}
