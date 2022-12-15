package lk.ijse.salongeetha.to;

public class Appointment {
    private String aptId;
    private String date;
    private String time;
    private String nic;
    private String Status;

    public Appointment() {
    }

    public Appointment(String aptId, String date, String time, String nic) {
        this.aptId = aptId;
        this.date = date;
        this.time = time;
        this.nic = nic;
    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
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
        return "Appointment{" +
                "aptId='" + aptId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
