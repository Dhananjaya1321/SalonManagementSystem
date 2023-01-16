package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AppointmentBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.*;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBO {
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    AppointmentDAO appointmentDAO = (AppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.APPOINTMENT);
    ServiceAppointmentDAO serviceAppointmentDAO = (ServiceAppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE_APPOINTMENT);

    @Override
    public String checkIdAppointment() throws SQLException, ClassNotFoundException {
        return appointmentDAO.checkId();
    }
    @Override
    public ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException {
        return employeeDAO.getBeauticians();
    }
    @Override
    public ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerDetails(customerDTO);
    }
    @Override
    public ResultSet searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.searchServiceDetails(serviceDTO);
    }
    @Override
    public ResultSet searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchEmployeeDetails(employeeDTO);
    }
    @Override
    public ResultSet getAllServices() throws SQLException, ClassNotFoundException {
        return serviceDAO.getAll();
    }
    @Override
    public ResultSet getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }



    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = appointmentDAO.addAppointment(appointmentDTO, serviceAppointmentDetailDTOS);

            if (isAdded) {
                boolean addDetails = serviceAppointmentDAO.addDetails(serviceAppointmentDetailDTOS);
                if (addDetails) {
                    DBConnection.getDBConnection().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

}
