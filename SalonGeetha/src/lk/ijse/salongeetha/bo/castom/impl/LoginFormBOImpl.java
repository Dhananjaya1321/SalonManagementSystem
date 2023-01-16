package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.LoginFormBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.SQLException;

public class LoginFormBOImpl implements LoginFormBO {
    LoginDAO loginDAO = (LoginDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.LOGIN);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);

    @Override
    public boolean addAdminDetails(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        boolean isAdded;
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            isAdded = loginDAO.addDetails(userDTO, employeeDTO);
            if (isAdded) {
                isAdded=loginDAO.add(userDTO);
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
        return loginDAO.setUserAccount(userDTO);
    }
    @Override
    public EmployeeDTO getEmployeeJobTitle(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeJobTitle(userDTO);
    }
    @Override
    public boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        return loginDAO.checkUserAccount();
    }
}
