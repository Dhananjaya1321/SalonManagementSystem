package lk.ijse.salongeetha.entity;

public class EmployeeServiceDetail {
    private String empId;
    private String sevId;

    public EmployeeServiceDetail() {
    }

    public EmployeeServiceDetail(String empId, String sevId) {
        this.empId = empId;
        this.sevId = sevId;
    }

    @Override
    public String toString() {
        return "EmployeeServiceDetail{" +
                "empId='" + empId + '\'' +
                ", sevId='" + sevId + '\'' +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }
}
