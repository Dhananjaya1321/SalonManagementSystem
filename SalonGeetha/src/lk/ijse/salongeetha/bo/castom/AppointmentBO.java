package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBOImpl {
    String checkIdAppointment() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO>  searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO>  searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO>  searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> getAllServices() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean addAppointment(AppointmentDTO appointmentDTO, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException;
}
