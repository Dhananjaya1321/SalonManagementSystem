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
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.EmployeeDAO;
import lk.ijse.salongeetha.model.castom.UserDAO;
import lk.ijse.salongeetha.model.castom.impl.EmployeeModel;
import lk.ijse.salongeetha.model.castom.impl.UserModel;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.tm.EmployeeTM;
import lk.ijse.salongeetha.to.User;
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


    private String setJobTitel;
    ArrayList<Employee> employeeArrayList;
    UserDAO userDAO = new UserModel();
    EmployeeDAO employeeDAO=new EmployeeModel();
    {
        try {
            employeeArrayList = getAllEmployee();

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

        this.employee = new Employee(empId, name, address, dob, phoneNumber, description, email, nic, setJobTitel);

        User user = new User(userNameText, empId, password);


        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.PHONE, phoneNumber)) {
                if (ValidityCheck.check(Validation.EMAIL, email)) {
                    if (ValidityCheck.check(Validation.NIC, nic)) {
                        if (jobTitel.equals("Receptionist")) {
                            if (ValidityCheck.check(Validation.PASSWORD, password)) {
                                if (password.equals(rePassword)) {
                                    addReceptionist(user);
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


    private void addReceptionist(User user) {
        try {
            boolean addReceptionist = employeeDAO.addReceptionist(this.employee, user);
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
            boolean addEmployee = employeeDAO.add(this.employee);
            if (addEmployee) {
                setNextId();
                Alert alert = new Alert(Alert.AlertType.WARNING, "Employee added successful");
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
        this.employee = new Employee(empId, name, address, dob, phoneNumber, description, email, nic, setJobTitel);
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
            boolean isUpdated = employeeDAO.update(employee);
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
            employeeArrayList = getAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Employee emp : employeeArrayList) {
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
                    Employee employee = new Employee();
                    if (!tm.getJobTitle().equals("Receptionist")) {

                        employee.setEmpId(employeeId);
                        try {
                            boolean isDeleted = employeeDAO.delete(employee);
                            if (isDeleted) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Employee delete successfully");
                                alert1.show();
                                setNextId();

                            }

                        } catch (SQLException | ClassNotFoundException exception) {
                            throw new RuntimeException(exception);
                        }
                    } else {

                        String empId = tm.getEmpId();
                        User user = new User();
                        user.setEid(empId);
                        employee.setEmpId(employeeId);
                        try {
                            boolean deleteUser = userDAO.delete(user, employee);
                            if (deleteUser) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Receptionist and user account  delete successfully");
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
            String currentId = employeeDAO.checkId();
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

    private Employee employee = new Employee();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            employee.setName(text);
            try {
                employeeArrayList = search(employee);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }

    public ArrayList<Employee> search(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employee.getName());
        ResultSet resultSet = employeeDAO.search(matcher.matches(),employee);
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployee.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployee.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployee.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployee.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployee.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployee.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployee.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employees.add(searchEmployee);
        }
        return employees;
    }
    public void cleanTable() {
        try {
            tblView.getItems().clear();
            employeeArrayList = getAllEmployee();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    private ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = employeeDAO.getAll();
        if (resultSet.next()) {
            do {
                Employee employee = new Employee();
                employee.setEmpId(String.valueOf(resultSet.getObject(1)));
                employee.setName(String.valueOf(resultSet.getObject(2)));
                employee.setAddress(String.valueOf(resultSet.getObject(3)));
                employee.setDob(String.valueOf(resultSet.getObject(4)));
                employee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
                employee.setDescription(String.valueOf(resultSet.getObject(6)));
                employee.setEmail(String.valueOf(resultSet.getObject(7)));
                employee.setNic(String.valueOf(resultSet.getObject(8)));
                employee.setJobTitle(String.valueOf(resultSet.getObject(9)));
                employees.add(employee);
            } while (resultSet.next());
            return employees;
        }
        return new ArrayList<>();
    }
}
