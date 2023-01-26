package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SuperDAOImpl;
import lk.ijse.salongeetha.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAOImpl {
    ArrayList<CustomEntity> getAmountDueServiceAppointmentDetails(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity>  getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getAllProductService() throws SQLException, ClassNotFoundException;

    boolean checkEmail(User user) throws SQLException, ClassNotFoundException;

    String getEmployeeJobTitle(User userDTO) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getBookingForChart(String time) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;
}
