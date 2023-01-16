package lk.ijse.salongeetha.entity;

public class BookRentalsDetail {
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    private int qty;

    public BookRentalsDetail() {
    }

    public BookRentalsDetail(String rentId, String bokId, int forHowManyDays, int qty) {
        this.rentId = rentId;
        this.bokId = bokId;
        this.forHowManyDays = forHowManyDays;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "BookRentalsDetail{" +
                "rentId='" + rentId + '\'' +
                ", bokId='" + bokId + '\'' +
                ", forHowManyDays=" + forHowManyDays +
                ", qty=" + qty +
                '}';
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
}
