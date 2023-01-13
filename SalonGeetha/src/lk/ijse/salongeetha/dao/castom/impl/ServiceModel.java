package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceModel implements ServiceDAO {
    public boolean add(Service service) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Service VALUES (?,?,?,?,?)", service.getSevId(), service.getDescription()
                , service.getName(), service.getPrice(), service.getDiscount());
    }

    public boolean delete(Service service) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Service WHERE Sev_Id=?", service.getSevId());
    }

    public boolean update(Service service) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Service SET Description=?,Name=?,Price=?,Discount=? WHERE Sev_Id=?", service.getDescription()
                , service.getName(), service.getPrice(), service.getDiscount(), service.getSevId());
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

    public ResultSet search(boolean value, Service service) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Service WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Service WHERE Sev_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + service.getName() + "%");
    }

    @Override
    public ResultSet searchServiceDetails(Service service) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Service WHERE Sev_Id = ?", service.getSevId());
    }
}
