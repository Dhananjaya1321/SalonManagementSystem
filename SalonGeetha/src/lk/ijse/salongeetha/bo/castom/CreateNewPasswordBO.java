package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface CreateNewPasswordBO {
    boolean changePassword(User user) throws SQLException, ClassNotFoundException;
}
