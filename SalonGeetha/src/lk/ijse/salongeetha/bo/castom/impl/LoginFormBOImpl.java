package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.LoginFormBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.entity.User;
import lk.ijse.salongeetha.dto.EmployeeDTO;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;

public class LoginFormBOImpl implements LoginFormBO {
    LoginDAO loginDAO = (LoginDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.LOGIN);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    QueryDAO queryDAO= (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    private User user;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @Override
    public boolean addAdminDetails(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        boolean isAdded;
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            /*User*/
            user = new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword());
            /*Employee*/
            employee = new Employee(employeeDTO.getEmpId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getDob(),
                    employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getNic(), employeeDTO.getJobTitle());
            isAdded = loginDAO.addDetails(user, employee);
            if (isAdded) {
                isAdded = loginDAO.add(user);
                if (isAdded) {
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
    public boolean setUserAccount(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return loginDAO.setUserAccount(new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword()));
    }

    @Override
    public String getEmployeeJobTitle(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        /*Employee*/
        return queryDAO.getEmployeeJobTitle(new User(userDTO.getUserName(), userDTO.getEid(), userDTO.getPassword()));
        /*EmployeeDTO*/

    }

    @Override
    public boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        return loginDAO.checkUserAccount();
    }
}
