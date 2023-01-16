package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface HomeFormBO extends SuperBOImpl {
    String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;

    ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException;

    String getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    boolean checkAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
