package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class EmployeeServiceTM {
    private String empId;
    private String empName;
    private String sevId;
    private String sevName;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public EmployeeServiceTM() {
    }

    public EmployeeServiceTM(String empId, String empName, String sevId, String sevName, JFXButton deleteButton) {
        this.empId = empId;
        this.empName = empName;
        this.sevId = sevId;
        this.sevName = sevName;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public String getSevName() {
        return sevName;
    }

    public void setSevName(String sevName) {
        this.sevName = sevName;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JFXButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JFXButton updateButton) {
        this.updateButton = updateButton;
    }
}
