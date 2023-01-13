package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.dao.castom.impl.LoginModel;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.paint.Color.RED;

public class CreateNewPasswordFormController {

    public AnchorPane alertPane;
    public JFXButton btnGoToLogin;
    public JFXButton btnChange;
    public Label lblPasswordVaildation;
    public Label lblConfirmPasswordVaildation;
    public Label lblPasswordVaildation1;
    @FXML
    private AnchorPane fullPane;

    @FXML
    private AnchorPane rigthPane;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtNewPassword;

    @FXML
    private AnchorPane leftPane;
    static String userName;
    LoginDAO loginDAO=new LoginModel();
     public static void passUserName(String userName){
        CreateNewPasswordFormController.userName =userName;
    }
    @FXML
    void btnBackLoginFormOnAction(ActionEvent event) throws IOException {
        Navigation("FrogotPasswordForm.fxml","fullPane");
    }

    @FXML
    void btnChangeOnAction(ActionEvent event) throws IOException {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        User user=new User();
        user.setPassword(newPassword);
        user.setUserName(userName);
        if(ValidityCheck.check(Validation.PASSWORD,newPassword)){
            if (newPassword.equals(confirmPassword)) {
                createNewPassword(user);
            }else{
                txtConfirmPassword.setText(null);
                txtConfirmPassword.requestFocus();
                txtConfirmPassword.setFocusColor(RED);
                lblConfirmPasswordVaildation.setText("Password is not match");
            }
        }else{
            lblPasswordVaildation.setText("Minimum eight characters,");
            lblPasswordVaildation1.setText("at least one letter and one number");
            txtNewPassword.setFocusColor(RED);
            txtNewPassword.requestFocus();
        }
    }
    private void createNewPassword(User user){
            try {
                boolean isChangePassword = loginDAO.ChangePassword(user);
                if (isChangePassword) {
                    btnChange.setVisible(false);
                    btnGoToLogin.setVisible(true);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }
    public void btnGoToLoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation("LoginForm.fxml","fullPane");
    }
    public void Navigation(String form,String paneType) throws IOException {
        Stage stage;
        if (paneType.equals("alert")) {
            stage=(Stage) alertPane.getScene().getWindow();
        }else {
            stage=(Stage) fullPane.getScene().getWindow();
        }

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/"+form))));
        stage.centerOnScreen();
    }
        public void initialize(){
            btnChange.setVisible(true);
            btnGoToLogin.setVisible(false);
        }


}
