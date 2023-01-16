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
import lk.ijse.salongeetha.bo.castom.ProductServiceBO;
import lk.ijse.salongeetha.to.ProductDTO;
import lk.ijse.salongeetha.to.ProductServiceDetailDTO;
import lk.ijse.salongeetha.to.ServiceDTO;
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
    ProductServiceBO productServiceBO = (ProductServiceBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.PRODUCT_SERVICE);

    @FXML
    void btnAddONAction(ActionEvent event) {
        String productIdValue = cmbProductId.getValue();
        String serviceIdValue = cmbServiceId.getValue();
        String serviceNameText = lblServiceName.getText();
        int txtQtyText = Integer.parseInt(txtQty.getText());
        ProductServiceDetailDTO productServiceDetailDTO = new ProductServiceDetailDTO(productIdValue, serviceIdValue, serviceNameText, txtQtyText);

        try {
            boolean checkAlreadyExists = productServiceBO.checkAlreadyExists(productServiceDetailDTO);
            if (!checkAlreadyExists) {
                boolean addProductService = productServiceBO.addProductAndServiceDetail(productServiceDetailDTO);
                if (addProductService) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "successfully added");
                    alert.show();
                    tblView.getItems().clear();
                    productServiceDetailDTOArrayList = getAllProductServiceDetail();
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

    @FXML
    void btnSaveONAction(ActionEvent event) {

    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        this.serviceId = cmbServiceId.getValue();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setName(serviceId);
        try {
            ArrayList<ServiceDTO> serviceDTOS = search(serviceDTO);
            if (serviceDTOS.size() > 0) {
                for (ServiceDTO s : serviceDTOS) {
                    lblServiceName.setText(s.getName());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<ServiceDTO> search(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(serviceDTO.getName());
        ResultSet resultSet = productServiceBO.searchService(matcher.matches(), serviceDTO);
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

    public void cmbProductIdOnAction(ActionEvent actionEvent) {
        this.productId = cmbProductId.getValue();
    }

    public void initialize() {
        btnSave.setVisible(false);
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


            ArrayList<ProductDTO> productDTOS = getAllProduct();
            String[] Pids;
            if (productDTOS.size() != 0) {
                Pids = new String[productDTOS.size()];
                for (int i = 0; i < Pids.length; i++) {
                    Pids[i] = String.valueOf(productDTOS.get(i).getProId());
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

    ArrayList<ProductServiceDetailDTO> productServiceDetailDTOArrayList;

    {
        try {
            productServiceDetailDTOArrayList = getAllProductServiceDetail();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<ProductServiceTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (ProductServiceDetailDTO p : productServiceDetailDTOArrayList) {
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
                    ProductServiceDetailDTO productServiceDetailDTO = new ProductServiceDetailDTO();

                    try {
                        productServiceDetailDTO.setProId(proId);
                        productServiceDetailDTO.setSevId(sevId);
                        boolean isDeleted = productServiceBO.deleteProductAndServiceDetail(productServiceDetailDTO);
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

    private ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        ResultSet resultSet = productServiceBO.getAllService();
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

    private ArrayList<ProductServiceDetailDTO> getAllProductServiceDetail() throws SQLException, ClassNotFoundException {
        ArrayList<ProductServiceDetailDTO> productServiceDetailDTOS = new ArrayList<>();
        ResultSet resultSet = productServiceBO.getAllProductAndServiceDAO();
        while (resultSet.next()) {
            ProductServiceDetailDTO productServiceDetailDTO = new ProductServiceDetailDTO();
            productServiceDetailDTO.setProId(String.valueOf(resultSet.getObject(1)));
            productServiceDetailDTO.setSevId(String.valueOf(resultSet.getObject(2)));
            productServiceDetailDTO.setQty((Integer) resultSet.getObject(3));
            productServiceDetailDTO.setName(String.valueOf(resultSet.getObject(4)));
            productServiceDetailDTOS.add(productServiceDetailDTO);
        }
        return productServiceDetailDTOS;
    }

    private ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        ResultSet resultSet = productServiceBO.getAllProduct();
        while (resultSet.next()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProId(String.valueOf(resultSet.getObject(1)));
            productDTO.setDescription(String.valueOf(resultSet.getObject(2)));
            productDTO.setCategory(String.valueOf(resultSet.getObject(3)));
            productDTO.setBrand(String.valueOf(resultSet.getObject(4)));
            productDTO.setUnitPrice((Double) resultSet.getObject(5));
            productDTO.setQtyOnHand((Integer) resultSet.getObject(6));
            productDTO.setSupId(String.valueOf(resultSet.getObject(7)));
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
