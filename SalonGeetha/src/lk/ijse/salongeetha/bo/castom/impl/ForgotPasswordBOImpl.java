package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ForgotPasswordBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.entity.User;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgotPasswordBO {
    QueryDAO queryDAO= (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    @Override
    public boolean checkEmail(UserDTO dto) throws SQLException, ClassNotFoundException {
        return queryDAO.checkEmail(new User(dto.getUserName(), dto.getEid(), dto.getPassword()));
    }
}
