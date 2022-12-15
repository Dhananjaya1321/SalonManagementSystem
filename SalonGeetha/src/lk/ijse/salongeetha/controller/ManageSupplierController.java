package lk.ijse.salongeetha.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.model.*;
import lk.ijse.salongeetha.to.Supplier;
import lk.ijse.salongeetha.to.tm.SupplierTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.paint.Color.RED;

public class ManageSupplierController {


    public JFXButton btnClean;
    public JFXTextField txtSupplierId;
    public TableColumn<?, ?> columenAddress;
    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public JFXButton btnUpdate;
    public JFXButton btnAdd;
    public TextField txtSearch1;
    public TextField txtSearch;
    public Label lblVName;
    public Label lblVPhone;
    public Label lblVEmail;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<SupplierTM> tblView;

    @FXML
    private TableColumn<?, ?> columenSupplierId;

    @FXML
    private TableColumn<?, ?> columenName;

    @FXML
    private TableColumn<?, ?> columenDescription;

    @FXML
    private TableColumn<?, ?> columenPhoneNumber;

    @FXML
    private TableColumn<?, ?> columenEmail;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblSupplierId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextArea txtAddress;
    ArrayList<Supplier> supplierList;

    {
        try {
            supplierList = SupplierModel.getSuppliers();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddONAction(ActionEvent event) {
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        String email = txtEmail.getText();
        String name = txtName.getText();
        String phone = txtPhoneNumber.getText();
        String supId = lblSupplierId.getText();
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.PHONE, phone)) {
                if (ValidityCheck.check(Validation.EMAIL, email)) {

                    Supplier supplier = new Supplier(supId, description, name, address, phone, email);
                    try {
                        boolean addSupplier = SupplierModel.addSupplier(supplier);
                        if (addSupplier) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Supplier added successful");
                            alert.show();
                            cleanAll();
                            setNextId();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "error");
                            alert.show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    lblVEmail.setText("Invalid email");
                    txtEmail.setFocusColor(RED);
                    txtEmail.requestFocus();
                }
            } else {
                lblVPhone.setText("Invalid number");
                txtPhoneNumber.setFocusColor(RED);
                txtPhoneNumber.requestFocus();
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.setFocusColor(RED);
            txtName.requestFocus();
        }
    }


    public void btnUpdateONAction(ActionEvent actionEvent) {
        String address = txtAddress.getText();
        String description = txtDescription.getText();
        String email = txtEmail.getText();
        String name = txtName.getText();
        String phone = txtPhoneNumber.getText();
        String supId = lblSupplierId.getText();

        if (ValidityCheck.check(Validation.NAME, name)) {
            if (ValidityCheck.check(Validation.PHONE, phone)) {
                if (ValidityCheck.check(Validation.EMAIL, email)) {
                    Supplier supplier = new Supplier(supId, description, name, address, phone, email);
                    try {
                        boolean addSupplier = SupplierModel.updateSupplier(supplier);
                        if (addSupplier) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful");
                            alert.show();
                            cleanAll();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    lblVEmail.setText("Invalid email");
                    txtEmail.setFocusColor(RED);
                    txtEmail.requestFocus();
                }
            } else {
                lblVPhone.setText("Invalid number");
                txtPhoneNumber.setFocusColor(RED);
                txtPhoneNumber.requestFocus();
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.setFocusColor(RED);
            txtName.requestFocus();
        }
    }

    public void btnCleanONAction(ActionEvent actionEvent) {
        cleanAll();
    }


    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    public void btnSearchONAction(ActionEvent actionEvent) {
        search();
    }


    Supplier supplier = new Supplier();

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            supplier.setName(text);
            try {
                supplierList = SupplierModel.searchSupplier(supplier);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }

    public void cleanTable() {

        try {
            tblView.getItems().clear();
            supplierList = SupplierModel.getSuppliers();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void cleanAll() {
        lblSupplierId.setText("");
        txtDescription.setText("");
        txtPhoneNumber.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtSearch.setText("");
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        lblVPhone.setText(null);
        lblVEmail.setText(null);
        lblVName.setText(null);
        setNextId();
        cleanTable();
        loadAllData();

    }

    ObservableList<SupplierTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Supplier s : supplierList) {
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
                    SupplierTM tm = tblView.getSelectionModel().getSelectedItem();


                    String supplierId = tm.getSupId();
                    Supplier supplier = new Supplier();
                    supplier.setSupId(supplierId);
                    try {
                        boolean isDeleted = SupplierModel.deleteSupplier(supplier);
                        if (isDeleted) {
                            Alert deleteSuccessfully = new Alert(Alert.AlertType.WARNING, "Employee delete successfully");
                            deleteSuccessfully.show();
                            setNextId();
                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                    tblView.getItems().removeAll(tm);
                }
            });
            update.setOnAction((e) -> {
                SupplierTM tm = tblView.getSelectionModel().getSelectedItem();
                btnUpdate.setVisible(true);
                btnAdd.setVisible(false);

                lblSupplierId.setText(tm.getSupId());
                txtName.setText(tm.getName());
                txtDescription.setText(tm.getDescription());
                txtPhoneNumber.setText(tm.getPhoneNumber());
                txtEmail.setText(tm.getEmail());
                txtAddress.setText(tm.getAddress());

            });
            SupplierTM supplierTM = new SupplierTM(s.getSupId(), s.getDescription(), s.getName(), s.getAddress(), s.getPhoneNumber(), s.getEmail(), update, delete);
            observableList.add(supplierTM);
            tblView.setItems(observableList);
        }
    }

    private void setNextId() {
        try {

            String currentId = SupplierModel.checkId();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.SUPPLIER);
            lblSupplierId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        columenSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columenAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columenPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columenEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        cleanAll();
        setNextId();
    }


}
