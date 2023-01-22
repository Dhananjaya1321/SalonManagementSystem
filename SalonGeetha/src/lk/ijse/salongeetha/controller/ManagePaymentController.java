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
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.PaymentBO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.impl.QueryDAOImpl;
import lk.ijse.salongeetha.dto.*;
import lk.ijse.salongeetha.view.tm.PaymentTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private String setAOrB = "AppointmentDTO payment";
    ArrayList<PaymentDTO> aPaymentDTOArrayList;
    ArrayList<PaymentDTO> bPaymentDTOArrayList;
    PaymentBO paymentBO = (PaymentBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.PAYMENT);

    {
        try {
            aPaymentDTOArrayList = getAllAPayments();
            bPaymentDTOArrayList = getAllBPayments();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<PaymentDTO> getAllBPayments() throws SQLException, ClassNotFoundException {
        return paymentBO.getAllBookingPayments();
    }

    private ArrayList<PaymentDTO> getAllAPayments() throws SQLException, ClassNotFoundException {
        return paymentBO.getAllAppointmentPayments();
    }

    ObservableList<PaymentTM> observableList = FXCollections.observableArrayList();

    private void loadAllAPaymentData() {
        observableList.clear();
        for (PaymentDTO s : aPaymentDTOArrayList) {
            PaymentTM paymentTM = new PaymentTM(s.getPayId(), s.getPaymentMethod(), /*methanata getAmountDue()*/s.getAmountPaid(), s.getaOrBId());
            observableList.add(paymentTM);
//            System.out.println(s.getAmountDue());
            tblView.setItems(observableList);
        }
    }

    private void loadAllBPaymentData() {
        observableList.clear();
        for (PaymentDTO s : bPaymentDTOArrayList) {
            PaymentTM paymentTM = new PaymentTM(s.getPayId(), s.getPaymentMethod(), /*methanata getAmountDue()*/s.getAmountPaid(), s.getaOrBId());
//            System.out.println(paymentTM.getAOrB_Id());
            observableList.add(paymentTM);
            tblView.setItems(observableList);
        }
    }




    private void loadCmb() {
        String[] payMethods = new String[]{"Cash", "Checks", "Debit cards", "Credit cards", "Mobile payments", "Electronic bank transfers"};
        cmbPaymentMethod.getItems().setAll(payMethods);

        ArrayList<AppointmentDTO> appointmentDTOS;
        ArrayList<BookDTO> bookDTOS;

        try {
            appointmentDTOS = paymentBO.getAppointmentIds();
            bookDTOS = paymentBO.getBookingIds();

            String[] aIds = new String[appointmentDTOS.size()];
            String[] bIds = new String[bookDTOS.size()];

            if (appointmentDTOS.size() != 0) {
                for (int i = 0; i < appointmentDTOS.size(); i++) {
                    aIds[i] = appointmentDTOS.get(i).getAptId();
                }
            }
            if (bookDTOS.size() != 0) {
                for (int i = 0; i < bIds.length; i++) {
                    bIds[i] = bookDTOS.get(i).getBokId();

                }
            }
            String[] ids = new String[aIds.length + bIds.length];
            for (int i = 0; i < aIds.length; i++) {
                ids[i] = appointmentDTOS.get(i).getAptId();
            }
            int j = 0;
            for (int i = aIds.length; i < ids.length; i++) {
                ids[i] = bookDTOS.get(j).getBokId();
//                System.out.println(bookDTOS.get(j).getBokId());
                j++;
            }

            cmbAppointmentIdOrBookingId.getItems().addAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setNextAId() {
        try {
            String billPayment = paymentBO.checkAppointmentId();
            String generateNextId = GenerateId.generateNextId(billPayment, IdTypes.APAYMENT);
            lblPaymentId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextBId() {
        try {
            String billPayment = paymentBO.checkBookingId();
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
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayId(pid);
        paymentDTO.setPaymentMethod(paymentMethodValue);
        paymentDTO.setAmountDue(amountDue);
        paymentDTO.setaOrBId(appointmentIdOrBookingIdValue);
        try {
            boolean addPayment = add(paymentDTO);
            if (addPayment) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "PaymentDTO add is successful");
                alert.show();
                loadCmb();
                tblView.getItems().clear();

                if (setAOrB.equals("AppointmentDTO paymentDTO")) {
                    aPaymentDTOArrayList = getAllAPayments();
                    loadAllAPaymentData();
                    setNextAId();


                   /* InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/appointmentBill.jrxml");
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
                    }*/
                } else {
                    bPaymentDTOArrayList = getAllBPayments();
                    loadAllBPaymentData();
                    setNextBId();


                    /*InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/bookingBill.jrxml");
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
                    }*/
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

    private boolean add(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(paymentDTO.getaOrBId());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBokId(paymentDTO.getaOrBId());
        bookDTO.setStatus("Paid");

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setAptId(paymentDTO.getaOrBId());
        appointmentDTO.setStatus("Paid");

        return paymentBO.add(matcher.matches(), paymentDTO, bookDTO, appointmentDTO);
    }

    public void cmbAppointmentIdOrBookingId(ActionEvent actionEvent) {
        String value = cmbAppointmentIdOrBookingId.getValue();

        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(value);
        try {
            if (matcher.matches()) {
                total = 0;
                BookRentalsDetailDTO bookRentalsDetailDTO = new BookRentalsDetailDTO();
                bookRentalsDetailDTO.setBokId(value);
                QueryDAO queryDAO = new QueryDAOImpl();
                ArrayList<BookRentalsDetailDTO> amountDue = getAmountDueBookRentalsDetail(bookRentalsDetailDTO);
                if (amountDue.size() != 0) {
                    for (BookRentalsDetailDTO b : amountDue) {
                        total += b.getForHowManyDays() * (b.getPrice() - (b.getPrice() * b.getDiscount()) / 100.0);
                    }
                    lblAmountDue.setText(String.valueOf(total));
                }
            } else {
                total = 0;
                ServiceAppointmentDetailDTO serviceAppointmentDetailDTO = new ServiceAppointmentDetailDTO();
                serviceAppointmentDetailDTO.setAptId(value);
                ArrayList<ServiceAppointmentDetailDTO> amountDue = getAmountDueServiceAppointmentDetail(serviceAppointmentDetailDTO);
                if (amountDue.size() != 0) {
                    for (ServiceAppointmentDetailDTO a : amountDue) {
                        total += a.getPrice() - (a.getPrice() * a.getDiscount()) / 100.0;
                    }
                    lblAmountDue.setText(String.valueOf(total));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<BookRentalsDetailDTO> getAmountDueBookRentalsDetail(BookRentalsDetailDTO bookRentalsDetailDTO) throws SQLException, ClassNotFoundException {
        ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS = new ArrayList<>();
        ArrayList<CustomDTO> dtos = paymentBO.getAmountDueBookRentalsDetail(bookRentalsDetailDTO);
        for (CustomDTO c : dtos) {
            bookRentalsDetailDTOS.add(new BookRentalsDetailDTO(
                    bookRentalsDetailDTO.getRentId(),
                    bookRentalsDetailDTO.getBokId(),
                    c.getForHowManyDays(),
                    c.getQty(),
                    c.getPrice(),
                    c.getDiscount()));
        }
        return bookRentalsDetailDTOS;
    }

    private ArrayList<ServiceAppointmentDetailDTO> getAmountDueServiceAppointmentDetail(ServiceAppointmentDetailDTO serviceAppointmentDetailDTO) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> dtos =paymentBO.getAmountDueServiceAppointmentDetails(serviceAppointmentDetailDTO);
        ArrayList<ServiceAppointmentDetailDTO> arrayList=new ArrayList<>();
        for (CustomDTO c : dtos) {
            arrayList.add(new ServiceAppointmentDetailDTO(serviceAppointmentDetailDTO.getAptId(),
                    serviceAppointmentDetailDTO.getSevId(),c.getPrice(),c.getDiscount()));
        }
        return arrayList;
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
