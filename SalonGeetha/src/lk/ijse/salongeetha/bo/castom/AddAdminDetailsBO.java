package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Employee;

import java.sql.SQLException;

public interface AddAdminDetailsBO extends SuperBOImpl {

    boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException;
}
