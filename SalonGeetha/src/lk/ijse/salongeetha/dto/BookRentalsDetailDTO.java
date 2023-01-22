package lk.ijse.salongeetha.dto;

public class BookRentalsDetailDTO {
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    private int qty;
    private double price;
    private double discount;

    public BookRentalsDetailDTO(String rentId, String bokId, int forHowManyDays, int qty, double price, double discount) {
        this.rentId = rentId;
        this.bokId = bokId;
        this.forHowManyDays = forHowManyDays;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
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



   /* select bd.Qty,bd.For_how_many_days,r.Price_pre_day,r.Discount
    from book_rentals_detail bd join rentals r on bd.Rent_Id = r.Rent_Id;*/

    public BookRentalsDetailDTO() {
    }

    public BookRentalsDetailDTO(String rentId, String bokId, int forHowManyDays, int qty) {
        this.rentId = rentId;
        this.bokId = bokId;
        this.forHowManyDays = forHowManyDays;
        this.qty = qty;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    public int getForHowManyDays() {
        return forHowManyDays;
    }

    public void setForHowManyDays(int forHowManyDays) {
        this.forHowManyDays = forHowManyDays;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "BookRentalsDetailDTO{" +
                "rentId='" + rentId + '\'' +
                ", bokId='" + bokId + '\'' +
                ", forHowManyDays=" + forHowManyDays +
                ", qty=" + qty +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
