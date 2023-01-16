package lk.ijse.salongeetha.entity;

public class ServiceAppointmentDetail {
    private String aptId;
    private String sevId;

    public ServiceAppointmentDetail() {
    }

    public ServiceAppointmentDetail(String aptId, String sevId) {
        this.aptId = aptId;
        this.sevId = sevId;
    }

    @Override
    public String toString() {
        return "ServiceAppointmentDetail{" +
                "aptId='" + aptId + '\'' +
                ", sevId='" + sevId + '\'' +
                '}';
    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }
}
