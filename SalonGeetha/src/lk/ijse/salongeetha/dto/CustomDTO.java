package lk.ijse.salongeetha.dto;

public class CustomDTO {
    private double price;
    private double discount;
    private int qty;
    private int forHowManyDays;
    public CustomDTO(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    public CustomDTO() {
    }

    public CustomDTO(int qty, int forHowManyDays, double price, double discount) {
        this.qty=qty;
        this.forHowManyDays=forHowManyDays;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "price=" + price +
                ", discount=" + discount +
                ", qty=" + qty +
                ", forHowManyDays=" + forHowManyDays +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getForHowManyDays() {
        return forHowManyDays;
    }

    public void setForHowManyDays(int forHowManyDays) {
        this.forHowManyDays = forHowManyDays;
    }
}
