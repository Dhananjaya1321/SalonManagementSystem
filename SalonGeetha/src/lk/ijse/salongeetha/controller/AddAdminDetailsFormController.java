package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.salongeetha.model.castom.EmployeeDAO;
import lk.ijse.salongeetha.model.castom.impl.EmployeeModel;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.SQLException;
import java.time.LocalDate;

import static javafx.scene.paint.Color.RED;

public class AddAdminDetailsFormController {

    public Label lblPhoneValidation;
    @FXML
    private AnchorPane popUpPane;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXDatePicker txtDOB;
    EmployeeDAO employeeDAO = new EmployeeModel();

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        LocalDate dob = txtDOB.getValue();
        String phoneNumber = txtPhoneNumber.getText();

        if (ValidityCheck.check(Validation.PHONE, phoneNumber)) {
            Employee employee = new Employee();
            employee.setDob(String.valueOf(dob));
            employee.setDescription(description);
            employee.setPhoneNumber(phoneNumber);
            employee.setAddress(address);
            try {
                boolean updateAdmin = employeeDAO.updateAdmin(employee);
                if (updateAdmin) {
                    popUpPane.setVisible(false);
                } else {
                    //error ekk pennanna
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            lblPhoneValidation.setText("Invalid number");
            txtPhoneNumber.setText(null);
            txtPhoneNumber.requestFocus();
            txtPhoneNumber.setFocusColor(RED);
        }
    }
}
