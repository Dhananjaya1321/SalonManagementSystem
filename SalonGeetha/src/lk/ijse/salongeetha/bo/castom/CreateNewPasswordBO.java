package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface CreateNewPasswordBO extends SuperBOImpl {
    boolean changePassword(User user) throws SQLException, ClassNotFoundException;
}
