package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAOImpl implements ServiceDAO {
    public boolean add(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Service VALUES (?,?,?,?,?)", serviceDTO.getSevId(), serviceDTO.getDescription()
                , serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getDiscount());
    }

    public boolean delete(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Service WHERE Sev_Id=?", serviceDTO.getSevId());
    }

    public boolean update(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Service SET Description=?,Name=?,Price=?,Discount=? WHERE Sev_Id=?", serviceDTO.getDescription()
                , serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getDiscount(), serviceDTO.getSevId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Sev_Id FROM Service ORDER BY Sev_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Service");
    }

    public ResultSet search(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Service WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Service WHERE Sev_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + serviceDTO.getName() + "%");
    }

    @Override
    public ResultSet searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Service WHERE Sev_Id = ?", serviceDTO.getSevId());
    }
}
