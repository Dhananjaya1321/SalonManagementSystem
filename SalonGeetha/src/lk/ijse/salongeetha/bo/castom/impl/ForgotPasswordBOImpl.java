package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ForgotPasswordBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgotPasswordBO {
    LoginDAO loginDAO = (LoginDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.LOGIN);
    @Override
    public boolean checkEmail(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return loginDAO.checkEmail(userDTO);
    }
}
