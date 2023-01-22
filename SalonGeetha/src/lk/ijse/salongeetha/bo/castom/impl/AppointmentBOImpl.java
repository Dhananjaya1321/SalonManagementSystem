package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AppointmentBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.*;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.entity.*;
import lk.ijse.salongeetha.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBO {
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    AppointmentDAO appointmentDAO = (AppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.APPOINTMENT);
    ServiceAppointmentDAO serviceAppointmentDAO = (ServiceAppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE_APPOINTMENT);

    private ArrayList<CustomerDTO> customerDTOS;
    private ArrayList<ServiceDTO> serviceDTOS;
    private ArrayList<EmployeeDTO> employeeDTO = new ArrayList<>();
    private ArrayList<Service> services;
    private ArrayList<Customer> customers;
    private ArrayList<Employee> all;

    @Override
    public String checkIdAppointment() throws SQLException, ClassNotFoundException {
        return appointmentDAO.checkId();
    }

    @Override
    public ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException {
        /*ArrayList<Employee>*/
        all = employeeDAO.getBeauticians();
        for (Employee e : all) {
            employeeDTO.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return employeeDTO;
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomerDetails(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer> */
        customers = customerDAO.searchCustomerDetails(new Customer(dto.getNic(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(),
                dto.getDob(), dto.getUserName()));
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(),
                    c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ServiceDTO> searchServiceDetails(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.searchServiceDetails(new Service(dto.getSevId(), dto.getDescription(),
                dto.getName(), dto.getPrice(), dto.getDiscount()));
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public ArrayList<EmployeeDTO> searchEmployeeDetails(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Employee> */
        all = employeeDAO.searchEmployeeDetails(new Employee(dto.getEmpId(), dto.getName(), dto.getAddress(), dto.getDob(),
                dto.getPhoneNumber(), dto.getDescription(), dto.getEmail(), dto.getNic(), dto.getJobTitle()));
        /*ArrayList<EmployeeDTO> */
        employeeDTO = new ArrayList<>();
        for (Employee e : all) {
            employeeDTO.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return employeeDTO;
    }

    @Override
    public ArrayList<ServiceDTO> getAllServices() throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.getAll();
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer>*/
        customers = customerDAO.getAll();
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(),
                    c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }


    @Override
    public boolean addAppointment(AppointmentDTO dto, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            Appointment appointment = new Appointment(dto.getAptId(), dto.getDate(), dto.getTime(), dto.getNic(),
                    dto.getStatus());

            ArrayList<ServiceAppointmentDetail> details = new ArrayList<>();
            for (ServiceAppointmentDetailDTO s : serviceAppointmentDetailDTOS) {
                details.add(new ServiceAppointmentDetail(s.getAptId(), s.getSevId()));
            }

            boolean isAdded = appointmentDAO.addAppointment(appointment, details);

            if (isAdded) {
                boolean addDetails = serviceAppointmentDAO.addDetails(details);
                if (addDetails) {
                    DBConnection.getDBConnection().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

}
