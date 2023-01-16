package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public boolean add(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employeeDTO.getEmpId(), employeeDTO.getName(),
                employeeDTO.getAddress(), employeeDTO.getDob(), employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(),
                employeeDTO.getNic(), employeeDTO.getJobTitle());
    }

    public boolean delete(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Employee WHERE Emp_Id=?", employeeDTO.getEmpId());
    }

    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Name=?,Address=?,Phone_number=?,Description=?,Email=? WHERE Emp_Id=?"
                , employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail(), employeeDTO.getEmpId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, EmployeeDTO to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Employee WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Employee WHERE Emp_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Employee");
    }


    public ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM Employee WHERE Job_title='Beautician'");
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeDTO searchEmployeeDTO = new EmployeeDTO();
            searchEmployeeDTO.setEmpId(String.valueOf(resultSet.getObject(1)));
            employeeDTOS.add(searchEmployeeDTO);
        }
        return employeeDTOS;

    }

    @Override
    public ResultSet searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Employee WHERE Emp_Id = ?", employeeDTO.getEmpId());

    }


    public boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employeeDTO.getEmpId(), employeeDTO.getName()
                    , employeeDTO.getAddress(), employeeDTO.getDob(), employeeDTO.getPhoneNumber(), employeeDTO.getDescription(), employeeDTO.getEmail()
                    , employeeDTO.getNic(), employeeDTO.getJobTitle());
    }

    public EmployeeDTO getEmployeeJobTitle(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Job_title FROM Employee e JOIN User u ON  e.Emp_Id = u.Emp_Id WHERE u.User_name=?"
                , userDTO.getUserName());
        if (resultSet.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setJobTitle(String.valueOf(resultSet.getObject(1)));
            return employeeDTO;
        }
        return null;
    }

    public boolean checkAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Phone_number FROM Employee WHERE Job_title='Admin'");
        if (resultSet.next()) {
            employeeDTO.setPhoneNumber(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    public boolean updateAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Address=?,DOB=?,Phone_number=?,Description=? WHERE Job_title='Admin'"
                , employeeDTO.getAddress(), employeeDTO.getDob(), employeeDTO.getPhoneNumber(), employeeDTO.getDescription());
    }
}
