package lk.ijse.salongeetha.controller;

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
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.ServiceModel;
import lk.ijse.salongeetha.to.Service;
import lk.ijse.salongeetha.to.tm.ServiceTM;
import lk.ijse.salongeetha.util.GenerateId;
import lk.ijse.salongeetha.util.IdTypes;
import lk.ijse.salongeetha.util.Validation;
import lk.ijse.salongeetha.util.ValidityCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.RED;

public class ManageServiceController {

    public JFXTextField txtName;
    public JFXButton btnClean;
    public Label lblServiceId;
    public TableColumn<?, ?> columenServiceId;
    public TableColumn<?, ?> columenName;
    public TableColumn<?, ?> columenPrice;
    public TableColumn<?, ?> columenDescription;
    public TableColumn<?, ?> columenDiscount;
    public TextField txtSearch;
    public Label lblVName;
    public Label lblVDiscount;
    public Label lblVPrice;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<ServiceTM> tblView;


    @FXML
    private TableColumn<?, ?> columenAction;


    @FXML
    private Label lblname;

    @FXML
    private Label lblSalaryId12;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private Label lblSalaryId1211;

    @FXML
    private JFXTextArea txtDescription;
    ArrayList<Service> serviceArrayList;
    ServiceDAO serviceDAO = new ServiceModel();

    {
        try {
            serviceArrayList = getAllService();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<ServiceTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Service s : serviceArrayList) {
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
                    ServiceTM tm = tblView.getSelectionModel().getSelectedItem();

                    String serviceId = tm.getSevId();
                    Service service = new Service();
//                    if(!tm.getJobTitle().equals("Receptionist")) {

                    service.setSevId(serviceId);
                    try {
                        boolean isDeleted = serviceDAO.delete(service);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Service delete successfully");
                            alert1.show();
                            setNextId();

                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }


                    tblView.getItems().removeAll(tm);
                }
            });
            ServiceTM serviceTM = new ServiceTM(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount(), delete, update);
            observableList.add(serviceTM);
            tblView.setItems(observableList);
        }
    }

    public void btnSearchONAction(ActionEvent actionEvent) {
        search();
    }

    public void btnCleanONAction(ActionEvent actionEvent) {
        cleanAll();
    }

    private void cleanAll() {
        txtPrice.setText("");
        txtDescription.setText("");
        txtName.setText("");
        txtDiscount.setText("");
        txtSearch.setText("");
        lblVPrice.setText(null);
        lblVName.setText(null);
        lblVDiscount.setText(null);
    }

    private void setNextId() {
        try {

            String currentId = serviceDAO.checkId();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.SERVICE);
            lblServiceId.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        columenServiceId.setCellValueFactory(new PropertyValueFactory<>("sevId"));
        columenName.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenPrice.setCellValueFactory(new PropertyValueFactory<>("name"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("price"));
        columenDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        columenAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        loadAllData();

//        new Pulse(lblBar).play();
        setNextId();
    }

    public void btnNextONAction(ActionEvent actionEvent) {
        String serviceId = lblServiceId.getText();
        String description = txtDescription.getText();
        String discount = txtDiscount.getText();
        String price = txtPrice.getText();
        String name = txtName.getText();
        if (ValidityCheck.check(Validation.NAME, name)) {
            if (Double.parseDouble(price) >= 0) {

                Service service = new Service(serviceId, description, name, Double.parseDouble(price), Double.parseDouble(discount));
                try {
                    boolean addService = serviceDAO.add(service);
                    if (addService) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Service is added");
                        alert.show();
                        setNextId();
                        cleanAll();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Error");
                        alert.show();
                    }
                    tblView.getItems().clear();
                    serviceArrayList = getAllService();
                    loadAllData();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                lblVPrice.setText("Incorrect value");
                txtPrice.setFocusColor(RED);
                txtPrice.requestFocus();
            }
        } else {
            lblVName.setText("Invalid name");
            txtName.setFocusColor(RED);
            txtName.requestFocus();
        }
    }

    private Service service = new Service();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            service.setName(text);
            try {
                serviceArrayList = search(service);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }
    private ArrayList<Service> search(Service service) throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(service.getName());
        ResultSet resultSet = serviceDAO.search(matcher.matches(),service);
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

    public void cleanTable() {
        try {
            tblView.getItems().clear();
            serviceArrayList = getAllService();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
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
}
