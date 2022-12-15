package lk.ijse.salongeetha.to;

public class BillPayment {
    private String bilId;
    private String empId;
    private String date;
    private String description;
    private String title;
    private double amountPaid;

    public BillPayment() {
    }

    public BillPayment(String bilId, String empId, String date, String description, String title, double amountPaid) {
        this.bilId = bilId;
        this.empId = empId;
        this.date = date;
        this.description = description;
        this.title = title;
        this.amountPaid = amountPaid;
    }

    public String getBilId() {
        return bilId;
    }

    public void setBilId(String bilId) {
        this.bilId = bilId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "BillPayment{" +
                "bilId='" + bilId + '\'' +
                ", empId='" + empId + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
