package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Employee;

import java.sql.SQLException;

public interface AddAdminDetailsBO {

    boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException;
}
