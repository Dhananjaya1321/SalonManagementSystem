package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface UserDAO extends SQLUtil<User> {
    boolean delete(User user, Employee employee) throws SQLException, ClassNotFoundException;
}
