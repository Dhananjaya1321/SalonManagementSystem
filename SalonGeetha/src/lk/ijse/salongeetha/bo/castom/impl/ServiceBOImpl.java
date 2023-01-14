package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ServiceBO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.ServiceDAOImpl;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO=new ServiceDAOImpl();
    @Override
    public boolean deleteService(Service service) throws SQLException, ClassNotFoundException {
       return serviceDAO.delete(service);
    }
    @Override
    public String checkIdService() throws SQLException, ClassNotFoundException {
        return serviceDAO.checkId();
    }
    @Override
    public ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value,service);
    }
    @Override
    public ResultSet getAllService() throws SQLException, ClassNotFoundException {
        return serviceDAO.getAll();
    }
    @Override
    public boolean addService(Service service) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(service);
    }

}
