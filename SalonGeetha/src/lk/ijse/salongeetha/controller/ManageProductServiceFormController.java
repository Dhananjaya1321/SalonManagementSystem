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
import lk.ijse.salongeetha.dto.ProductDTO;
import lk.ijse.salongeetha.dto.ProductServiceDetailDTO;
import lk.ijse.salongeetha.dto.ServiceDTO;
import lk.ijse.salongeetha.view.tm.ProductServiceTM;

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
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(serviceDTO.getName());
        return productServiceBO.searchService(matcher.matches(), serviceDTO);
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
        return productServiceBO.getAllService();
    }

    private ArrayList<ProductServiceDetailDTO> getAllProductServiceDetail() throws SQLException, ClassNotFoundException {
        return productServiceBO.getAllProductAndServiceDAO();
    }

    private ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        return productServiceBO.getAllProduct();
    }
}
