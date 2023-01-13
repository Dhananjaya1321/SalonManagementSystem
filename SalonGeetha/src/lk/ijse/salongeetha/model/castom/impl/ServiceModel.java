package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Service");
        while (resultSet.next()) {
            Service service = new Service();
            service.setSevId(String.valueOf(resultSet.getObject(1)));
            service.setDescription(String.valueOf(resultSet.getObject(2)));
            service.setName(String.valueOf(resultSet.getObject(3)));
            service.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            service.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            services.add(service);
        }
        return services;
    }

    public ArrayList<Service> search(Service service) throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(service.getName());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Service WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Service WHERE Sev_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + service.getName() + "%");
        while (resultSet.next()) {
            Service searchService = new Service();
            searchService.setSevId(String.valueOf(resultSet.getObject(1)));
            searchService.setDescription(String.valueOf(resultSet.getObject(2)));
            searchService.setName(String.valueOf(resultSet.getObject(3)));
            searchService.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            searchService.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            services.add(searchService);
        }
        return services;
    }

    public static ArrayList<Service> searchServiceDetails(Service service) throws SQLException, ClassNotFoundException {
        ArrayList<Service> services = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Service WHERE Sev_Id = ?", service.getSevId());
        while (resultSet.next()) {
            Service searchService = new Service();
            searchService.setSevId(String.valueOf(resultSet.getObject(1)));
            searchService.setDescription(String.valueOf(resultSet.getObject(2)));
            searchService.setName(String.valueOf(resultSet.getObject(3)));
            searchService.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            searchService.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            services.add(searchService);
        }
        return services;
    }
}
