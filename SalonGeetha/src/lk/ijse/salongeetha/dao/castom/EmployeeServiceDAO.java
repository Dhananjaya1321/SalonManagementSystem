package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.EmployeeServiceDetail;

import java.sql.SQLException;

public interface EmployeeServiceDAO extends SQLUtil<EmployeeServiceDetail> {
    boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException;
}
