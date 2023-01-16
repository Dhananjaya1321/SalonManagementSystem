package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.BillBO;
import lk.ijse.salongeetha.to.BillPaymentDTO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.tm.BillPaymentTM;
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

public class ManageBillPaymentController {

    public JFXTextField txtBillId;

    public JFXButton btnClean;
    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public TextField txtSearch;
    public Label lblVTitle;
    public Label lblVAmount;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<BillPaymentTM> tblView;

    @FXML
    private TableColumn<?, ?> columenBillId;

    @FXML
    private TableColumn<?, ?> columenEmployeeId;

    @FXML
    private TableColumn<?, ?> columenDate;

    @FXML
    private TableColumn<?, ?> columenTitel;

    @FXML
    private TableColumn<?, ?> columenAmountPaid;

    @FXML
    private TableColumn<?, ?> columenDescription;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblBillId;

    @FXML
    private JFXComboBox<String> cmbSelectEmployeeId;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextField txtTitel;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextArea txtDescription;

    ArrayList<BillPaymentDTO> billPaymentDTOArrayList;
    BillBO billBO= (BillBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.BILL);

    {
        try {
            billPaymentDTOArrayList = getAllBillPayment();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<BillPaymentTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (BillPaymentDTO b : billPaymentDTOArrayList) {
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
                    BillPaymentTM tm = tblView.getSelectionModel().getSelectedItem();

                    String billPaymentId = tm.getBilId();
                    BillPaymentDTO billPaymentDTO = new BillPaymentDTO();

                    try {
                        billPaymentDTO.setBilId(billPaymentId);
                        boolean isDeleted = billBO.deleteBillPayment(billPaymentDTO);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Bill PaymentDTO delete successfully");
                            alert1.show();
                            setNextId();

                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }


                    tblView.getItems().removeAll(tm);
                }
            });
            update.setOnAction((e) -> {
                BillPaymentTM tm = tblView.getSelectionModel().getSelectedItem();
                btnUpdate.setVisible(true);
                btnAdd.setVisible(false);
                lblBillId.setText(tm.getBilId());
                cmbSelectEmployeeId.setValue(tm.getEmpId());
                txtDate.setValue(LocalDate.parse(tm.getDate()));
                txtTitel.setText(tm.getTitle());
                txtAmount.setText(String.valueOf(tm.getAmountPaid()));
                txtDescription.setText(tm.getDescription());

            });
            BillPaymentTM billPaymentTM = new BillPaymentTM(b.getBilId(), b.getEmpId(), b.getDate(), b.getDescription(), b.getTitle(), b.getAmountPaid(), delete, update);
            observableList.add(billPaymentTM);
            tblView.setItems(observableList);
        }
    }

    @FXML
    void btnAddONAction(ActionEvent event) {
        String date = String.valueOf(txtDate.getValue());
        double amount = Double.parseDouble(txtAmount.getText());
        String description = txtDescription.getText();
        String titel = txtTitel.getText();
        String billId = lblBillId.getText();
        String employeeId = String.valueOf(cmbSelectEmployeeId.getValue());
        if (ValidityCheck.check(Validation.NAME, titel)) {
            if (amount >= 0) {
                BillPaymentDTO billPaymentDTO = new BillPaymentDTO(billId, employeeId, date, description, titel, amount);
                try {
                    boolean isAdded = billBO.addBillPayment(billPaymentDTO);
                    if (isAdded) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "added successfully");
                        alert.show();
                        cleanAll();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error");
                        alert.show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                txtAmount.setFocusColor(RED);
                txtAmount.requestFocus();
                lblVAmount.setText("Invalid Amount");
            }
        } else {
            txtTitel.setFocusColor(RED);
            txtTitel.requestFocus();
            lblVTitle.setText("Invalid title");

        }

    }


    public void btnUpdateONAction(ActionEvent actionEvent) {
        String date = String.valueOf(txtDate.getValue());
        double amount = Double.parseDouble(txtAmount.getText());
        String description = txtDescription.getText();
        String titel = txtTitel.getText();
        String billId = lblBillId.getText();
        String employeeId = String.valueOf(cmbSelectEmployeeId.getValue());
        if (ValidityCheck.check(Validation.NAME, titel)) {
            if (amount >= 0) {
                BillPaymentDTO billPaymentDTO = new BillPaymentDTO(billId, employeeId, date, description, titel, amount);
                try {
                    boolean updateBillPayment = billBO.updateBillPayment(billPaymentDTO);
                    if (updateBillPayment) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Update successfully");
                        alert.show();
                        cleanAll();
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                txtAmount.setFocusColor(RED);
                txtAmount.requestFocus();
                lblVAmount.setText("Invalid Amount");
            }
        } else {
            txtTitel.setFocusColor(RED);
            txtTitel.requestFocus();
            lblVTitle.setText("Invalid title");

        }

    }

    public void btnSearchONAction(ActionEvent actionEvent) {

    }

    public void btnCleanONAction(ActionEvent actionEvent) {
        cleanAll();
    }

    private void cleanAll() {
        cmbSelectEmployeeId.setValue("");
        txtDescription.setText("");
        txtAmount.setText("");
        txtTitel.setText("");
        txtSearch.setText("");
        txtDate.setValue(null);
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        lblVAmount.setText(null);
        lblVTitle.setText(null);
        setNextId();
        try {
            tblView.getItems().clear();
            billPaymentDTOArrayList = getAllBillPayment();
            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextId() {
        try {
            String currentId = billBO.checkIdBillPayment();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.BILL);
            lblBillId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        columenBillId.setCellValueFactory(new PropertyValueFactory<>("bilId"));
        columenEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenTitel.setCellValueFactory(new PropertyValueFactory<>("title"));
        columenAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

        cleanAll();
        btnUpdate.setVisible(false);
//        new Pulse(lblBar).play();


        try {
            ArrayList<EmployeeDTO> employeesIds = getAllEmployee();
            String[] ids;
            if (employeesIds.size() != 0) {
                ids = new String[employeesIds.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(employeesIds.get(i).getEmpId());
                }
                cmbSelectEmployeeId.getItems().addAll(ids);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    BillPaymentDTO billPaymentDTO = new BillPaymentDTO();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            billPaymentDTO.setTitle(text);
            try {
                billPaymentDTOArrayList = search(billPaymentDTO);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }
    public ArrayList<BillPaymentDTO> search(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        ArrayList<BillPaymentDTO> billPaymentDTOS = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(billPaymentDTO.getTitle());
        ResultSet resultSet = billBO.searchBillPayment(matcher.matches(), billPaymentDTO);
        while (resultSet.next()) {
            BillPaymentDTO searchBillPaymentDTO = new BillPaymentDTO();
            searchBillPaymentDTO.setBilId(String.valueOf(resultSet.getObject(1)));
            searchBillPaymentDTO.setDate(String.valueOf(resultSet.getObject(2)));
            searchBillPaymentDTO.setDescription(String.valueOf(resultSet.getObject(3)));
            searchBillPaymentDTO.setTitle(String.valueOf(resultSet.getObject(4)));
            searchBillPaymentDTO.setAmountPaid((Double) resultSet.getObject(5));
            searchBillPaymentDTO.setEmpId(String.valueOf(resultSet.getObject(6)));
            billPaymentDTOS.add(searchBillPaymentDTO);
        }
        return billPaymentDTOS;
    }

    public void cleanTable() {

        try {
            tblView.getItems().clear();
            billPaymentDTOArrayList = getAllBillPayment();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }
    private ArrayList<BillPaymentDTO> getAllBillPayment() throws SQLException, ClassNotFoundException {
        ArrayList<BillPaymentDTO> billPaymentDTOS = new ArrayList<>();
        ResultSet resultSet = billBO.getAllBillPayment();
        while (resultSet.next()) {
            BillPaymentDTO billPaymentDTO = new BillPaymentDTO();
            billPaymentDTO.setBilId(String.valueOf(resultSet.getObject(1)));
            billPaymentDTO.setDate(String.valueOf(resultSet.getObject(2)));
            billPaymentDTO.setDescription(String.valueOf(resultSet.getObject(3)));
            billPaymentDTO.setTitle(String.valueOf(resultSet.getObject(4)));
            billPaymentDTO.setAmountPaid((Double) resultSet.getObject(5));
            billPaymentDTO.setEmpId(String.valueOf(resultSet.getObject(6)));
            billPaymentDTOS.add(billPaymentDTO);
        }
        return billPaymentDTOS;
    }
    private ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ResultSet resultSet = billBO.getAllEmployees();
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
