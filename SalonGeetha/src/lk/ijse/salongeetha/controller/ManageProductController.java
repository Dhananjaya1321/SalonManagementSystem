package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.ProductDAO;
import lk.ijse.salongeetha.model.castom.SupplierDAO;
import lk.ijse.salongeetha.model.castom.impl.ProductModel;
import lk.ijse.salongeetha.model.castom.impl.SupplierModel;
import lk.ijse.salongeetha.to.Product;
import lk.ijse.salongeetha.to.Supplier;
import lk.ijse.salongeetha.to.tm.ProductTM;
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

public class ManageProductController {


    public JFXTextField txtProductId;
    public TableColumn columenDelete;
    public TableColumn columenUpdate;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public TextField txtSearch;
    public Label lblVBrand;
    public Label lblVPrice;
    public Label lblVQty;
    @FXML
    private GridPane rigthPane;

    @FXML
    private Label lblBar;

    @FXML
    private TableView<ProductTM> tblView;

    @FXML
    private TableColumn<?, ?> columenProductId;

    @FXML
    private TableColumn<?, ?> columenCategory;

    @FXML
    private TableColumn<?, ?> columenSupplierId;

    @FXML
    private TableColumn<?, ?> columenBrand;

    @FXML
    private TableColumn<?, ?> columenQty;

    @FXML
    private TableColumn<?, ?> columenUnitPrice;

    @FXML
    private TableColumn<?, ?> columenDescription;

    @FXML
    private TableColumn<?, ?> columenAction;

    @FXML
    private Label lblProduct;

    @FXML
    private JFXComboBox<String> cmbProductCatogary;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtQtyOnHand;
    ArrayList<Product> productArrayList;
    SupplierDAO supplierDAO=new SupplierModel();
    ProductDAO productDAO=new ProductModel();
    {
        try {
            productArrayList = getAllProduct();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<ProductTM> observableList = FXCollections.observableArrayList();

    private void loadAllData() {
        for (Product p : productArrayList) {
            JFXButton delete = new JFXButton("Delete");
            JFXButton update = new JFXButton("Update");
            update.setStyle("-fx-background-color: linear-gradient(to right, #17ff00, #12fe18, #0bfc25, #05fb2e, #00f936)");
            delete.setStyle("-fx-background-color: linear-gradient(to right, #fb0000, #fc0000, #fd0000, #fe0000, #ff0000)");
            delete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    ProductTM tm = tblView.getSelectionModel().getSelectedItem();

                    String productId = tm.getProId();
                    Product product = new Product();

                    product.setProId(productId);
                    try {
                        boolean isDeleted = productDAO.delete(product);
                        if (isDeleted) {
                            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Product delete successfully");
                            alert1.show();
                            setNextId();

                        }

                    } catch (SQLException | ClassNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }


                    tblView.getItems().removeAll(tm);
                }

            });
            update.setOnAction((e) -> {
                ProductTM tm = tblView.getSelectionModel().getSelectedItem();
                btnUpdate.setVisible(true);
                btnAdd.setVisible(false);

                lblProduct.setText(tm.getProId());
                cmbProductCatogary.setValue(tm.getCategory());
                txtDescription.setText(tm.getDescription());
                txtBrand.setText(String.valueOf(tm.getBrand()));
                txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
                cmbSupplierId.setValue(String.valueOf(tm.getSupId()));

            });
            ProductTM productTM = new ProductTM(p.getProId(), p.getDescription(), p.getCategory(), p.getBrand(), p.getSupId(), p.getUnitPrice(), p.getQtyOnHand(), delete, update);
            observableList.add(productTM);
            tblView.setItems(observableList);
        }
    }

    @FXML
    void btnAddONAction(ActionEvent event) {
        String productId = lblProduct.getText();
        String catogary = cmbProductCatogary.getValue();
        String supplierId = cmbSupplierId.getValue();
        String brand = txtBrand.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        String description = txtDescription.getText();
        if (ValidityCheck.check(Validation.NAME, brand)) {
            if (qtyOnHand >= 0) {
                if (unitPrice >= 0) {
                    Product product = new Product(productId, description, catogary, brand, supplierId, unitPrice, qtyOnHand);
                    try {
                        boolean addProduct = productDAO.add(product);
                        if (addProduct) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product collection is successful");
                            alert.show();
                            setNextId();
                            cleanAll();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Error");
                            alert.show();
                        }
                        tblView.getItems().clear();
                        productArrayList = getAllProduct();
                        loadAllData();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    txtUnitPrice.setFocusColor(RED);
                    txtUnitPrice.requestFocus();
                    lblVPrice.setText("Invalid price");
                }
            } else {
                txtQtyOnHand.setFocusColor(RED);
                txtQtyOnHand.requestFocus();
                lblVQty.setText("Invalid Qty");
            }
        } else {
            txtBrand.setFocusColor(RED);
            txtBrand.requestFocus();
            lblVBrand.setText("Invalid brand");
        }
    }

    public void btnUpdateONAction(ActionEvent actionEvent) {
        String productId = lblProduct.getText();
        String catogary = cmbProductCatogary.getValue();
        String supplierId = cmbSupplierId.getValue();
        String brand = txtBrand.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        String description = txtDescription.getText();
        if (ValidityCheck.check(Validation.NAME, brand)) {
            if (qtyOnHand >= 0) {
                if (unitPrice >= 0) {
                    Product product = new Product(productId, description, catogary, brand, supplierId, unitPrice, qtyOnHand);
                    try {
                        boolean updateProduct = productDAO.update(product);
                        if (updateProduct) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful");
                            alert.show();
                            setNextId();
                            cleanAll();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    txtUnitPrice.setFocusColor(RED);
                    txtUnitPrice.requestFocus();
                    lblVPrice.setText("Invalid price");
                }
            } else {
                txtQtyOnHand.setFocusColor(RED);
                txtQtyOnHand.requestFocus();
                lblVQty.setText("Invalid Qty");
            }
        } else {
            txtBrand.setFocusColor(RED);
            txtBrand.requestFocus();
            lblVBrand.setText("Invalid brand");
        }
    }

    public void btnCleanONAction(ActionEvent actionEvent) {
        cleanAll();
    }

    private void cleanAll() {
        txtDescription.setText("");
        txtBrand.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
        txtSearch.setText("");
        cmbSupplierId.setValue("");
        cmbProductCatogary.setValue("");
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        lblVPrice.setText(null);
        lblVBrand.setText(null);
        lblVQty.setText(null);
        try {
            tblView.getItems().clear();
            productArrayList = getAllProduct();
            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextId() {
        try {

            String currentId = productDAO.checkId();
            String generateNextId = GenerateId.generateNextId(currentId, IdTypes.PRODUCT);
            lblProduct.setText(generateNextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        setNextId();
        columenProductId.setCellValueFactory(new PropertyValueFactory<>("proId"));
        columenCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columenSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        columenBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columenQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        columenUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        columenDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columenDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        columenUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        cleanAll();

        String[] category = {"Shampoo", "Conditioner", "Leave in Deep conditioner", "Styling Products", "Hair spray", "Clay", "Wax", "Brushes", "Other"};
        cmbProductCatogary.getItems().addAll(category);

        try {
            ArrayList<Supplier> supplierIds = getAllSupplier();
            String[] ids;
            if (supplierIds.size() != 0) {
                ids = new String[supplierIds.size()];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = String.valueOf(supplierIds.get(i).getSupId());
                }
                cmbSupplierId.getItems().addAll(ids);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    private Product product = new Product();

    public void txtSearchOnAction(KeyEvent keyEvent) {
        search();
    }

    private void search() {
        String text = txtSearch.getText();
        if (!text.equals("")) {
            cleanTable();
            product.setBrand(text);
            try {
                productArrayList = search(product);
                loadAllData();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            cleanTable();
            loadAllData();
        }
    }

    public ArrayList<Product> search(Product product) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(product.getBrand());
        ResultSet resultSet = productDAO.search(matcher.matches(),product);
        while (resultSet.next()) {
            Product searchProduct = new Product();
            searchProduct.setProId(String.valueOf(resultSet.getObject(1)));
            searchProduct.setDescription(String.valueOf(resultSet.getObject(2)));
            searchProduct.setCategory(String.valueOf(resultSet.getObject(3)));
            searchProduct.setBrand(String.valueOf(resultSet.getObject(4)));
            searchProduct.setUnitPrice((Double) resultSet.getObject(5));
            searchProduct.setQtyOnHand((Integer) resultSet.getObject(6));
            searchProduct.setSupId(String.valueOf(resultSet.getObject(7)));
            products.add(searchProduct);
        }
        return products;
    }


    public void cleanTable() {
        try {
            tblView.getItems().clear();
            productArrayList = getAllProduct();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Product");
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
    private ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = supplierDAO.getAll();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupId(String.valueOf(resultSet.getObject(1)));
            supplier.setDescription(String.valueOf(resultSet.getObject(2)));
            supplier.setName(String.valueOf(resultSet.getObject(3)));
            supplier.setAddress(String.valueOf(resultSet.getObject(4)));
            supplier.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            supplier.setEmail(String.valueOf(resultSet.getObject(6)));
            suppliers.add(supplier);
        }
        return suppliers;
    }
}

