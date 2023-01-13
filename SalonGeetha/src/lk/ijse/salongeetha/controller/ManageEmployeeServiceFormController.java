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
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.EmployeeDAO;
import lk.ijse.salongeetha.model.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.model.castom.QueryDAO;
import lk.ijse.salongeetha.model.castom.ServiceDAO;
import lk.ijse.salongeetha.model.castom.impl.EmployeeModel;
import lk.ijse.salongeetha.model.castom.impl.EmployeeServiceModel;
import lk.ijse.salongeetha.model.castom.impl.QueryDAOImpl;
import lk.ijse.salongeetha.model.castom.impl.ServiceModel;
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
    ServiceDAO serviceDAO = new ServiceModel();
    EmployeeDAO employeeDAO = new EmployeeModel();
    EmployeeServiceDAO employeeServiceDAO = new EmployeeServiceModel();
    QueryDAO queryDAO = new QueryDAOImpl();

    @FXML
    void btnAddONAction(ActionEvent event) {
        String cmbEmpIdValue = cmbEmpId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String serviceNameText = lblServiceName.getText();
        String empNameText = lblEmpName.getText();

        EmployeeServiceDetail employeeServiceDetail = new EmployeeServiceDetail(cmbEmpIdValue, serviceIdValue, serviceNameText, empNameText);

        try {
            boolean checkAlreadyExists = employeeServiceDAO.checkAlreadyExists(employeeServiceDetail);
            if (!checkAlreadyExists) {
                boolean addProductService = employeeServiceDAO.add(employeeServiceDetail);
                if (addProductService) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully added");
                    alert.show();
                    tblView.getItems().clear();
                    employeeServiceDetailArrayList = getAllEmployeeServiceDetails();
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
            ArrayList<Service> services = getAllService();
            String[] ids;

            if (services.size() != 0) {
                ids = new String[services.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(services.get(i).getSevId());
                }
                cmbServiceId.getItems().addAll(ids);
            }


            ArrayList<Employee> employees = getAllEmployee();
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
            employeeServiceDetailArrayList = getAllEmployeeServiceDetails();

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
                        boolean isDeleted = employeeServiceDAO.delete(employeeServiceDetail);
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
            ArrayList<Employee> employees = searchEmployee(employee);
            if (employees.size() > 0) {
                for (Employee e : employees) {
                    lblEmpName.setText(e.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> searchEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employee.getName());
        ResultSet resultSet = employeeDAO.search(matcher.matches(), employee);
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployee.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployee.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployee.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployee.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployee.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployee.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployee.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employees.add(searchEmployee);
        }
        return employees;
    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        this.serviceId = cmbServiceId.getValue();
        Service service = new Service();
        service.setName(serviceId);
        try {
            ArrayList<Service> services = searchService(service);
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

    private ArrayList<Service> searchService(Service service) throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(service.getName());
        ResultSet resultSet = serviceDAO.search(matcher.matches(), service);
        while (resultSet.next()) {
            Service searchService = new Service();
            searchService.setSevId(String.valueOf(resultSet.getObject(1)));
            searchService.setDescription(String.valueOf(resultSet.getObject(2)));
            searchService.setName(String.valueOf(resultSet.getObject(3)));
            searchService.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            searchService.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            services.add(searchService);
        }
        return services;
    }

    private ArrayList<Service> getAllService() throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        ResultSet resultSet = serviceDAO.getAll();
        while (resultSet.next()) {
            Service service = new Service();
            service.setSevId(String.valueOf(resultSet.getObject(1)));
            service.setDescription(String.valueOf(resultSet.getObject(2)));
            service.setName(String.valueOf(resultSet.getObject(3)));
            service.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            service.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            services.add(service);
        }
        return services;
    }

    private ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = employeeDAO.getAll();
        if (resultSet.next()) {
            do {
                Employee employee = new Employee();
                employee.setEmpId(String.valueOf(resultSet.getObject(1)));
                employee.setName(String.valueOf(resultSet.getObject(2)));
                employee.setAddress(String.valueOf(resultSet.getObject(3)));
                employee.setDob(String.valueOf(resultSet.getObject(4)));
                employee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
                employee.setDescription(String.valueOf(resultSet.getObject(6)));
                employee.setEmail(String.valueOf(resultSet.getObject(7)));
                employee.setNic(String.valueOf(resultSet.getObject(8)));
                employee.setJobTitle(String.valueOf(resultSet.getObject(9)));
                employees.add(employee);
            } while (resultSet.next());
            return employees;
        }
        return new ArrayList<>();
    }

    private ArrayList<EmployeeServiceDetail> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeServiceDetail> employeeServiceDetails = new ArrayList<>();
        ResultSet resultSet = queryDAO.getAllEmployeeServiceDetails();
        while (resultSet.next()) {
            EmployeeServiceDetail employeeServiceDetail = new EmployeeServiceDetail();
            employeeServiceDetail.setEmpId(String.valueOf(resultSet.getObject(1)));
            employeeServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            employeeServiceDetail.setEmpName(String.valueOf(resultSet.getObject(3)));
            employeeServiceDetail.setSevName(String.valueOf(resultSet.getObject(4)));
            employeeServiceDetails.add(employeeServiceDetail);
        }
        return employeeServiceDetails;
    }
}
