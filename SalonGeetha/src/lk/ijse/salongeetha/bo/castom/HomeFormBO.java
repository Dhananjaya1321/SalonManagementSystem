package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.CustomDTO;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HomeFormBO extends SuperBOImpl {
    int getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;

    ArrayList<CustomDTO> getBookingForChart(String time) throws SQLException, ClassNotFoundException;

    int getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    EmployeeDTO checkAdmin() throws SQLException, ClassNotFoundException;
}
