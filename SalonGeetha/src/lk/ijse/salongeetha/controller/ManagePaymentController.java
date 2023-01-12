package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.castom.BookingDAO;
import lk.ijse.salongeetha.model.castom.PaymentDAO;
import lk.ijse.salongeetha.model.castom.impl.*;
import lk.ijse.salongeetha.to.*;
import lk.ijse.salongeetha.to.tm.PaymentTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagePaymentController {

    public TableColumn columenAmountStatus;
    public ToggleGroup payment;
    public JFXRadioButton bPayment;
    public JFXRadioButton aPayment;
    @FXML
    private GridPane rigthPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblBar;

    @FXML
    private Label lblPaymentId;

    @FXML
    private JFXComboBox<String> cmbPaymentMethod;

    @FXML
    private JFXComboBox<String> cmbAppointmentIdOrBookingId;

    @FXML
    private JFXTextField txtAmountPaid;

    @FXML
    private Label lblAmountDue;

    @FXML
    private Label lblBalance;

    @FXML
    private TableView<PaymentTM> tblView;

    @FXML
    private TableColumn<?, ?> columenPaymentId;

    @FXML
    private TableColumn<?, ?> columenAIdOrBId;

    @FXML
    private TableColumn<?, ?> columenPaymentMethod;

    @FXML
    private TableColumn<?, ?> columenAmountDue;

    @FXML
    private TableColumn<?, ?> columenAction;
    private double total;
    private String setAOrB = "Appointment payment";
    ArrayList<Payment> aPaymentArrayList;
    ArrayList<Payment> bPaymentArrayList;
    PaymentDAO paymentDAO = new PaymentModel();
    BookingDAO booingDAO = new BookingModel();

    {
        try {
            aPaymentArrayList = paymentDAO.getAllAPayments();
            bPaymentArrayList = paymentDAO.getAllBPayments();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<PaymentTM> observableList = FXCollections.observableArrayList();

    private void loadAllAPaymentData() {
        observableList.clear();
        for (Payment s : aPaymentArrayList) {
            PaymentTM paymentTM = new PaymentTM(s.getPayId(), s.getPaymentMethod(), s.getAmountDue(), s.getaOrBId());
            observableList.add(paymentTM);
            tblView.setItems(observableList);
        }
    }

    private void loadAllBPaymentData() {
        observableList.clear();
        for (Payment s : bPaymentArrayList) {
            PaymentTM paymentTM = new PaymentTM(s.getPayId(), s.getPaymentMethod(), s.getAmountDue(), s.getaOrBId());
            observableList.add(paymentTM);
            tblView.setItems(observableList);
        }
    }


    public void initialize() {

        loadAllAPaymentData();
        setNextAId();
        aPayment.setSelected(true);
        loadCmb();
        columenPaymentId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        columenAIdOrBId.setCellValueFactory(new PropertyValueFactory<>("AOrB_Id"));
        columenPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        columenAmountDue.setCellValueFactory(new PropertyValueFactory<>("Amount_due"));

    }

    private void loadCmb() {
        String[] payMethods = new String[]{"Cash", "Checks", "Debit cards", "Credit cards", "Mobile payments", "Electronic bank transfers"};
        cmbPaymentMethod.getItems().setAll(payMethods);

        ArrayList<Appointment> appointments;
        ArrayList<Book> books;

        try {
            appointments = AppointmentModel.getIds();
            books = booingDAO.getIdS();

            String[] aIds = new String[appointments.size()];
            String[] bIds = new String[books.size()];

            if (appointments.size() != 0) {
                for (int i = 0; i < appointments.size(); i++) {
                    aIds[i] = appointments.get(i).getAptId();
                }
            }
            if (books.size() != 0) {
                for (int i = 0; i < bIds.length; i++) {
                    bIds[i] = books.get(i).getBokId();

                }
            }
            String[] ids = new String[aIds.length + bIds.length];
            for (int i = 0; i < aIds.length; i++) {
                ids[i] = appointments.get(i).getAptId();
            }
            int j = 0;
            for (int i = aIds.length; i < ids.length; i++) {
                ids[i] = books.get(j).getBokId();
//                System.out.println(books.get(j).getBokId());
                j++;
            }

            cmbAppointmentIdOrBookingId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setNextAId() {
        try {
            String billPayment = paymentDAO.checkAppointmentId();
            String generateNextId = GenerateId.generateNextId(billPayment, IdTypes.APAYMENT);
            lblPaymentId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextBId() {
        try {
            String billPayment = paymentDAO.checkBookId();
            String generateNextId = GenerateId.generateNextId(billPayment, IdTypes.BPAYMENT);
            lblPaymentId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnSavePaymentOnAction(ActionEvent actionEvent) {
        String pid = lblPaymentId.getText();
        String appointmentIdOrBookingIdValue = cmbAppointmentIdOrBookingId.getValue();
        double amountDue = Double.parseDouble(lblAmountDue.getText());
        String paymentMethodValue = cmbPaymentMethod.getValue();
        Payment payment = new Payment();
        payment.setPayId(pid);
        payment.setPaymentMethod(paymentMethodValue);
        payment.setAmountDue(amountDue);
        payment.setaOrBId(appointmentIdOrBookingIdValue);
        try {
            boolean addPayment = paymentDAO.add(payment);
            if (addPayment) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment add is successful");
                alert.show();
                loadCmb();
                tblView.getItems().clear();

                if (setAOrB.equals("Appointment payment")) {
                    aPaymentArrayList = paymentDAO.getAllAPayments();
                    loadAllAPaymentData();
                    setNextAId();


                    InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/appointmentBill.jrxml");
                    HashMap<String, Object> hm = new HashMap<>();
                    hm.put("TotalValue", Double.valueOf(lblAmountDue.getText()));
                    hm.put("AmountPaid", Double.valueOf(txtAmountPaid.getText()));
                    hm.put("Balance", Double.valueOf(lblBalance.getText()));
                    try {
                        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getDBConnection().getConnection());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException | ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    bPaymentArrayList = paymentDAO.getAllBPayments();
                    loadAllBPaymentData();
                    setNextBId();


                    InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/bookingBill.jrxml");
                    HashMap<String, Object> hm = new HashMap<>();
                    hm.put("TotalValue", Double.valueOf(lblAmountDue.getText()));
                    hm.put("AmountPaid", Double.valueOf(txtAmountPaid.getText()));
                    hm.put("Balance", Double.valueOf(lblBalance.getText()));
                    try {
                        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getDBConnection().getConnection());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException | ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    cmbPaymentMethod.setValue(null);
                    cmbAppointmentIdOrBookingId.setValue(null);
                    lblBalance.setText(null);
                    lblAmountDue.setText(null);
                    txtAmountPaid.setText(null);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbAppointmentIdOrBookingId(ActionEvent actionEvent) {
        String value = cmbAppointmentIdOrBookingId.getValue();

        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(value);
//        System.out.println(matcher.matches());
        try {
            if (matcher.matches()) {
                total = 0;
                BookRentalsDetail bookRentalsDetail = new BookRentalsDetail();
                bookRentalsDetail.setBokId(value);

                ArrayList<BookRentalsDetail> amountDue = BookingRentalsModel.getAmountDue(bookRentalsDetail);
                if (amountDue.size() != 0) {
                    for (BookRentalsDetail b : amountDue) {
                        total += b.getForHowManyDays() * (b.getPrice() - (b.getPrice() * b.getDiscount()) / 100.0);
                    }
                    lblAmountDue.setText(String.valueOf(total));
                }
            } else {
                total = 0;
                ServiceAppointmentDetail serviceAppointmentDetail = new ServiceAppointmentDetail();
                serviceAppointmentDetail.setAptId(value);
                ArrayList<ServiceAppointmentDetail> amountDue = ServiceAppointmentModel.getAmountDue(serviceAppointmentDetail);
                if (amountDue.size() != 0) {
                    for (ServiceAppointmentDetail a : amountDue) {
                        total += a.getPrice() - (a.getPrice() * a.getDiscount()) / 100.0;
                    }
                    lblAmountDue.setText(String.valueOf(total));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtAmountPaid(ActionEvent actionEvent) {
        double balance = Double.parseDouble(txtAmountPaid.getText()) - total;
        lblBalance.setText(String.valueOf(balance));
    }

    public void bPayment(ActionEvent actionEvent) {
        setAOrB = bPayment.getText();
        loadAllBPaymentData();
        setNextBId();
    }

    public void aPayment(ActionEvent actionEvent) {
        setAOrB = aPayment.getText();
        loadAllAPaymentData();
        setNextAId();
    }
}
