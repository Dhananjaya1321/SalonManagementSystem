package lk.ijse.salongeetha.dto;

public class EmployeeDTO {
    private String empId;
    private String name;
    private String address;
    private String dob;
    private String phoneNumber;
    private String description;
    private String email;
    private String nic;
    private String jobTitle;
    private String userName;

    public EmployeeDTO() {

    }

    public EmployeeDTO(String empId, String name, String address, String dob, String phoneNumber, String description,
                       String email, String nic, String jobTitle, String userName) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
        this.nic = nic;
        this.jobTitle = jobTitle;
        this.userName = userName;
    }

    public EmployeeDTO(String empId, String name, String address, String dob, String phoneNumber, String description,
                       String email, String nic, String jobTitle) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
        this.nic = nic;
        this.jobTitle = jobTitle;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", nic='" + nic + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
