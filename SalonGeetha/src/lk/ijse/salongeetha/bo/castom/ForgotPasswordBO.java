package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface ForgotPasswordBO extends SuperBOImpl {
    boolean checkEmail(User user) throws SQLException, ClassNotFoundException;
}
