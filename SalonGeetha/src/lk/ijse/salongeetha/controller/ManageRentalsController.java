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
import lk.ijse.salongeetha.model.CustomerModel;
import lk.ijse.salongeetha.model.RentalsModel;
import lk.ijse.salongeetha.model.ServiceModel;
import lk.ijse.salongeetha.to.Rentals;
import lk.ijse.salongeetha.to.Service;
import lk.ijse.salongeetha.to.tm.EmployeeTM;
import lk.ijse.salongeetha.to.tm.RentalsTM;
import lk.ijse.salongeetha.to.tm.ServiceTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.paint.Color.RED;

public class ManageRentalsController {

    public JFXTextField txtRentalsId;
    public JFXButton btnClean;
    public Label lblRentalId;
    public TableColumn<?, ?> columenQty;
    public TableColumn columenUpdate;
    public TableColumn columenDelete;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public TextField txtSearch;
    public Label lblVName;
    public Label lblVQty;
    public Label lblVPrice;
    public Label lblVDiscount;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<RentalsTM> tblView;

    @FXML
    private TableColumn<?, ?> columenRentalsId;

    @FXML
    private TableColumn<?, ?> columenName;

    @FXML
    private TableColumn<?, ?> columenDescription;

    @FXML
    private TableColumn<?, ?> columenPricePerDay;

    @FXML
    private TableColumn<?, ?> columenDsicount;

    @FXML
    private TableColumn<?, ?> columenAction;


    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtAvaliableCount;

    @FXML
    private JFXTextField txtPricePreDay;

    @FXML
    private JFXTextField txtDiscount;
    ArrayList<Rentals> rentalsArrayList;

    {
        try {
            rentalsArrayList = RentalsModel.getAllRentals();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<RentalsTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Rentals r : rentalsArrayList) {
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
                    RentalsTM tm = tblView.getSelectionModel().getSelectedItem();

                    String rentalId = tm.getRntId();
                    Rentals rentals = new Rentals();

                    rentals.setRntId(rentalId);
                    try {
                        boolean isDeleted = RentalsModel.deleteRentals(rentals);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Rental delete successfully");
                            alert1.show();
                            setNextId();

                        }
//                        tblView.getItems().clear();
//                        rentalsArrayList = RentalsModel.getAllRentals();
//                        loadAllData();
                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                    tblView.getItems().removeAll(tm);
                }
            });
            update.setOnAction((e) -> {
                RentalsTM tm = tblView.getSelectionModel().getSelectedItem();
                btnUpdate.setVisible(true);
                btnAdd.setVisible(false);

                lblRentalId.setText(tm.getRntId());
                txtName.setText(tm.getName());
                txtDescription.setText(tm.getDescription());
                txtPricePreDay.setText(String.valueOf(tm.getPricePreDay()));
                txtAvaliableCount.setText(String.valueOf(tm.getAvaliableCount()));
                txtDiscount.setText(String.valueOf(tm.getDiscount()));

            });
            RentalsTM rentalsTM = new RentalsTM(r.getRntId(), r.getName(), r.getDescription(), r.getAvaliableCount(), r.getPricePreDay(), r.getDiscount(), delete, update);
            observableList.add(rentalsTM);
            tblView.setItems(observableList);
        }
    }

    @FXML
    void btnAddONAction(ActionEvent event) {
        String rentalId = lblRentalId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int avaliableCount = Integer.parseInt(txtAvaliableCount.getText());
        double pricePreDay = Double.parseDouble(txtPricePreDay.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (avaliableCount > 0) {
                if (pricePreDay > 0) {
                    Rentals rentals = new Rentals(rentalId, name, description, avaliableCount, pricePreDay, discount);
                    try {
                        boolean addRentals = RentalsModel.addRentals(rentals);
                        if (addRentals) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Renal add is successful");
                            alert.show();
                            cleanAll();
                            setNextId();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Error");
                            alert.show();
                        }
                        cleanAll();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    lblVPrice.setText("Invalid price");
                    txtPricePreDay.requestFocus();
                    txtPricePreDay.setFocusColor(RED);
                }
            } else {
                lblVQty.setText("Invalid Qty");
                txtAvaliableCount.requestFocus();
                txtAvaliableCount.setFocusColor(RED);
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.setFocusColor(RED);
            txtName.requestFocus();
        }
    }


    public void btnUpdateONAction(ActionEvent actionEvent) {
        String rentalId = lblRentalId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int avaliableCount = Integer.parseInt(txtAvaliableCount.getText());
        double pricePreDay = Double.parseDouble(txtPricePreDay.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (avaliableCount > 0) {
                if (pricePreDay > 0) {
                    Rentals rentals = new Rentals(rentalId, name, description, avaliableCount, pricePreDay, discount);
                    try {
                        boolean updateRentals = RentalsModel.updateRentals(rentals);
                        if (updateRentals) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful");
                            alert.show();
                            cleanAll();
                            setNextId();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    lblVPrice.setText("Invalid price");
                    txtPricePreDay.requestFocus();
                    txtPricePreDay.setFocusColor(RED);
                }
            } else {
                lblVQty.setText("Invalid Qty");
                txtAvaliableCount.requestFocus();
                txtAvaliableCount.setFocusColor(RED);
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

    private void cleanAll() {
        txtAvaliableCount.setText("");
        txtName.setText("");
        txtDiscount.setText("");
        txtSearch.setText("");
        txtPricePreDay.setText("");
        txtDescription.setText("");
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        lblVPrice.setText(null);
        lblVName.setText(null);
        lblVDiscount.setText(null);
        lblVQty.setText(null);
        try {
            tblView.getItems().clear();
            rentalsArrayList = RentalsModel.getAllRentals();
            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextId() {
        try {

            String currentId = RentalsModel.checkId();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.RENTAL);
            lblRentalId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        columenRentalsId.setCellValueFactory(new PropertyValueFactory<>("rntId"));
        columenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenPricePerDay.setCellValueFactory(new PropertyValueFactory<>("pricePreDay"));
        columenQty.setCellValueFactory(new PropertyValueFactory<>("avaliableCount"));
        columenDsicount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        loadAllData();
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);

//        new Pulse(lblBar).play();
        setNextId();
    }

    private Rentals rentals = new Rentals();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            rentals.setName(text);
            try {
                rentalsArrayList = RentalsModel.searchRentals(rentals);
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
            rentalsArrayList = RentalsModel.getAllRentals();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void btnSearchONAction(ActionEvent actionEvent) {
        search();
    }
}
