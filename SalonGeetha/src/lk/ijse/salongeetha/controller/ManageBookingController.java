package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.model.castom.impl.BookingModel;
import lk.ijse.salongeetha.model.castom.impl.CustomerModel;
import lk.ijse.salongeetha.model.castom.impl.RentalsModel;
import lk.ijse.salongeetha.to.*;
import lk.ijse.salongeetha.to.tm.BookTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static javafx.scene.paint.Color.RED;

public class ManageBookingController {

    public AnchorPane popUpPane;
    public TableColumn columenTotal;
    public Label lblVDateCount;
    public Label lblVQty;
    public Label lblTotal;

    @FXML
    private GridPane rigthPane;

    @FXML
    private TableView<BookTM> tblView;

    @FXML
    private TableColumn<?, ?> columenBookingId;

    @FXML
    private TableColumn<?, ?> columenCustomerId;

    @FXML
    private TableColumn<?, ?> columenRentalId;

    @FXML
    private TableColumn<?, ?> columenDate;

    @FXML
    private TableColumn<?, ?> columenHowManyDay;

    @FXML
    private TableColumn columenQty;

    @FXML
    private TableColumn columenPrice;

    @FXML
    private TableColumn columenDiscount;

    @FXML
    private TableColumn<?, ?> columenAction;


    @FXML
    private Label lblBookingId;

    @FXML
    private Label lblBillId1;

    @FXML
    private JFXTextField txtHowManyDays;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private Label lblCustomerPhone;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblCustomerNIC;

    @FXML
    private JFXComboBox<String> cmbRentalId;

    @FXML
    private Label lblRentalName;

    @FXML
    private Label lblServiceName2;

    @FXML
    private Label lblRentalDescription;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private JFXButton btnClean;

    @FXML
    private Label lblBar;


    @FXML
    private Label lblPricePreDay;

    @FXML
    private Label lblDiscount;

    @FXML
    private JFXTextField txtQty;
    private double calTotal = 0;
    ObservableList<BookTM> observableList = FXCollections.observableArrayList();
//    ArrayList<BookTM> arrayList = new ArrayList();

    @FXML
    void btnAddONAction(ActionEvent event) {
        String rid = cmbRentalId.getValue();
        String nic = cmbCustomerId.getValue();
        String bid = lblBookingId.getText();
        String date = String.valueOf(txtDate.getValue());
        String howManyDays = String.valueOf(txtHowManyDays.getText());
       /* String name = lblCustomerName.getText();
        String phone = lblCustomerPhone.getText();
        String rantal = lblRentalName.getText();
        String description = lblRentalDescription.getText();
        int qtyOnHand = Integer.parseInt(lblQtyOnHand.getText());*/
        double pricePreDay = Double.parseDouble(lblPricePreDay.getText());
        double discount = Double.parseDouble(lblDiscount.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = (pricePreDay - ((pricePreDay * discount) / 100.0)) * qty;


        cmbRentalId.setValue(null);
        lblDiscount.setText(null);
        lblPricePreDay.setText(null);
        lblQtyOnHand.setText(null);
        lblRentalDescription.setText(null);
        lblRentalName.setText(null);
        txtQty.setText(null);


        JFXButton remove = new JFXButton("Remove");
        remove.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
        remove.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {

                BookTM tm = tblView.getSelectionModel().getSelectedItem();
                tblView.getItems().removeAll(tm);
                tblView.refresh();
            }
            calTotal = 0;
            for (int i = 0; i < tblView.getItems().size(); i++) {
                calTotal += (Double) columenTotal.getCellData(i);
            }
            lblTotal.setText(String.valueOf(calTotal));
//            calculateTot(arrayList);
        });
        if (!observableList.isEmpty()) {
            for (int i = 0; i < tblView.getItems().size(); i++) {
                if (columenRentalId.getCellData(i).equals(rid) && columenHowManyDay.getCellData(i).equals(howManyDays)) {
                    qty += (int) columenQty.getCellData(i);
                    total = (pricePreDay - ((pricePreDay * discount) / 100.0)) * qty;

                    observableList.get(i).setQty(qty);
                    observableList.get(i).setTotal(total);
                    tblView.refresh();
                    return;
                }
            }
        }

        if (Integer.valueOf(howManyDays) > 0) {
            if (qty > 0) {
                observableList.add(new BookTM(bid, nic, rid, date, Integer.valueOf(howManyDays), qty, pricePreDay, discount, total, remove));
                tblView.setItems(observableList);
                lblVQty.setText(null);
                lblVDateCount.setText(null);
            } else {
                txtQty.requestFocus();
                txtQty.setFocusColor(RED);
                lblVQty.setText("Invalid qty");
            }
        } else {
            txtHowManyDays.requestFocus();
            txtHowManyDays.setFocusColor(RED);
            lblVDateCount.setText("Invalid day count");
        }
        calTotal = 0;
        for (int i = 0; i < tblView.getItems().size(); i++) {
            calTotal += (Double) columenTotal.getCellData(i);
        }

        lblTotal.setText(String.valueOf(calTotal));
    }

    @FXML
    void btnCleanONAction(ActionEvent event) {
        cleanAll();
    }

    @FXML
    void btnMakeAppointmentOnAction(ActionEvent event) {
        String bid = lblBookingId.getText();
        String nic = cmbCustomerId.getValue();
        String date = String.valueOf(txtDate.getValue());
        ArrayList<BookRentalsDetail> bookRentalsDetails = new ArrayList<>();
        for (int i = 0; i < tblView.getItems().size(); i++) {
            BookTM tm = observableList.get(i);
            bookRentalsDetails.add(new BookRentalsDetail(tm.getRid(), tm.getBokId(), tm.getHowManyDays(), tm.getQty()));
        }
        Book book = new Book(bid, date, nic);

        try {
            boolean addBooking = BookingModel.addBooking(book, bookRentalsDetails);
            if (addBooking) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Booking is successful");
                alert.show();
                cleanAll();
                observableList.clear();
                setNextId();




            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void cleanAll() {
        cmbRentalId.setValue(null);
        lblDiscount.setText(null);
        lblPricePreDay.setText(null);
        lblCustomerPhone.setText(null);
        lblCustomerName.setText(null);
        lblTotal.setText(null);
        cmbCustomerId.setValue(null);
        lblQtyOnHand.setText(null);
        lblRentalDescription.setText(null);
        lblRentalName.setText(null);
        txtQty.setText(null);
        txtHowManyDays.setText(null);
        currentDate();
        lblVQty.setText(null);
        lblVDateCount.setText(null);
        tblView.getItems().clear();
    }

    public void cmbRentalId(ActionEvent actionEvent) {
        String value = cmbRentalId.getValue();
        Rentals rental = new Rentals();
        rental.setRntId(value);
        try {
            ArrayList<Rentals> rentals = RentalsModel.searchRentalsDetails(rental);
            if (rentals.size() > 0) {
                for (Rentals r : rentals) {
                    lblRentalName.setText(r.getName());
                    lblRentalDescription.setText(r.getDescription());
                    lblQtyOnHand.setText(String.valueOf(r.getAvaliableCount()));
                    lblPricePreDay.setText(String.valueOf(r.getPricePreDay()));
                    lblDiscount.setText(String.valueOf(r.getDiscount()));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbCustomerId(ActionEvent actionEvent) {
        String value = cmbCustomerId.getValue();
        Customer customer = new Customer();
        customer.setNic(value);
        try {
            ArrayList<Customer> customers = CustomerModel.searchCustomerDetails(customer);
            if (customers.size() > 0) {
                for (Customer c : customers) {
//                    lblCustomerNIC.setText(c.getNic());
                    lblCustomerName.setText(c.getName());
                    lblCustomerPhone.setText(c.getPhoneNumber());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNewONAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/salongeetha/view/AddCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();

    }

    public void initialize() {

        setNextId();
        loadCmb();
        currentDate();
        columenBookingId.setCellValueFactory(new PropertyValueFactory<>("bokId"));
        columenRentalId.setCellValueFactory(new PropertyValueFactory<>("rid"));
        columenCustomerId.setCellValueFactory(new PropertyValueFactory<>("nic"));
        columenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columenHowManyDay.setCellValueFactory(new PropertyValueFactory<>("howManyDays"));
        columenQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columenDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        columenPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columenTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columenAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

    }

    private void setNextId() {
        try {
            String currentId = BookingModel.checkId();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.BOOK);
            lblBookingId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCmb() {
        try {
            ArrayList<Rentals> allRentals = RentalsModel.getAllRentals();
            String[] ids;
            if (allRentals.size() != 0) {
                ids = new String[allRentals.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = allRentals.get(i).getRntId();
                }
                cmbRentalId.getItems().addAll(ids);
            }

            ArrayList<Customer> customers = CustomerModel.getAllCustomer();
            if (customers.size() != 0) {
                ids = new String[customers.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(customers.get(i).getNic());
                }
                cmbCustomerId.getItems().addAll(ids);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void currentDate() {
        txtDate.setValue(LocalDate.now());
    }


}
