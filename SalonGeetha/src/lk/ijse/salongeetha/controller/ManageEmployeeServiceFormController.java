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
import lk.ijse.salongeetha.dto.*;
import lk.ijse.salongeetha.view.tm.EmployeeServiceTM;

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
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employeeDTO.getName());
        return employeeServiceBO.searchEmployee(matcher.matches(), employeeDTO);

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
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(serviceDTO.getName());
        return employeeServiceBO.searchService(matcher.matches(), serviceDTO);
    }

    private ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        return employeeServiceBO.getAllService();
    }

    private ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeServiceBO.getAllEmployee();
    }

    private ArrayList<EmployeeServiceDetailDTO> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        return employeeServiceBO.getAllEmployeeServiceDetails();
    }
}
