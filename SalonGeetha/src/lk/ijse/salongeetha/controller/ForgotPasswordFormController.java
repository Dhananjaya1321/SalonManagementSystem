package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.ForgotPasswordBO;
import lk.ijse.salongeetha.to.UserDTO;
import lk.ijse.salongeetha.util.GenerateOTP;
import lk.ijse.salongeetha.util.SendMail;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.paint.Color.RED;

public class ForgotPasswordFormController {

    public JFXTextField txtUserName;
    public Label lblFrogotPasswordForm;
    public JFXButton btnSend;
    public JFXButton btnContinue;
    public JFXButton btnBLF;
    public AnchorPane rigthPane;
    public Label lblUserNameValidation;
    public Label lblEmailValidation;
    public Label lblOTPValidation;
    @FXML
    private AnchorPane fullPane;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtOTPCode;

    private String otp;
    private String userName;
    ForgotPasswordBO forgotPasswordBO = (ForgotPasswordBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.FORGOT_PASSWORD);

    @FXML
    void btnSendEmailOnAction(ActionEvent event) {
        userName = txtUserName.getText();
        String email = txtEmail.getText();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        try {

            boolean isChecked = forgotPasswordBO.checkEmail(userDTO);
            if (isChecked) {
                if (email.equals(userDTO.getEmail())) {
                    otp = GenerateOTP.getOTP();
                    boolean isSend = SendMail.Authentication("Authentication", email, otp);
                    if (isSend) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email send successfully");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Email not sent.\n Please check your internet connection");
                        alert.show();
                    }
                } else {
                    txtEmail.setText(null);
                    txtEmail.requestFocus();
                    txtEmail.setFocusColor(RED);
                    lblEmailValidation.setText("Emails do not match. Enter valid email address");

                }
            } else {
                txtUserName.setText(null);
                txtUserName.requestFocus();
                txtUserName.setFocusColor(RED);
                lblUserNameValidation.setText("Invalid userName");

            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation("LoginForm.fxml");
    }

    public void btnContinueOnAction(ActionEvent actionEvent) throws IOException {
        String otp = txtOTPCode.getText();
        if (otp.equals(this.otp)) {
            CreateNewPasswordFormController.passUserName(this.userName);
            Navigation("CreateNewPasswordForm.fxml");
        } else {
            txtOTPCode.setText(null);
            txtOTPCode.setFocusColor(RED);
            txtOTPCode.requestFocus();
            lblOTPValidation.setText("OTP code is not match");
        }
    }

    public void Navigation(String form) throws IOException {
        Stage stage = (Stage) fullPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/" + form))));
        stage.centerOnScreen();
    }

    public void initialize() {
        lblEmailValidation.setText("");
    }

}
