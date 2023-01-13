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
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.ProductModel;
import lk.ijse.salongeetha.dao.castom.impl.ProductServiceModel;
import lk.ijse.salongeetha.dao.castom.impl.ServiceModel;
import lk.ijse.salongeetha.to.Product;
import lk.ijse.salongeetha.to.ProductServiceDetail;
import lk.ijse.salongeetha.to.Service;
import lk.ijse.salongeetha.to.tm.ProductServiceTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageProductServiceFormController {

    public TextField txtQty;
    @FXML
    private GridPane rigthPane;

    @FXML
    private TableView<ProductServiceTM> tblView;

    @FXML
    private TableColumn<?, ?> columnServiceId;

    @FXML
    private TableColumn<?, ?> columnProId;

    @FXML
    private TableColumn<?, ?> columnServiceName;

    @FXML
    private TableColumn<?, ?> columnQty;

    @FXML
    private TableColumn<?, ?> columnAction;

    @FXML
    private TableColumn<?, ?> columnUpdate;

    @FXML
    private TableColumn<?, ?> columnDelete;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbProductId;

    @FXML
    private JFXComboBox<String> cmbServiceId;

    @FXML
    private Label lblServiceName;
    private String serviceId;
    private String productId;
    ProductDAO productDAO=new ProductModel();
    ServiceDAO serviceDAO=new ServiceModel();
    ProductServiceDAO productServiceDAO=new ProductServiceModel();
    @FXML
    void btnAddONAction(ActionEvent event) {
        String productIdValue = cmbProductId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String serviceNameText = lblServiceName.getText();
        int txtQtyText = Integer.parseInt(txtQty.getText());
        ProductServiceDetail productServiceDetail = new ProductServiceDetail(productIdValue, serviceIdValue, serviceNameText, txtQtyText);

        try {
            boolean checkAlreadyExists = productServiceDAO.checkAlreadyExists(productServiceDetail);
            if (!checkAlreadyExists) {
                 boolean addProductService = productServiceDAO.add(productServiceDetail);
                if (addProductService) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully added");
                    alert.show();
                    tblView.getItems().clear();
                    productServiceDetailArrayList = getAllProductServiceDetail();
                    loadAllData();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "This values is already exists");
                alert.show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveONAction(ActionEvent event) {

    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        this.serviceId = cmbServiceId.getValue();
        Service service = new Service();
        service.setName(serviceId);
        try {
            ArrayList<Service> services = search(service);
            if (services.size() > 0) {
                for (Service s : services) {
                    lblServiceName.setText(s.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
    public void cmbProductIdOnAction(ActionEvent actionEvent) {
        this.productId = cmbProductId.getValue();
    }

    public void initialize() {
        btnSave.setVisible(false);
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


            ArrayList<Product> products = getAllProduct();
            String[] Pids;
            if (products.size() != 0) {
                Pids = new String[products.size()];
                for (int i = 0; i < Pids.length; i++) {
                    Pids[i] = String.valueOf(products.get(i).getProId());
                }
                cmbProductId.getItems().addAll(Pids);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        columnServiceId.setCellValueFactory(new PropertyValueFactory<>("proId"));
        columnProId.setCellValueFactory(new PropertyValueFactory<>("sevId"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnServiceName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
//            columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        loadAllData();
    }

    ArrayList<ProductServiceDetail> productServiceDetailArrayList;

    {
        try {
            productServiceDetailArrayList = getAllProductServiceDetail();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<ProductServiceTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (ProductServiceDetail p : productServiceDetailArrayList) {
            JFXButton delete = new JFXButton("Delete");
            delete.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
            delete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    ProductServiceTM tm = tblView.getSelectionModel().getSelectedItem();

                    String proId = tm.getProId();
                    String sevId = tm.getSevId();
                    ProductServiceDetail productServiceDetail = new ProductServiceDetail();

                    try {
                        productServiceDetail.setProId(proId);
                        productServiceDetail.setSevId(sevId);
                        boolean isDeleted = productServiceDAO.delete(productServiceDetail);
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
            ProductServiceTM productServiceTM = new ProductServiceTM(p.getProId(), p.getSevId(), p.getQty(), p.getName(), delete);
            observableList.add(productServiceTM);
            tblView.setItems(observableList);
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
    private ArrayList<ProductServiceDetail> getAllProductServiceDetail() throws SQLException, ClassNotFoundException {
        ArrayList<ProductServiceDetail> productServiceDetails = new ArrayList<>();
        ResultSet resultSet = productServiceDAO.getAll();
        while (resultSet.next()) {
            ProductServiceDetail productServiceDetail = new ProductServiceDetail();
            productServiceDetail.setProId(String.valueOf(resultSet.getObject(1)));
            productServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            productServiceDetail.setQty((Integer) resultSet.getObject(3));
            productServiceDetail.setName(String.valueOf(resultSet.getObject(4)));
            productServiceDetails.add(productServiceDetail);
        }
        return productServiceDetails;
    }
    private ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet resultSet = productDAO.getAll();
        while (resultSet.next()) {
            Product product = new Product();
            product.setProId(String.valueOf(resultSet.getObject(1)));
            product.setDescription(String.valueOf(resultSet.getObject(2)));
            product.setCategory(String.valueOf(resultSet.getObject(3)));
            product.setBrand(String.valueOf(resultSet.getObject(4)));
            product.setUnitPrice((Double) resultSet.getObject(5));
            product.setQtyOnHand((Integer) resultSet.getObject(6));
            product.setSupId(String.valueOf(resultSet.getObject(7)));
            products.add(product);
        }
        return products;
    }
}
