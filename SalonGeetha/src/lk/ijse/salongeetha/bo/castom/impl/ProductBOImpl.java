package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.entity.Product;
import lk.ijse.salongeetha.entity.Supplier;
import lk.ijse.salongeetha.dto.ProductDTO;
import lk.ijse.salongeetha.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    SupplierDAO supplierDAO = (SupplierDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SUPPLIER);
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);
    private ArrayList<Product> products;
    private ArrayList<ProductDTO> productDTOS;

    @Override
    public boolean deleteProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.delete(new Product(dto.getProId(), dto.getDescription(), dto.getCategory(), dto.getBrand()
                , dto.getSupId(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.add(new Product(dto.getProId(), dto.getDescription(), dto.getCategory(), dto.getBrand()
                , dto.getSupId(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getProId(), dto.getDescription(), dto.getCategory(), dto.getBrand()
                , dto.getSupId(), dto.getUnitPrice(), dto.getQtyOnHand()));
    }

    @Override
    public String checkIdProduct() throws SQLException, ClassNotFoundException {
        return productDAO.checkId();
    }


    @Override
    public ArrayList<ProductDTO> searchProduct(boolean value, ProductDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Product>*/
        products = productDAO.search(value, new Product(dto.getProId(), dto.getDescription(), dto.getCategory(), dto.getBrand()
                , dto.getSupId(), dto.getUnitPrice(), dto.getQtyOnHand()));
        /*ArrayList<ProductDTO>*/
        productDTOS = new ArrayList<>();
        for (Product p : products) {
            productDTOS.add(new ProductDTO(p.getProId(), p.getDescription(), p.getCategory(), p.getBrand(), p.getSupId(), p.getUnitPrice(), p.getQtyOnHand()));
        }
        return productDTOS;
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        /*ArrayList<Supplier>*/
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        /*ArrayList<SupplierDTO>*/
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier s : suppliers) {
            supplierDTOS.add(new SupplierDTO(s.getSupId(), s.getDescription(), s.getName(), s.getAddress(),
                    s.getPhoneNumber(), s.getEmail()));
        }
        return supplierDTOS;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        /*ArrayList<Product>*/
        products = productDAO.getAll();
        /*ArrayList<ProductDTO>*/
        productDTOS = new ArrayList<>();
        for (Product p : products) {
            productDTOS.add(new ProductDTO(p.getProId(), p.getDescription(), p.getCategory(), p.getBrand(), p.getSupId(), p.getUnitPrice(), p.getQtyOnHand()));
        }
        return productDTOS;
    }
}
