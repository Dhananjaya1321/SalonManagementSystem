package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AppointmentBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.*;
import lk.ijse.salongeetha.dao.castom.impl.*;
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
    public ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException {
        return employeeDAO.getBeauticians();
    }
    @Override
    public ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerDetails(customer);
    }
    @Override
    public ResultSet searchServiceDetails(Service service) throws SQLException, ClassNotFoundException {
        return serviceDAO.searchServiceDetails(service);
    }
    @Override
    public ResultSet searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchEmployeeDetails(employee);
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
    public boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = appointmentDAO.addAppointment(appointment, serviceAppointmentDetails);

            if (isAdded) {
                boolean addDetails = serviceAppointmentDAO.addDetails(serviceAppointmentDetails);
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
