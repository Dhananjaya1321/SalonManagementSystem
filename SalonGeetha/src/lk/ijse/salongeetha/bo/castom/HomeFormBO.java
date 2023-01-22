package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HomeFormBO extends SuperBOImpl {
    int getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;

    ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException;

    int getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    EmployeeDTO checkAdmin() throws SQLException, ClassNotFoundException;
}
