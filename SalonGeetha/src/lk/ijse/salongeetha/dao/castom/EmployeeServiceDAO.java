package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.EmployeeServiceDetailDTO;

import java.sql.SQLException;

public interface EmployeeServiceDAO extends SQLUtil<EmployeeServiceDetailDTO> {
    boolean checkAlreadyExists(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;
}
