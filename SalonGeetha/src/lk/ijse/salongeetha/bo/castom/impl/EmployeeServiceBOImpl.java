package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.entity.CustomEntity;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.entity.EmployeeServiceDetail;
import lk.ijse.salongeetha.entity.Service;
import lk.ijse.salongeetha.dto.EmployeeDTO;
import lk.ijse.salongeetha.dto.EmployeeServiceDetailDTO;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeServiceBOImpl implements EmployeeServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    EmployeeServiceDAO employeeServiceDAO = (EmployeeServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE_SERVICE);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    private ArrayList<Employee> all;
    private ArrayList<EmployeeDTO> employeeDTO;
    private ArrayList<Service> services;
    private ArrayList<ServiceDTO> serviceDTOS;

    @Override
    public boolean checkAlreadyExists(EmployeeServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.checkAlreadyExists(new EmployeeServiceDetail(dto.getEmpId(), dto.getSevId()));
    }


    @Override
    public boolean addEmployeeAndServiceDetail(EmployeeServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.add(new EmployeeServiceDetail(dto.getEmpId(), dto.getSevId()));
    }

    @Override
    public ArrayList<EmployeeDTO> searchEmployee(boolean value, EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Employee>*/
        all = employeeDAO.search(value,
                new Employee(dto.getEmpId(), dto.getName(), dto.getAddress(), dto.getDob(),
                        dto.getPhoneNumber(), dto.getDescription(), dto.getEmail(), dto.getNic(), dto.getJobTitle()));

        /*ArrayList<EmployeeDTO>*/
        employeeDTO = new ArrayList<>();
        for (Employee e : all) {
            employeeDTO.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return employeeDTO;
    }

    @Override
    public ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.search(value, new Service(dto.getSevId(), dto.getDescription(), dto.getName(),
                dto.getPrice(), dto.getDiscount()));
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;

    }

    @Override
    public boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.delete(new EmployeeServiceDetail(dto.getEmpId(), dto.getSevId()));
    }

    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
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
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        /*ArrayList<Employee>*/
        all = employeeDAO.getAll();
        /* ArrayList<EmployeeDTO>*/
        employeeDTO = new ArrayList<>();
        for (Employee e : all) {
            employeeDTO.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return employeeDTO;
    }

    @Override
    public ArrayList<EmployeeServiceDetailDTO> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = queryDAO.getAllEmployeeServiceDetails();
        ArrayList<EmployeeServiceDetailDTO> dtos = new ArrayList<>();
        for (CustomEntity c : all) {
            dtos.add(new EmployeeServiceDetailDTO(c.getEmpId(), c.getSevId(), c.getEmpName(), c.getSevName()));
        }
        return dtos;
    }
}
