package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductServiceDetailDTO {
    private String proId;
    private String sevId;
    private String name;
    private int qty;

}
