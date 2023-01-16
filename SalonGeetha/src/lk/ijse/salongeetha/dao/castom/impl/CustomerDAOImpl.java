package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.to.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean add(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Customer VALUES (?,?,?,?,?,?)", customerDTO.getNic(), customerDTO.getName(),
                customerDTO.getPhoneNumber(), customerDTO.getEmail(), customerDTO.getDob(), customerDTO.getUserName());
    }

    public boolean delete(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Customer WHERE NIC=?", customerDTO.getNic());
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Customer SET Name=?,Phone_number=?,Email=?,DOB=? WHERE NIC=?", customerDTO.getName(),
                customerDTO.getPhoneNumber(), customerDTO.getEmail(), customerDTO.getDob(), customerDTO.getNic());
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, CustomerDTO to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Customer WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Customer WHERE NIC LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Customer");
    }


    public ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Customer WHERE NIC=?", customerDTO.getNic());
    }
}
