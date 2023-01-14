package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierBO {
    boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;

    ResultSet searchSupplier(Boolean value, Supplier supplier) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;

    String checkIdSupplier() throws SQLException, ClassNotFoundException;

    ResultSet getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean addSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;
}
