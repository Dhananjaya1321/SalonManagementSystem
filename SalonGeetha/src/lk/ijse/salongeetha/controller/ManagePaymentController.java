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
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.*;
import lk.ijse.salongeetha.dao.castom.impl.*;
import lk.ijse.salongeetha.to.*;
import lk.ijse.salongeetha.to.tm.PaymentTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.ResultSet;
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
    AppointmentDAO appointmentDAO = new AppointmentModel();
    //    ServiceAppointmentDAO serviceAppointmentDAO = new ServiceAppointmentModel();
    QueryDAO queryDAO = new QueryDAOImpl();
    BookingDAO bookingDAO = new BookingModel();

    {
        try {
            aPaymentArrayList = getAllAPayments();
            bPaymentArrayList = getAllBPayments();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Payment> getAllBPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM book_payment");
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setPayId(String.valueOf(resultSet.getObject(1)));
            payment.setPaymentMethod(String.valueOf(resultSet.getObject(2)));
            payment.setaOrBId(String.valueOf(resultSet.getObject(3)));
            payment.setAmountDue(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            payments.add(payment);
        }
        return payments;
    }

    private ArrayList<Payment> getAllAPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = paymentDAO.getAllAPayments();
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setPayId(String.valueOf(resultSet.getObject(1)));
            payment.setPaymentMethod(String.valueOf(resultSet.getObject(2)));
            payment.setaOrBId(String.valueOf(resultSet.getObject(3)));
            payment.setAmountDue(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            payments.add(payment);
        }
        return payments;
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
            appointments = appointmentDAO.getIds();
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
            boolean addPayment = add(payment);
            if (addPayment) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment add is successful");
                alert.show();
                loadCmb();
                tblView.getItems().clear();

                if (setAOrB.equals("Appointment payment")) {
                    aPaymentArrayList = getAllAPayments();
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
                    bPaymentArrayList = getAllBPayments();
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

    private boolean add(Payment payment) throws SQLException, ClassNotFoundException {
        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(payment.getaOrBId());
        if (matcher.matches()) {
            bookingDAO.getId(payment);
            boolean isAdded = paymentDAO.addBookingPayment(payment);//set
            if (isAdded) {
                Book book = new Book();
                book.setBokId(payment.getaOrBId());
                book.setStatus("Paid");
                bookingDAO.update(book);
                return true;
            }
            return false;
        } else {
            appointmentDAO.getId(payment);
            boolean isAdded = paymentDAO.addAppointmentPayment(payment);//set
            if (isAdded) {
                Appointment appointment = new Appointment();
                appointment.setAptId(payment.getaOrBId());
                appointment.setStatus("Paid");
                appointmentDAO.update(appointment);
                return true;
            }
            return false;
        }
    }

    public void cmbAppointmentIdOrBookingId(ActionEvent actionEvent) {
        String value = cmbAppointmentIdOrBookingId.getValue();

        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(value);
        try {
            if (matcher.matches()) {
                total = 0;
                BookRentalsDetail bookRentalsDetail = new BookRentalsDetail();
                bookRentalsDetail.setBokId(value);
                QueryDAO queryDAO = new QueryDAOImpl();
                ArrayList<BookRentalsDetail> amountDue = getAmountDueBookRentalsDetail(bookRentalsDetail);
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
                ArrayList<ServiceAppointmentDetail> amountDue = getAmountDueServiceAppointmentDetail(serviceAppointmentDetail);
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
    private ArrayList<BookRentalsDetail> getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        ArrayList<BookRentalsDetail> bookRentalsDetails = new ArrayList<>();
        ResultSet resultSet = queryDAO.getAmountDueBookRentalsDetail(bookRentalsDetail);
        while (resultSet.next()) {
            BookRentalsDetail setBookRentalsDetail = new BookRentalsDetail();
            setBookRentalsDetail.setQty((Integer) resultSet.getObject(1));
            setBookRentalsDetail.setForHowManyDays((Integer) resultSet.getObject(2));
            setBookRentalsDetail.setPrice((Double) resultSet.getObject(3));
            setBookRentalsDetail.setDiscount((Double) resultSet.getObject(4));
            bookRentalsDetails.add(setBookRentalsDetail);
        }
        return bookRentalsDetails;
    }

    private ArrayList<ServiceAppointmentDetail> getAmountDueServiceAppointmentDetail(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException {
        ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails = new ArrayList<>();
        ResultSet resultSet = queryDAO.getAmountDueServiceAppointmentDetails(serviceAppointmentDetail);
        while (resultSet.next()) {
            ServiceAppointmentDetail setServiceAppointmentDetail = new ServiceAppointmentDetail();
            setServiceAppointmentDetail.setPrice((Double) resultSet.getObject(1));
            setServiceAppointmentDetail.setDiscount((Double) resultSet.getObject(2));
            serviceAppointmentDetails.add(setServiceAppointmentDetail);
        }
        return serviceAppointmentDetails;
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
