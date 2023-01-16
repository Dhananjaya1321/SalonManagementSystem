package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO= (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    @Override
    public boolean deleteService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
       return serviceDAO.delete(serviceDTO);
    }
    @Override
    public String checkIdService() throws SQLException, ClassNotFoundException {
        return serviceDAO.checkId();
    }
    @Override
    public ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value, serviceDTO);
    }
    @Override
    public ResultSet getAllService() throws SQLException, ClassNotFoundException {
        return serviceDAO.getAll();
    }
    @Override
    public boolean addService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(serviceDTO);
    }

}
