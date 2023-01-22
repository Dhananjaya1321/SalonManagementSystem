package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
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
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.AppointmentBO;
import lk.ijse.salongeetha.dto.*;
import lk.ijse.salongeetha.view.tm.AppointmentTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class ManageAppointmentController {

    public AnchorPane popUpPane;
    public Label lblBar;
    public Label lblEmpName;
    public Label lblEmpDescription;
    public Label lblTotal;
    @FXML
    private GridPane rigthPane;

    @FXML
    private TableView<AppointmentTM> tblView;

    @FXML
    private TableColumn<?, ?> columenAppointmentId;

    @FXML
    private TableColumn<?, ?> columenServiceId;

    @FXML
    private TableColumn<?, ?> columenEmployeeId;

    @FXML
    private TableColumn<?, ?> columenCustomerId;

    @FXML
    private TableColumn<?, ?> columenDate;

    @FXML
    private TableColumn<?, ?> columenTime;

    @FXML
    private TableColumn columenDiscount;

    @FXML
    private TableColumn columenPrice;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblAppointmentId;

    @FXML
    private Label lblBillId1;

    @FXML
    private JFXTimePicker txtTime;

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
    private JFXComboBox<String> cmbServiceId;

    @FXML
    private Label lblServiceName;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblDiscount;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;


    @FXML
    private JFXButton btnClean;

    private double calTotal = 0;

    ObservableList<AppointmentTM> observableList = FXCollections.observableArrayList();
    ArrayList<AppointmentTM> arrayList = new ArrayList();
    AppointmentBO appointmentBO= (AppointmentBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.APPOINTMENT);
    @FXML
    void btnAddONAction(ActionEvent event) {
        String employeeIdValue = cmbEmployeeId.getValue();
        String customerIdValue = cmbCustomerId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String appointmentIdText = lblAppointmentId.getText();
        String dateValue = String.valueOf(txtDate.getValue());
        String timeValue = String.valueOf(txtTime.getValue());
        double discountText = Double.parseDouble(lblDiscount.getText());
        double priceText = Double.parseDouble(lblPrice.getText());

        lblServiceName.setText(null);
        lblPrice.setText(null);
        lblDiscount.setText(null);
        lblEmpName.setText(null);
        lblEmpDescription.setText(null);
        cmbServiceId.setValue(null);
        cmbEmployeeId.setValue(null);
        JFXButton remove = new JFXButton("Remove");
        remove.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
        remove.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                AppointmentTM tm = tblView.getSelectionModel().getSelectedItem();
                tblView.getItems().removeAll(tm);
                tblView.refresh();
            }
            calTotal = 0;
            for (int i = 0; i < tblView.getItems().size(); i++) {
                calTotal += (Double) columenPrice.getCellData(i);
            }
            lblTotal.setText(String.valueOf(calTotal));
        });
        if (!observableList.isEmpty()) {
            for (int i = 0; i < tblView.getItems().size(); i++) {
                if (columenServiceId.getCellData(i).equals(serviceIdValue)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "this service add is already exists");
                    alert.show();
//                    tblOrderCart.refresh();
                    return;
                }
            }
        }

        if (employeeIdValue != null && serviceIdValue != null) {
            observableList.add(new AppointmentTM(appointmentIdText, employeeIdValue, serviceIdValue, customerIdValue, dateValue, timeValue, discountText, priceText, remove));
            tblView.setItems(observableList);
        }
        calTotal = 0;
        for (int i = 0; i < tblView.getItems().size(); i++) {
            calTotal += (Double) columenPrice.getCellData(i);
        }
        lblTotal.setText(String.valueOf(calTotal));
    }


    @FXML
    void btnCleanONAction(ActionEvent event) {
        cleanAll();

    }


    @FXML
    void btnMakeAppointmentOnAction(ActionEvent event) {
        String appointmentId = lblAppointmentId.getText();
        String nic = cmbCustomerId.getValue();
        String time = String.valueOf(txtTime.getValue());
        String date = String.valueOf(txtDate.getValue());
        ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS = new ArrayList<>();
        for (int i = 0; i < tblView.getItems().size(); i++) {
            AppointmentTM tm = observableList.get(i);
            serviceAppointmentDetailDTOS.add(new ServiceAppointmentDetailDTO(tm.getAptId(), tm.getSevId()));
        }
        AppointmentDTO appointmentDTO = new AppointmentDTO(appointmentId, date, time, nic);

        try {
            boolean addAppointment = appointmentBO.addAppointment(appointmentDTO, serviceAppointmentDetailDTOS);
            if (addAppointment) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "AppointmentDTO added");
                alert.show();
                setNextId();
                cleanAll();
                observableList.clear();


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "AppointmentDTO not added");
                alert.show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setNextId();
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

    private void setNextId() {
        try {
            String currentId = appointmentBO.checkIdAppointment();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.APPOINTMENT);
            lblAppointmentId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
//        new Pulse(lblBar).play();
        setNextId();
        loadCmb();

        columenAppointmentId.setCellValueFactory(new PropertyValueFactory<>("aptId"));
        columenServiceId.setCellValueFactory(new PropertyValueFactory<>("sevId"));
        columenEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columenCustomerId.setCellValueFactory(new PropertyValueFactory<>("nic"));
        columenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columenTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columenDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        columenPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columenAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        txtTime.setValue(LocalTime.parse(currentTime()));
        currentDate();
    }

    private void cleanAll() {
        cmbEmployeeId.setValue(null);
        cmbServiceId.setValue(null);
        lblServiceName.setText(null);
        lblPrice.setText(null);
        lblDiscount.setText(null);
        lblEmpName.setText(null);
        lblEmpDescription.setText(null);
        lblTotal.setText(null);
        cmbCustomerId.setValue(null);
        lblCustomerPhone.setText(null);
        lblCustomerName.setText(null);
//        lblCustomerNIC.setText(null);
        currentDate();
        txtTime.setValue(LocalTime.parse(currentTime()));
        tblView.getItems().clear();
    }

    private String currentTime() {
        String format = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        return format;
    }

    private void currentDate() {
        txtDate.setValue(LocalDate.now());
    }

    private void loadCmb() {
        try {
            ArrayList<ServiceDTO> allServiceDTO = getAllService();
            String[] ids;
            if (allServiceDTO.size() != 0) {
                ids = new String[allServiceDTO.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(allServiceDTO.get(i).getSevId());
                }
                cmbServiceId.getItems().addAll(ids);
            }

            ArrayList<CustomerDTO> customerDTOS = getAllCustomer();
            if (customerDTOS.size() != 0) {
                ids = new String[customerDTOS.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(customerDTOS.get(i).getNic());
                }
                cmbCustomerId.getItems().addAll(ids);
            }

            ArrayList<EmployeeDTO> employeeDTOS = appointmentBO.getBeauticians();
            if (employeeDTOS.size() != 0) {
                ids = new String[employeeDTOS.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(employeeDTOS.get(i).getEmpId());
                }
                cmbEmployeeId.getItems().addAll(ids);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String value = cmbCustomerId.getValue();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setNic(value);
        try {
            ArrayList<CustomerDTO> customerDTOS = searchCustomerDetails(customerDTO);
            if (customerDTOS.size() > 0) {
                for (CustomerDTO c : customerDTOS) {
                    lblCustomerName.setText(c.getName());
                    lblCustomerPhone.setText(c.getPhoneNumber());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private ArrayList<CustomerDTO> searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return appointmentBO.searchCustomerDetails(customerDTO);
    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        String value = cmbServiceId.getValue();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setSevId(value);
        try {
            ArrayList<ServiceDTO> serviceDTOS = searchServiceDetails(serviceDTO);
            if (serviceDTOS.size() > 0) {
                for (ServiceDTO s : serviceDTOS) {
                    lblServiceName.setText(s.getName());
                    lblPrice.setText(String.valueOf(s.getPrice()));
                    lblDiscount.setText(String.valueOf(s.getDiscount()));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
//            }
        }
    }

    private ArrayList<ServiceDTO> searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return appointmentBO.searchServiceDetails(serviceDTO);
    }

    public void cmbEmployeeIdOnAction(ActionEvent actionEvent) {
        String value = cmbEmployeeId.getValue();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpId(value);
        try {
            ArrayList<EmployeeDTO> employeeDTOS = searchEmployeeDetails(employeeDTO);
            if (employeeDTOS.size() > 0) {
                for (EmployeeDTO e : employeeDTOS) {
                    lblEmpName.setText(e.getName());
                    lblEmpDescription.setText(e.getDescription());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private ArrayList<EmployeeDTO> searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return appointmentBO.searchEmployeeDetails(employeeDTO);
    }
    private ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        return appointmentBO.getAllServices();
    }
    private ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return appointmentBO.getAllCustomers();
    }
}
