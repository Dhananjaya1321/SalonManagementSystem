package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.dao.castom.impl.UserDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.entity.User;
import lk.ijse.salongeetha.dto.EmployeeDTO;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    UserDAO userDAO = (UserDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.USER);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);

    private Employee employee;
    private User user;
    private ArrayList<Employee> all;
    private ArrayList<EmployeeDTO> dtoArrayList;

    @Override
    public boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);

            employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                    employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
            user = new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword());

            boolean isAdded = employeeDAO.addReceptionist(employee, user);
            if (isAdded) {
                UserDAO userDAO = new UserDAOImpl();
                boolean addUser = userDAO.add(new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword()));
                if (addUser) {
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

    @Override
    public boolean deleteReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            user = new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword());
            employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                    employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
            boolean executeUpdate = userDAO.delete(user, employee);
            if (executeUpdate) {
                boolean deleteEmployee = employeeDAO.delete(employee);
                if (deleteEmployee) {
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

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
        return employeeDAO.add(employee);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
        return employeeDAO.delete(employee);
    }

    @Override
    public String checkIdEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.checkId();
    }

    @Override
    public ArrayList<EmployeeDTO> searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
        /*ArrayList<Employee>*/
        all = employeeDAO.search(value, employee);
        /*ArrayList<EmployeeDTO>*/
        dtoArrayList = new ArrayList<>();
        for (Employee e : all) {
            dtoArrayList.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return dtoArrayList;
    }


    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        /*ArrayList<Employee>*/
        all = employeeDAO.getAll();
        /*ArrayList<EmployeeDTO>*/
        dtoArrayList = new ArrayList<>();
        for (Employee e : all) {
            dtoArrayList.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return dtoArrayList;
    }
}
