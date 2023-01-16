package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.AddAdminDetailsBO;
import lk.ijse.salongeetha.to.EmployeeDTO;
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
    AddAdminDetailsBO adminDetailsBO = (AddAdminDetailsBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.ADD_ADMIN);

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        LocalDate dob = txtDOB.getValue();
        String phoneNumber = txtPhoneNumber.getText();

        if (ValidityCheck.check(Validation.PHONE, phoneNumber)) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setDob(String.valueOf(dob));
            employeeDTO.setDescription(description);
            employeeDTO.setPhoneNumber(phoneNumber);
            employeeDTO.setAddress(address);
            try {
                boolean updateAdmin = adminDetailsBO.updateAdmin(employeeDTO);
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
