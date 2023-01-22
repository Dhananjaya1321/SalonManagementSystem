package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBOImpl {
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> searchSupplier(Boolean value, SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    String checkIdSupplier() throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean addSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
}
