package lk.ijse.salongeetha.to;

public class BookDTO {
    private String bokId;
    private String date;
    private String time;
    private String nic;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public BookDTO(String bokId, String date, String nic) {
        this.bokId = bokId;
        this.date = date;
        this.nic = nic;
    }

    public BookDTO() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bokId='" + bokId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }

}
