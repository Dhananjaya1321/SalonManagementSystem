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
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeModel;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeServiceModel;
import lk.ijse.salongeetha.dao.castom.impl.ServiceModel;
import lk.ijse.salongeetha.to.*;
import lk.ijse.salongeetha.to.tm.EmployeeServiceTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    ServiceDAO serviceDAO = new ServiceModel();
    EmployeeDAO employeeDAO = new EmployeeModel();

    @FXML
    void btnAddONAction(ActionEvent event) {
        String cmbEmpIdValue = cmbEmpId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String serviceNameText = lblServiceName.getText();
        String empNameText = lblEmpName.getText();

        EmployeeServiceDetail employeeServiceDetail = new EmployeeServiceDetail(cmbEmpIdValue, serviceIdValue, serviceNameText, empNameText);

        try {
            boolean checkAlreadyExists = EmployeeServiceModel.checkAlreadyExists(employeeServiceDetail);
            if (!checkAlreadyExists) {
                boolean addProductService = EmployeeServiceModel.addEmployeeService(employeeServiceDetail);
                if (addProductService) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully added");
                    alert.show();
                    tblView.getItems().clear();
                    employeeServiceDetailArrayList = EmployeeServiceModel.getAllEmployeeServiceDetails();
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
            ArrayList<Service> services = serviceDAO.getAll();
            String[] ids;

            if (services.size() != 0) {
                ids = new String[services.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(services.get(i).getSevId());
                }
                cmbServiceId.getItems().addAll(ids);
            }


            ArrayList<Employee> employees = employeeDAO.getAll();
            String[] eIds;
            if (employees.size() != 0) {
                eIds = new String[employees.size()];
                for (int i = 0; i < eIds.length; i++) {
                    eIds[i] = String.valueOf(employees.get(i).getEmpId());
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

    ArrayList<EmployeeServiceDetail> employeeServiceDetailArrayList;

    {
        try {
            employeeServiceDetailArrayList = EmployeeServiceModel.getAllEmployeeServiceDetails();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<EmployeeServiceTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (EmployeeServiceDetail es : employeeServiceDetailArrayList) {
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
                    EmployeeServiceDetail employeeServiceDetail = new EmployeeServiceDetail();

                    try {
                        employeeServiceDetail.setEmpId(empId);
                        employeeServiceDetail.setSevId(sevId);
                        boolean isDeleted = EmployeeServiceModel.deleteEmployeeService(employeeServiceDetail);
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
        Employee employee = new Employee();
        employee.setName(employeeId);
        try {
            ArrayList<Employee> employees = employeeDAO.search(employee);
            if (employees.size() > 0) {
                for (Employee e : employees) {
                    lblEmpName.setText(e.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        this.serviceId = cmbServiceId.getValue();
        Service service = new Service();
        service.setName(serviceId);
        try {
            ArrayList<Service> services = serviceDAO.search(service);
            if (services.size() > 0) {
                for (Service s : services) {
                    lblServiceName.setText(s.getName());
                    System.out.println(s.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
