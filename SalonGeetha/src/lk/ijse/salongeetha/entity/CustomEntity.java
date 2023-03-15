package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomEntity {
    private String userName;
    private String eid;
    private String password;
    private String supId;
    private String description;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String aptId;
    private String sevId;
    private double price;
    private double discount;
    private String rntId;
    private int avaliableCount;
    private double pricePreDay;
    private String proId;
    private int qty;
    private String category;
    private String brand;
    private double unitPrice;
    private int qtyOnHand;
    private String empId;
    private String dob;
    private String nic;
    private String jobTitle;
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    private String pay_Id;
    private String payment_method;
//    private String nic;
    private double amount_due;
    private String bok_Id;
    private String date;
    private String status;
    private String bilId;
    private String title;
    private double amountPaid;
    private String apt_Id;
    private String time;
    private String Status;
    private String sevName;
    private String empName;

    public CustomEntity(String empId, String sevId, String empName, String sevName) {
        this.empId = empId;
        this.sevId = sevId;
        this.empName = empName;
        this.sevName = sevName;
    }

    public CustomEntity(int qty, int forHowManyDays, double discount, double price) {
        this.qty=qty;
        this.forHowManyDays=forHowManyDays;
        this.price=price;
        this.discount=discount;
    }
    public CustomEntity(double price, double discount) {
        this.price=price;
        this.discount=discount;
    }
    public CustomEntity(int avaliableCount, String date) {
        this.avaliableCount=avaliableCount;
        this.date=date;
    }

    public CustomEntity(String proId, String sevId, int qty, String name) {
        this.proId=proId;
        this.sevId=sevId;
        this.qty=qty;
        this.name=name;
    }
}
