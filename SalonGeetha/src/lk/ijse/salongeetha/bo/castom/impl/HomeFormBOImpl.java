package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.HomeFormBO;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.impl.AppointmentDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.BookingDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.to.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeFormBOImpl implements HomeFormBO {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();
    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    @Override
    public String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAppointmentCount(setDate);
    }
    @Override
    public ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAppointmentForChart(time);
    }
    @Override
    public ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        return bookingDAO.getBookingForChart(time);
    }
    @Override
    public String getBookingCount(String setDate) throws SQLException, ClassNotFoundException {
        return bookingDAO.getBookingCount(setDate);
    }
    @Override
    public boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.checkAdmin(employee);
    }
}
