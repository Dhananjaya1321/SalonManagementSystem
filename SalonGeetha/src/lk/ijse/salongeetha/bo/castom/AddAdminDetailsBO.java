package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.EmployeeDTO;

import java.sql.SQLException;

public interface AddAdminDetailsBO extends SuperBOImpl {

    boolean updateAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
