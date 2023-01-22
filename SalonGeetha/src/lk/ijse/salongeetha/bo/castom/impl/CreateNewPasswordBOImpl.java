package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.CreateNewPasswordBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.entity.User;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;

public class CreateNewPasswordBOImpl implements CreateNewPasswordBO {
    LoginDAO loginDAO = (LoginDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.LOGIN);

    @Override
    public boolean changePassword(UserDTO dto) throws SQLException, ClassNotFoundException {
        return loginDAO.ChangePassword(new User(dto.getUserName(), dto.getEid(), dto.getPassword()));
    }
}
