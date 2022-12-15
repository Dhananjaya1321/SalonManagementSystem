package lk.ijse.salongeetha.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.model.CustomerModel;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageCustomerController {

    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public TextField txtSearch;
    @FXML
    private GridPane rigthPane;

    @FXML
    private TableView<CustomerTM> tblView;

    @FXML
    private TableColumn<?, ?> columenNIC;

    @FXML
    private TableColumn<?, ?> columenName;

    @FXML
    private TableColumn<?, ?> columenPhone;

    @FXML
    private TableColumn<?, ?> columenEmail;

    @FXML
    private TableColumn<?, ?> columenDOB;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblBar;

    @FXML
    private AnchorPane popUpPane;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/salongeetha/view/AddCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();


    }

    ArrayList<Customer> customerArrayList;

    {
        try {
            customerArrayList = CustomerModel.getAllCustomer();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Customer c : customerArrayList) {
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
                    CustomerTM tm = tblView.getSelectionModel().getSelectedItem();

                    String customerNIC = tm.getNic();
                    Customer customer = new Customer();
                    customer.setNic(customerNIC);
                    try {
                        boolean isDeleted = CustomerModel.deleteCustomer(customer);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Rental delete successfully");
                            alert1.show();
                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                    tblView.getItems().removeAll(tm);
                }
            });
            update.setOnAction((e) -> {
                CustomerTM tm = tblView.getSelectionModel().getSelectedItem();

                Customer customer = new Customer(tm.getNic(), tm.getName(), tm.getPhoneNumber(), tm.getEmail(), tm.getDob());
                UpdateCustomerFormController.getUpdateDetails(customer);

                URL resource = getClass().getResource("/lk/ijse/salongeetha/view/UpdateCustomerForm.fxml");
                Parent load;
                try {
                    load = FXMLLoader.load(resource);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.resizableProperty().setValue(false);
                stage.show();

            });
            CustomerTM customerTM = new CustomerTM(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(), c.getDob(), delete, update);
            observableList.add(customerTM);
            tblView.setItems(observableList);
        }
    }


    Customer customer = new Customer();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            customer.setName(text);
            try {
                customerArrayList = CustomerModel.searchCustomer(customer);
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
            customerArrayList = CustomerModel.getAllCustomer();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void initialize() {

        columenNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        columenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columenPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columenEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columenDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        loadAllData();


    }

}
