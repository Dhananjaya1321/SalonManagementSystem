package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HomeFormBO {
    String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;

    ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException;

    String getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException;
}
