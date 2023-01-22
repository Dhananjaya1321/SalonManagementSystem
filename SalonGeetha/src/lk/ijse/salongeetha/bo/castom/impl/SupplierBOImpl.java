package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.SupplierBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.entity.Supplier;
import lk.ijse.salongeetha.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SUPPLIER);
    private ArrayList<Supplier> suppliers;
    private ArrayList<SupplierDTO> supplierDTOS;

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupId(), supplierDTO.getDescription(), supplierDTO.getName(),
                supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail()));
    }

    @Override
    public ArrayList<SupplierDTO> searchSupplier(Boolean value, SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        /*ArrayList<Supplier>*/
        suppliers = supplierDAO.search(value, new Supplier(supplierDTO.getSupId(), supplierDTO.getDescription(),
                supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail()));
        /*ArrayList<SupplierDTO>*/
        supplierDTOS = new ArrayList<>();
        for (Supplier s : suppliers) {
            supplierDTOS.add(new SupplierDTO(s.getSupId(), s.getDescription(), s.getName(), s.getAddress(),
                    s.getPhoneNumber(), s.getEmail()));
        }
        return supplierDTOS;
    }

    @Override
    public boolean deleteSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(new Supplier(supplierDTO.getSupId(), supplierDTO.getDescription(),
                supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail()));
    }

    @Override
    public String checkIdSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.checkId();
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        /*ArrayList<Supplier>*/
        suppliers = supplierDAO.getAll();
        /*ArrayList<SupplierDTO>*/
        supplierDTOS = new ArrayList<>();
        for (Supplier s : suppliers) {
            supplierDTOS.add(new SupplierDTO(s.getSupId(), s.getDescription(), s.getName(), s.getAddress(),
                    s.getPhoneNumber(), s.getEmail()));
        }
        return supplierDTOS;
    }

    @Override
    public boolean addSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(supplierDTO.getSupId(), supplierDTO.getDescription(),
                supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail()));
    }
}
