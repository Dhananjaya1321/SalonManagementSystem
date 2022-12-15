package lk.ijse.salongeetha.to;

public class EmployeeServiceDetail {
    private String empId;
    private String sevId;
    private String sevName;
    private String empName;

    public EmployeeServiceDetail() {
    }

    public EmployeeServiceDetail(String empId, String sevId, String sevName, String empName) {
        this.empId = empId;
        this.sevId = sevId;
        this.sevName = sevName;
        this.empName = empName;
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

    @Override
    public String toString() {
        return "EmployeeServiceDetail{" +
                "empId='" + empId + '\'' +
                ", sevId='" + sevId + '\'' +
                '}';
    }

    public String getSevName() {
        return sevName;
    }

    public void setSevName(String sevName) {
        this.sevName = sevName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
