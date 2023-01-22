package lk.ijse.salongeetha.controller;

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
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.CustomerBO;
import lk.ijse.salongeetha.dto.CustomerDTO;
import lk.ijse.salongeetha.view.tm.CustomerTM;
import lk.ijse.salongeetha.util.SendMail;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageCustomerController {

    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public TextField txtSearch;
    public TextArea txtMsg;
    public TextField txtSubject;
    public TextField txtTo;
    public TableColumn columenMail;
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
    CustomerBO customerBO = (CustomerBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.CUSTOMER);

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

    public void btnSendMailOnAction(ActionEvent actionEvent) {
        String to = txtTo.getText();
        String subject = txtSubject.getText();
        String msg = txtMsg.getText();


        if (ValidityCheck.check(Validation.EMAIL, to)) {
            if (msg != null) {
                boolean authentication = SendMail.Authentication(subject, to, msg);
                if (authentication) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email send is successful");
                    alert.show();
                    txtTo.setText(null);
                    txtMsg.setText(null);
                    txtSubject.setText(null);
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Email send is fail");
                    alert.show();
                }
            } else {
                txtMsg.requestFocus();
            }
        } else {
            txtTo.setText(null);
            txtTo.requestFocus();
        }

    }

    ArrayList<CustomerDTO> customerDTOArrayList;

    {
        try {
            customerDTOArrayList = getAllCustomer();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (CustomerDTO c : customerDTOArrayList) {
            JFXButton delete = new JFXButton("Delete");
            JFXButton update = new JFXButton("Update");
            JFXButton mail = new JFXButton("Set to mail");
            update.setStyle("-fx-background-color: linear-gradient(to right, #17ff00, #12fe18, #0bfc25, #05fb2e, #00f936);");
            mail.setStyle("-fx-background-color: #0000FF");
            delete.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
            delete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    CustomerTM tm = tblView.getSelectionModel().getSelectedItem();

                    String customerNIC = tm.getNic();
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setNic(customerNIC);
                    try {
                        boolean isDeleted = customerBO.deleteCustomer(customerDTO);
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

                CustomerDTO customerDTO = new CustomerDTO(tm.getNic(), tm.getName(), tm.getPhoneNumber(), tm.getEmail(), tm.getDob());
                UpdateCustomerFormController.getUpdateDetails(customerDTO);

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
            mail.setOnAction((e) -> {
                CustomerTM tm = tblView.getSelectionModel().getSelectedItem();
                String email = tm.getEmail();
                if (email != null) {
                    txtTo.setText(email);
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Select customerDTO");
                    alert.show();
                }

                /*
                CustomerDTO customerDTO = new CustomerDTO(tm.getNic(), tm.getName(), tm.getPhoneNumber(), tm.getEmail(), tm.getDob());

                UpdateCustomerFormController.getUpdateDetails(customerDTO);
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
                stage.show();*/

            });
            CustomerTM customerTM = new CustomerTM(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(), c.getDob(), delete, update, mail);
            observableList.add(customerTM);
            tblView.setItems(observableList);
        }
    }


    CustomerDTO customerDTO = new CustomerDTO();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            customerDTO.setName(text);
            try {
                customerDTOArrayList = search(customerDTO);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }

    public ArrayList<CustomerDTO> search(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(customerDTO.getName());
        return customerBO.searchCustomer(matcher.matches(), customerDTO);
    }

    public void cleanTable() {

        try {
            tblView.getItems().clear();
            customerDTOArrayList = getAllCustomer();
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
        columenMail.setCellValueFactory(new PropertyValueFactory<>("mailButton"));
        loadAllData();


    }

    private ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerBO.getAllCustomer();
    }
}
