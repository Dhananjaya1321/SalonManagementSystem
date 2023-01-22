package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.entity.Service;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    private ArrayList<Service> services;
    private ArrayList<ServiceDTO> serviceDTOS;

    @Override
    public boolean deleteService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(new Service(serviceDTO.getSevId(), serviceDTO.getDescription(),
                serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getDiscount()));
    }

    @Override
    public String checkIdService() throws SQLException, ClassNotFoundException {
        return serviceDAO.checkId();
    }

    @Override
    public ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.search(value, new Service(serviceDTO.getSevId(), serviceDTO.getDescription(),
                serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getDiscount()));
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.getAll();
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public boolean addService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(new Service(serviceDTO.getSevId(), serviceDTO.getDescription(),
                serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getDiscount()));
    }
}
