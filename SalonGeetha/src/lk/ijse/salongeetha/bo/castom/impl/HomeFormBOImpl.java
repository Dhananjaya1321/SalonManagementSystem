package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.HomeFormBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dto.CustomDTO;
import lk.ijse.salongeetha.entity.CustomEntity;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomeFormBOImpl implements HomeFormBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    BookingDAO bookingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    AppointmentDAO appointmentDAO = (AppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.APPOINTMENT);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);

    @Override
    public int getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAppointmentCount(setDate);
    }

    @Override
    public ArrayList<CustomDTO> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> bookingForChart = queryDAO.getAppointmentForChart(time);
        ArrayList<CustomDTO> customerDTOS = new ArrayList<>();
        for (CustomEntity c : bookingForChart) {
            customerDTOS.add(new CustomDTO(c.getAvaliableCount(),c.getDate()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<CustomDTO> getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> bookingForChart = queryDAO.getBookingForChart(time);
        ArrayList<CustomDTO> customerDTOS = new ArrayList<>();
        for (CustomEntity c : bookingForChart) {
            customerDTOS.add(new CustomDTO(c.getAvaliableCount(),c.getDate()));
        }
        return customerDTOS;
    }

    @Override
    public int getBookingCount(String setDate) throws SQLException, ClassNotFoundException {
        return bookingDAO.getBookingCount(setDate);
    }

    @Override
    public EmployeeDTO checkAdmin() throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.checkAdmin();
        return new EmployeeDTO(employee.getEmpId(), employee.getName(), employee.getAddress(), employee.getDob(),
                employee.getPhoneNumber(), employee.getDescription(), employee.getEmail(), employee.getNic(),
                employee.getJobTitle());
    }
}
