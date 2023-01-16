package lk.ijse.salongeetha.entity;

public class Book {
    private String bokId;
    private String date;
    private String nic;
    private String status;

    public Book() {
    }

    public Book(String bokId, String date, String nic, String status) {
        this.bokId = bokId;
        this.date = date;
        this.nic = nic;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bokId='" + bokId + '\'' +
                ", date='" + date + '\'' +
                ", nic='" + nic + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
