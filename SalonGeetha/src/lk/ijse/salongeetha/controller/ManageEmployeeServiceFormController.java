package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.EmployeeServiceBO;
import lk.ijse.salongeetha.to.*;
import lk.ijse.salongeetha.to.tm.EmployeeServiceTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageEmployeeServiceFormController {

    @FXML
    private GridPane rigthPane;

    @FXML
    private JFXButton btnManageEmployee;

    @FXML
    private JFXButton btnEmployeeService;

    @FXML
    private TableView<EmployeeServiceTM> tblView;

    @FXML
    private TableColumn<?, ?> columnEmpId;

    @FXML
    private TableColumn<?, ?> columnServiceId;

    @FXML
    private TableColumn<?, ?> columnEmpName;

    @FXML
    private TableColumn<?, ?> columnServiceName;

    @FXML
    private TableColumn<?, ?> columnAction;

    @FXML
    private TableColumn<?, ?> columnUpdate;

    @FXML
    private TableColumn<?, ?> columnDelete;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private Label lblEmpName;

    @FXML
    private JFXComboBox<String> cmbServiceId;

    @FXML
    private Label lblServiceName;
    private String serviceId;
    private String employeeId;
    EmployeeServiceBO employeeServiceBO= (EmployeeServiceBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.EMPLOYEE_SERVICE);

    @FXML
    void btnAddONAction(ActionEvent event) {
        String cmbEmpIdValue = cmbEmpId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String serviceNameText = lblServiceName.getText();
        String empNameText = lblEmpName.getText();

        EmployeeServiceDetailDTO employeeServiceDetailDTO = new EmployeeServiceDetailDTO(cmbEmpIdValue, serviceIdValue, serviceNameText, empNameText);

        try {
            boolean checkAlreadyExists = employeeServiceBO.checkAlreadyExists(employeeServiceDetailDTO);
            if (!checkAlreadyExists) {
                boolean addProductService = employeeServiceBO.addEmployeeAndServiceDetail(employeeServiceDetailDTO);
                if (addProductService) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully added");
                    alert.show();
                    tblView.getItems().clear();
                    employeeServiceDetailDTOArrayList = getAllEmployeeServiceDetails();
                    loadAllData();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "This values is already exists");
                alert.show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void initialize() {
        try {
            ArrayList<ServiceDTO> serviceDTOS = getAllService();
            String[] ids;

            if (serviceDTOS.size() != 0) {
                ids = new String[serviceDTOS.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(serviceDTOS.get(i).getSevId());
                }
                cmbServiceId.getItems().addAll(ids);
            }


            ArrayList<EmployeeDTO> employeeDTOS = getAllEmployee();
            String[] eIds;
            if (employeeDTOS.size() != 0) {
                eIds = new String[employeeDTOS.size()];
                for (int i = 0; i < eIds.length; i++) {
                    eIds[i] = String.valueOf(employeeDTOS.get(i).getEmpId());
                }
                cmbEmpId.getItems().addAll(eIds);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        columnServiceId.setCellValueFactory(new PropertyValueFactory<>("sevId"));
        columnEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columnEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        columnServiceName.setCellValueFactory(new PropertyValueFactory<>("sevName"));
        columnAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        loadAllData();
    }

    ArrayList<EmployeeServiceDetailDTO> employeeServiceDetailDTOArrayList;

    {
        try {
            employeeServiceDetailDTOArrayList = getAllEmployeeServiceDetails();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<EmployeeServiceTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (EmployeeServiceDetailDTO es : employeeServiceDetailDTOArrayList) {
            JFXButton delete = new JFXButton("Delete");
            delete.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
            delete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    EmployeeServiceTM tm = tblView.getSelectionModel().getSelectedItem();

                    String empId = tm.getEmpId();
                    String sevId = tm.getSevId();
                    EmployeeServiceDetailDTO employeeServiceDetailDTO = new EmployeeServiceDetailDTO();

                    try {
                        employeeServiceDetailDTO.setEmpId(empId);
                        employeeServiceDetailDTO.setSevId(sevId);
                        boolean isDeleted = employeeServiceBO.deleteEmployeeAndServiceDetail(employeeServiceDetailDTO);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "delete successful");
                            alert1.show();
//                            setNextId();

                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }


                    tblView.getItems().removeAll(tm);
                }
            });
//            update.setOnAction((e) -> {
//                BillPaymentTM tm = tblView.getSelectionModel().getSelectedItem();
//                btnUpdate.setVisible(true);
//                btnAdd.setVisible(false);
//                lblBillId.setText(tm.getBilId());
//                cmbSelectEmployeeId.setValue(tm.getEmpId());
//                txtDate.setValue(LocalDate.parse(tm.getDate()));
//                txtTitel.setText(tm.getTitle());
//                txtAmount.setText(String.valueOf(tm.getAmountPaid()));
//                txtDescription.setText(tm.getDescription());
//
//            });
            EmployeeServiceTM employeeServiceTM = new EmployeeServiceTM(es.getEmpId(), es.getEmpName(), es.getSevId(), es.getSevName(), delete);
            observableList.add(employeeServiceTM);
            tblView.setItems(observableList);
        }
    }

    public void cmbEmpIdOnAction(ActionEvent actionEvent) {
        this.employeeId = cmbEmpId.getValue();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employeeId);
        try {
            ArrayList<EmployeeDTO> employeeDTOS = searchEmployee(employeeDTO);
            if (employeeDTOS.size() > 0) {
                for (EmployeeDTO e : employeeDTOS) {
                    lblEmpName.setText(e.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<EmployeeDTO> searchEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employeeDTO.getName());
        ResultSet resultSet = employeeServiceBO.searchEmployee(matcher.matches(), employeeDTO);
        while (resultSet.next()) {
            EmployeeDTO searchEmployeeDTO = new EmployeeDTO();
            searchEmployeeDTO.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployeeDTO.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployeeDTO.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployeeDTO.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployeeDTO.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployeeDTO.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployeeDTO.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployeeDTO.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployeeDTO.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employeeDTOS.add(searchEmployeeDTO);
        }
        return employeeDTOS;
    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        this.serviceId = cmbServiceId.getValue();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setName(serviceId);
        try {
            ArrayList<ServiceDTO> serviceDTOS = searchService(serviceDTO);
            if (serviceDTOS.size() > 0) {
                for (ServiceDTO s : serviceDTOS) {
                    lblServiceName.setText(s.getName());
                    System.out.println(s.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<ServiceDTO> searchService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(serviceDTO.getName());
        ResultSet resultSet = employeeServiceBO.searchService(matcher.matches(), serviceDTO);
        while (resultSet.next()) {
            ServiceDTO searchServiceDTO = new ServiceDTO();
            searchServiceDTO.setSevId(String.valueOf(resultSet.getObject(1)));
            searchServiceDTO.setDescription(String.valueOf(resultSet.getObject(2)));
            searchServiceDTO.setName(String.valueOf(resultSet.getObject(3)));
            searchServiceDTO.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            searchServiceDTO.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            serviceDTOS.add(searchServiceDTO);
        }
        return serviceDTOS;
    }

    private ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        ResultSet resultSet = employeeServiceBO.getAllService();
        while (resultSet.next()) {
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setSevId(String.valueOf(resultSet.getObject(1)));
            serviceDTO.setDescription(String.valueOf(resultSet.getObject(2)));
            serviceDTO.setName(String.valueOf(resultSet.getObject(3)));
            serviceDTO.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            serviceDTO.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            serviceDTOS.add(serviceDTO);
        }
        return serviceDTOS;
    }

    private ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ResultSet resultSet = employeeServiceBO.getAllEmployee();
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

    private ArrayList<EmployeeServiceDetailDTO> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeServiceDetailDTO> employeeServiceDetailDTOS = new ArrayList<>();
        ResultSet resultSet = employeeServiceBO.getAllEmployeeServiceDetails();
        while (resultSet.next()) {
            EmployeeServiceDetailDTO employeeServiceDetailDTO = new EmployeeServiceDetailDTO();
            employeeServiceDetailDTO.setEmpId(String.valueOf(resultSet.getObject(1)));
            employeeServiceDetailDTO.setSevId(String.valueOf(resultSet.getObject(2)));
            employeeServiceDetailDTO.setEmpName(String.valueOf(resultSet.getObject(3)));
            employeeServiceDetailDTO.setSevName(String.valueOf(resultSet.getObject(4)));
            employeeServiceDetailDTOS.add(employeeServiceDetailDTO);
        }
        return employeeServiceDetailDTOS;
    }
}
