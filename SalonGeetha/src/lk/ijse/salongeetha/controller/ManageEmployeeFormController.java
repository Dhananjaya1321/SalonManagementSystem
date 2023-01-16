package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.EmployeeBO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;
import lk.ijse.salongeetha.to.tm.EmployeeTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.RED;

public class ManageEmployeeFormController extends MainFormController {

    public JFXRadioButton rbntBeautician;
    public JFXRadioButton rbtnReceptionist;
    public JFXRadioButton rbtnCleaner;
    public JFXTextField txtEmployeeId;
    public JFXButton btnClean;
    public Label lblRePassword;
    public Label lblPassword;
    public Label lblUserName;
    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnManageEmployee;
    public JFXButton btnEmployeeService;
    public TextField txtSearch;
    public Label lblVName;
    public Label lblVAddress;
    public Label lblVPhone;
    public Label lblVEmail;
    public Label lblVDate;
    public Label lblVNic;
    public Label lblVDescription;
    public Label lblVUserName;
    public Label lblVPassword;
    public Label lblVRePassword;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<EmployeeTM> tblView;

    @FXML
    private TableColumn<?, ?> columenId;

    @FXML
    private TableColumn<?, ?> columenName;

    @FXML
    private TableColumn<?, ?> columenAddress;

    @FXML
    private TableColumn<?, ?> columenDOB;

    @FXML
    private TableColumn<?, ?> columenPhoneNumber;

    @FXML
    private TableColumn<?, ?> columenDescription;

    @FXML
    private TableColumn<?, ?> columenEmail;

    @FXML
    private TableColumn<?, ?> columenNIC;

    @FXML
    private TableColumn<?, ?> columenJobTitel;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblEmpId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtPhonenumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXDatePicker txtDOB;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private ToggleGroup jobTitel;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private JFXPasswordField txtPassword;
    EmployeeBO employeeBO = (EmployeeBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.EMPLOYEE);

    private String setJobTitel;
    ArrayList<EmployeeDTO> employeeDTOArrayList;

    {
        try {
            employeeDTOArrayList = getAllEmployee();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddONAction(ActionEvent event) {
        String empId = lblEmpId.getText();
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        String dob = String.valueOf(txtDOB.getValue());
        String email = txtEmail.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String phoneNumber = txtPhonenumber.getText();
        String userNameText = txtUserName.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();

        this.employeeDTO = new EmployeeDTO(empId, name, address, dob, phoneNumber, description, email, nic, setJobTitel);

        UserDTO userDTO = new UserDTO(userNameText, empId, password);


        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.PHONE, phoneNumber)) {
                if (ValidityCheck.check(Validation.EMAIL, email)) {
                    if (ValidityCheck.check(Validation.NIC, nic)) {
                        if (jobTitel.equals("Receptionist")) {
                            if (ValidityCheck.check(Validation.PASSWORD, password)) {
                                if (password.equals(rePassword)) {
                                    addReceptionist(userDTO);
                                } else {
                                    lblVRePassword.setText("password is not match");
                                    txtRePassword.requestFocus();
                                    txtRePassword.setFocusColor(RED);
                                }
                            } else {
                                lblVPassword.setText("Invalid password");
                                txtPassword.requestFocus();
                                txtPassword.setFocusColor(RED);
                            }

                        } else {
                            addEmployee();
                        }


                    } else {
                        lblVNic.setText("Invalid NIC");
                        txtNIC.requestFocus();
                        txtNIC.setFocusColor(RED);
                    }
                } else {
                    lblVEmail.setText("Invalid email");
                    txtEmail.requestFocus();
                    txtEmail.setFocusColor(RED);
                }
            } else {
                lblVPhone.setText("Invalid number");
                txtPhonenumber.requestFocus();
                txtPhonenumber.setFocusColor(RED);
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.requestFocus();
            txtName.setFocusColor(RED);
        }
    }


    private void addReceptionist(UserDTO userDTO) {
        try {
            boolean addReceptionist = employeeBO.addReceptionist(this.employeeDTO, userDTO);
            if (addReceptionist) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Receptionist added successful");
                alert.show();
                setNextId();
                cleanAll();
                loadAllData();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "error");
                alert.show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//

    }

    private void addEmployee() {
        try {
            boolean addEmployee = employeeBO.addEmployee(this.employeeDTO);
            if (addEmployee) {
                setNextId();
                Alert alert = new Alert(Alert.AlertType.WARNING, "EmployeeDTO added successful");
                alert.show();
                cleanAll();
                loadAllData();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public void btnUpdateONAction(ActionEvent actionEvent) {
        String empId = lblEmpId.getText();
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        String dob = String.valueOf(txtDOB.getValue());
        String email = txtEmail.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String phoneNumber = txtPhonenumber.getText();
        this.employeeDTO = new EmployeeDTO(empId, name, address, dob, phoneNumber, description, email, nic, setJobTitel);
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.PHONE, phoneNumber)) {
                if (ValidityCheck.check(Validation.EMAIL, email)) {
                    if (ValidityCheck.check(Validation.NIC, nic)) {
                        updateEmployee();
                    } else {
                        lblVNic.setText("Invalid NIC");
                        txtNIC.requestFocus();
                        txtNIC.setFocusColor(RED);
                    }
                } else {
                    lblVEmail.setText("Invalid email");
                    txtEmail.requestFocus();
                    txtEmail.setFocusColor(RED);
                }
            } else {
                lblVPhone.setText("Invalid number");
                txtPhonenumber.requestFocus();
                txtPhonenumber.setFocusColor(RED);
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.requestFocus();
            txtName.setFocusColor(RED);
        }
    }

    private void updateEmployee() {
        try {
            boolean isUpdated = employeeBO.updateEmployee(employeeDTO);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "update successful");
                alert.show();
                cleanAll();
                loadAllData();
                setNextId();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchONAction(ActionEvent actionEvent) {
        search();

    }

    public void btnCleanONAction(ActionEvent actionEvent) {
        cleanAll();
        loadAllData();
    }

    private void cleanAll() {
        txtSearch.setText("");
//        txtEmployeeId.setText("");
        txtAddress.setText("");
        txtDescription.setText("");
        txtDOB.setValue(null);
        txtEmail.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtPhonenumber.setText("");
        rbtnCleaner.setSelected(false);
        rbtnReceptionist.setSelected(false);
        rbntBeautician.setSelected(false);
        txtPassword.setVisible(false);
        txtUserName.setVisible(false);
        txtRePassword.setVisible(false);
        txtPassword.setText(null);
        txtRePassword.setText(null);
        txtUserName.setText(null);
        lblPassword.setVisible(false);
        lblRePassword.setVisible(false);
        lblUserName.setVisible(false);
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        tblView.getItems().clear();
        lblVPassword.setText(null);
        lblVRePassword.setText(null);
        lblVUserName.setText(null);
        lblVName.setText(null);
        lblVAddress.setText(null);
        lblVPhone.setText(null);
        lblVEmail.setText(null);
        lblVNic.setText(null);
        lblVDescription.setText(null);

        try {
            employeeDTOArrayList = getAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (EmployeeDTO emp : employeeDTOArrayList) {
            JFXButton delete = new JFXButton("Delete");
            JFXButton update = new JFXButton("Update");
            update.setStyle("-fx-background-color: linear-gradient(to right, #17ff00, #12fe18, #0bfc25, #05fb2e, #00f936);");
            delete.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
            delete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    EmployeeTM tm = tblView.getSelectionModel().getSelectedItem();

                    String employeeId = tm.getEmpId();
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    if (!tm.getJobTitle().equals("Receptionist")) {

                        employeeDTO.setEmpId(employeeId);
                        try {
                            boolean isDeleted = employeeBO.deleteEmployee(employeeDTO);
                            if (isDeleted) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "EmployeeDTO delete successfully");
                                alert1.show();
                                setNextId();

                            }

                        } catch (SQLException | ClassNotFoundException exception) {
                            throw new RuntimeException(exception);
                        }
                    } else {

                        String empId = tm.getEmpId();
                        UserDTO userDTO = new UserDTO();
                        userDTO.setEid(empId);
                        employeeDTO.setEmpId(employeeId);
                        try {
                            boolean deleteUser = employeeBO.deleteReceptionist(employeeDTO, userDTO);
                            if (deleteUser) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Receptionist and userDTO account  delete successfully");
                                alert1.show();
                                setNextId();

                            }
                        } catch (SQLException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    tblView.getItems().removeAll(tm);
                }
            });
            update.setOnAction((e) -> {
                EmployeeTM tm = tblView.getSelectionModel().getSelectedItem();
                btnUpdate.setVisible(true);
                btnAdd.setVisible(false);

                lblEmpId.setText(tm.getEmpId());
                txtAddress.setText(tm.getAddress());
                txtDescription.setText(tm.getDescription());
                txtDOB.setValue(LocalDate.parse(tm.getDob()));
                txtEmail.setText(tm.getEmail());
                txtName.setText(tm.getName());
                txtNIC.setText(tm.getNic());
                txtPhonenumber.setText(tm.getPhoneNumber());
                if (tm.getJobTitle().equals("Beautician")) {
                    rbntBeautician.setSelected(true);
                } else if (tm.getJobTitle().equals("Receptionist")) {
                    rbtnReceptionist.setSelected(true);
                } else {
                    rbtnCleaner.setSelected(true);
                }

            });


            EmployeeTM employeeTM = new EmployeeTM(emp.getEmpId(), emp.getName(), emp.getAddress(), emp.getDob(), emp.getPhoneNumber(), emp.getDescription(), emp.getEmail(), emp.getNic(), emp.getJobTitle(), delete, update);
            observableList.add(employeeTM);
            tblView.setItems(observableList);
        }
    }

    private void setNextId() {
        try {
            String currentId = employeeBO.checkIdEmployee();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.EMPLOYEE);
            lblEmpId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        columenId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columenAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columenDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        columenPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columenNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        columenJobTitel.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        loadAllData();
        btnUpdate.setVisible(false);

//        new Pulse(lblBar).play();
        setNextId();
        txtPassword.setVisible(false);
        txtUserName.setVisible(false);
        txtRePassword.setVisible(false);
        lblPassword.setVisible(false);
        lblRePassword.setVisible(false);
        lblUserName.setVisible(false);
    }

    public void rbntBeautician(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        txtUserName.setVisible(false);
        txtRePassword.setVisible(false);
        lblPassword.setVisible(false);
        lblRePassword.setVisible(false);
        lblUserName.setVisible(false);
        txtPassword.setText(null);
        txtRePassword.setText(null);
        txtUserName.setText(null);
        setJobTitel = rbntBeautician.getText();
    }

    public void rbtnReceptionist(MouseEvent mouseEvent) {
        txtPassword.setVisible(true);
        txtUserName.setVisible(true);
        txtRePassword.setVisible(true);
        lblPassword.setVisible(true);
        lblRePassword.setVisible(true);
        lblUserName.setVisible(true);
        setJobTitel = rbtnReceptionist.getText();
//        System.out.println(setJobTitel);
    }

    public void rbtnCleaner(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        txtUserName.setVisible(false);
        txtRePassword.setVisible(false);
        lblPassword.setVisible(false);
        lblRePassword.setVisible(false);
        lblUserName.setVisible(false);
        txtPassword.setText(null);
        txtRePassword.setText(null);
        txtUserName.setText(null);
        setJobTitel = rbtnCleaner.getText();
    }

    private EmployeeDTO employeeDTO = new EmployeeDTO();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            employeeDTO.setName(text);
            try {
                employeeDTOArrayList = search(employeeDTO);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }

    public ArrayList<EmployeeDTO> search(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employeeDTO.getName());
        ResultSet resultSet = employeeBO.searchEmployee(matcher.matches(), employeeDTO);
        while (resultSet.next()) {
            EmployeeDTO searchEmployeeDTO = new EmployeeDTO();
            searchEmployeeDTO.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployeeDTO.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployeeDTO.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployeeDTO.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployeeDTO.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployeeDTO.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployeeDTO.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployeeDTO.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployeeDTO.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employeeDTOS.add(searchEmployeeDTO);
        }
        return employeeDTOS;
    }

    public void cleanTable() {
        try {
            tblView.getItems().clear();
            employeeDTOArrayList = getAllEmployee();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    private ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ResultSet resultSet = employeeBO.getAllEmployee();
        if (resultSet.next()) {
            do {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmpId(String.valueOf(resultSet.getObject(1)));
                employeeDTO.setName(String.valueOf(resultSet.getObject(2)));
                employeeDTO.setAddress(String.valueOf(resultSet.getObject(3)));
                employeeDTO.setDob(String.valueOf(resultSet.getObject(4)));
                employeeDTO.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
                employeeDTO.setDescription(String.valueOf(resultSet.getObject(6)));
                employeeDTO.setEmail(String.valueOf(resultSet.getObject(7)));
                employeeDTO.setNic(String.valueOf(resultSet.getObject(8)));
                employeeDTO.setJobTitle(String.valueOf(resultSet.getObject(9)));
                employeeDTOS.add(employeeDTO);
            } while (resultSet.next());
            return employeeDTOS;
        }
        return new ArrayList<>();
    }
}
