package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.model.CustomerModel;
import lk.ijse.salongeetha.model.EmployeeModel;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.paint.Color.RED;

public class AddCustomerFormController {
    public AnchorPane popUpPane;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtNic;
    public JFXTextField txtPhone;
    public JFXDatePicker txtDOB;
    public static String userName;
    public Label lblNameValidation;
    public Label lblEmailValidation;
    public Label lblPhoneValidation;
    public Label lblNICValidation;
    private boolean reload = false;

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String nic = txtNic.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String dob = String.valueOf(txtDOB.getValue());
        Customer customer = new Customer(nic, name, phone, email, dob, userName);

        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.NIC, nic)) {
                if (ValidityCheck.check(Validation.PHONE, phone)) {
                    if (ValidityCheck.check(Validation.EMAIL, email)) {
                        addCustomer(customer);
                        closeCustomerAddForm(actionEvent);
                    } else {
                        txtEmail.requestFocus();
                        txtEmail.setFocusColor(RED);
                        lblEmailValidation.setText("Invalid email");
                    }
                } else {
                    txtPhone.requestFocus();
                    txtPhone.setFocusColor(RED);
                    lblPhoneValidation.setText("Invalid number");
                }

            } else {
                txtNic.requestFocus();
                txtNic.setFocusColor(RED);
                lblNICValidation.setText("Invalid NIC");
            }
        } else {
            txtName.requestFocus();
            txtName.setFocusColor(RED);
            lblNameValidation.setText("Invalid name");
        }


    }

    private void addCustomer(Customer customer) {
        try {
            boolean addCustomer = CustomerModel.addCustomer(customer);
            if (addCustomer) {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save is successful", ok);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {

                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        closeCustomerAddForm(actionEvent);
    }

    public void initialize() {
        popUpPane.setVisible(true);

    }

    private void closeCustomerAddForm(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        this.reload = true;
    }
}
