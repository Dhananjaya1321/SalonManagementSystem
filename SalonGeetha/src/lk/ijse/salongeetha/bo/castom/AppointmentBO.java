package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBOImpl {
    String checkIdAppointment() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException;

    ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllServices() throws SQLException, ClassNotFoundException;

    ResultSet getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean addAppointment(AppointmentDTO appointmentDTO, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException;
}
