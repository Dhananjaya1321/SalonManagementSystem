package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO {
    String checkIdAppointment() throws SQLException, ClassNotFoundException;

    ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException;

    ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException;

    ResultSet searchServiceDetails(Service service) throws SQLException, ClassNotFoundException;

    ResultSet searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException;

    ResultSet getAllServices() throws SQLException, ClassNotFoundException;

    ResultSet getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException;
}
