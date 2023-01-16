package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.LoginFormBO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.paint.Color.RED;

public class LoginFormController {

    public Circle backgroundGirl1;
    public ImageView imgGirl;
    public Polygon backgroundGirl2;
    public Polygon backgroundGirl21;
    public Polygon backgroundGirl22;
    public ImageView imgGirl2;
    public JFXPasswordField txtRePasswordCA;

    public FontAwesomeIconView btnClose;
    public FontAwesomeIconView btnClose1;
    public Label lblNameValidation;
    public Label lblUserNameValidation;
    public Label lblRePasswordValidation;
    public Label lblPasswordValidationCA;
    public Label lblPasswordValidationLA;
    public Label lblUserNICValidation;
    public Label lblEmailValidation;
    public Label lblPasswordValidationCA1;
    public Label lblPasswordValidationLA1;
    @FXML
    private AnchorPane fullPane;

    @FXML
    private AnchorPane rigthPane;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtUserNameCA;

    @FXML
    private JFXPasswordField txtPasswordCA;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private JFXButton btnLoginAccount;

    @FXML
    private Label lblRegistrationForm;

    @FXML
    private Label lblLonin;

    @FXML
    private JFXTextField txtUserNameLA;

    @FXML
    private JFXPasswordField txtPasswordLA;

    @FXML
    private Button btnFrogotPassword;

    @FXML
    private AnchorPane leftPane;
    LoginFormBO loginFormBO= (LoginFormBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.LOGIN);

    @FXML
    void createAccountOnAction(ActionEvent event) {
        String nic = txtNIC.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String userName = txtUserNameCA.getText();
        String password = txtPasswordCA.getText();
        String rePassword = txtRePasswordCA.getText();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpId("E001");
        employeeDTO.setNic(nic);
        employeeDTO.setName(name);
        employeeDTO.setEmail(email);
        UserDTO userDTO = new UserDTO(userName, "E001", password);
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.USERNAME, userName)) {
                if (ValidityCheck.check(Validation.NIC, nic)) {
                    if (ValidityCheck.check(Validation.EMAIL, email)) {
                        if (ValidityCheck.check(Validation.PASSWORD, password)) {
                            if (rePassword.equals(password)) {
                                creatAccount(userDTO, employeeDTO);
                                lblUserNameValidation.setText(null);
                                btnClose.setVisible(false);
                                btnClose1.setVisible(true);
                            } else {
                                txtRePasswordCA.setFocusColor(RED);
                                txtRePasswordCA.requestFocus();
                                lblRePasswordValidation.setText("Password does not match");
                            }
                        } else {
                            txtPasswordCA.setFocusColor(RED);
                            txtPasswordCA.requestFocus();
                            lblPasswordValidationCA.setText("Minimum 8 characters, at least one upper case English letter,");
                            lblPasswordValidationCA1.setText(" one lower case English letter, one number and one special character");
                        }
                    } else {
                        txtEmail.setFocusColor(RED);
                        txtEmail.requestFocus();
                        lblEmailValidation.setText("Invalid email");
                    }
                } else {
                    txtNIC.setFocusColor(RED);
                    txtNIC.requestFocus();
                    lblUserNICValidation.setText("Invalid NIC");
                }
            } else {
                txtUserNameCA.setFocusColor(RED);
                txtUserNameCA.requestFocus();
                lblUserNameValidation.setText("Invalid userName");
            }
        } else {
            txtName.setFocusColor(RED);
            txtName.requestFocus();
            lblNameValidation.setText("Invalid name");
        }
    }


    private void creatAccount(UserDTO userDTO, EmployeeDTO employeeDTO) {
        try {
            boolean isAdded = loginFormBO.addAdminDetails(userDTO, employeeDTO);
            if (isAdded) {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.7));
                slide.setNode(leftPane);
                slide.setToX(506);
                slide.play();
                rigthPane.setTranslateX(-300);
                setPage(isAdded);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void loginOnACtion(ActionEvent event) throws IOException {
        setLogIn();
    }

    public void txtPasswordLA(ActionEvent actionEvent) throws IOException {
        setLogIn();
    }

    private void setLogIn() throws IOException {
        String userName = txtUserNameLA.getText();
        String password = txtPasswordLA.getText();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setPassword(password);
        try {
            boolean isSetUserAccount = loginFormBO.setUserAccount(userDTO);
            if (isSetUserAccount) {
                if (password.equals(userDTO.getPassword())) {
                    EmployeeDTO employeeDTOJobTitle = loginFormBO.getEmployeeJobTitle(userDTO);
                    String jobTitle = employeeDTOJobTitle.getJobTitle();
                    if (jobTitle.equals("Admin")) {
                        Stage stage = (Stage) fullPane.getScene().getWindow();
                        stage.resizableProperty().setValue(true);
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/MainForm.fxml"))));
                        stage.centerOnScreen();
                    } else if (jobTitle.equals("Receptionist")) {
                        AddCustomerFormController.userName = userName;
                        Stage stage = (Stage) fullPane.getScene().getWindow();
                        stage.resizableProperty().setValue(true);
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/ReceptionistHomeForm.fxml"))));
                        stage.centerOnScreen();
                    }
                } else {
                    txtPasswordLA.setText(null);
                    txtPasswordLA.setFocusColor(RED);
                    txtPasswordLA.requestFocus();
                    lblPasswordValidationLA.setText("incorrect password");
                }
            } else {
                txtUserNameLA.setText(null);
                txtUserNameLA.setFocusColor(RED);
                txtUserNameLA.requestFocus();
                lblUserNameValidation.setText("incorrect username");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void frogotPasswordOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) fullPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("/lk/ijse/salongeetha/view/FrogotPasswordForm.fxml"))));
        stage.centerOnScreen();
    }


    private void setPage(boolean isChecked) {
        lblRegistrationForm.setVisible(!isChecked);
        txtEmail.setVisible(!isChecked);
        txtName.setVisible(!isChecked);
        txtNIC.setVisible(!isChecked);
        txtPasswordCA.setVisible(!isChecked);
        txtUserNameCA.setVisible(!isChecked);
        btnCreateAccount.setVisible(!isChecked);
        imgGirl.setVisible(!isChecked);
        backgroundGirl1.setVisible(!isChecked);
        txtRePasswordCA.setVisible(!isChecked);
        lblPasswordValidationCA.setVisible(!isChecked);
        lblRePasswordValidation.setVisible(!isChecked);
        lblPasswordValidationCA1.setVisible(!isChecked);
        lblEmailValidation.setVisible(!isChecked);
        lblUserNICValidation.setVisible(!isChecked);
//        lblUserNameValidation.setVisible();
        lblNameValidation.setVisible(!isChecked);

        lblPasswordValidationLA.setVisible(isChecked);
        imgGirl2.setVisible(isChecked);
        backgroundGirl2.setVisible(isChecked);
        backgroundGirl21.setVisible(isChecked);
        backgroundGirl22.setVisible(isChecked);
        btnLoginAccount.setVisible(isChecked);
        lblLonin.setVisible(isChecked);
        btnFrogotPassword.setVisible(isChecked);
        txtPasswordLA.setVisible(isChecked);
        txtUserNameLA.setVisible(isChecked);
    }


    public void initialize() {
        try {
            boolean isChecked = loginFormBO.checkUserAccount();
            if (isChecked) {
                setPage(isChecked);
            } else {
                setPage(isChecked);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
