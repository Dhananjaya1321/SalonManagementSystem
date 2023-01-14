package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface ForgotPasswordBO {
    boolean checkEmail(User user) throws SQLException, ClassNotFoundException;
}
