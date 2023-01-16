package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierBO extends SuperBOImpl {
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchSupplier(Boolean value, SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    String checkIdSupplier() throws SQLException, ClassNotFoundException;

    ResultSet getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean addSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
}
