package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AddAdminDetailsBO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.to.Employee;

import java.sql.SQLException;

public class AddAdminDetailsBOImpl implements AddAdminDetailsBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateAdmin(employee);
    }

}
