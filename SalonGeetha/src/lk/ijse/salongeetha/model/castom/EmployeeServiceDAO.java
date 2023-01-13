package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;

import java.sql.SQLException;

public interface EmployeeServiceDAO extends SQLUtil<EmployeeServiceDetail> {
    boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException;
}
