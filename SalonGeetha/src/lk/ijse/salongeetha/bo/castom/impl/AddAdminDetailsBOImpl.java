package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AddAdminDetailsBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.SQLException;

public class AddAdminDetailsBOImpl implements AddAdminDetailsBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);

    @Override
    public boolean updateAdmin(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateAdmin(new Employee(dto.getEmpId(),dto.getName(), dto.getAddress(), dto.getDob(),
                dto.getPhoneNumber(), dto.getDescription(), dto.getEmail(), dto.getNic(), dto.getJobTitle()));
    }

}
