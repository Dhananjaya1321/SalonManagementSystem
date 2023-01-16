package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AddAdminDetailsBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.to.EmployeeDTO;

import java.sql.SQLException;

public class AddAdminDetailsBOImpl implements AddAdminDetailsBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);

    @Override
    public boolean updateAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateAdmin(employeeDTO);
    }

}
