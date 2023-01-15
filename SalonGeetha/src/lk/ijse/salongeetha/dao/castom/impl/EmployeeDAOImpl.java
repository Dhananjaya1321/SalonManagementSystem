package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employee.getEmpId(), employee.getName(),
                employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail(),
                employee.getNic(), employee.getJobTitle());
    }

    public boolean delete(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Employee WHERE Emp_Id=?", employee.getEmpId());
    }

    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Name=?,Address=?,Phone_number=?,Description=?,Email=? WHERE Emp_Id=?"
                , employee.getName(), employee.getAddress(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail(), employee.getEmpId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, Employee to) throws SQLException, ClassNotFoundException {
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


    public ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM Employee WHERE Job_title='Beautician'");
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            employees.add(searchEmployee);
        }
        return employees;

    }

    @Override
    public ResultSet searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Employee WHERE Emp_Id = ?", employee.getEmpId());

    }


    public boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employee.getEmpId(), employee.getName()
                    , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail()
                    , employee.getNic(), employee.getJobTitle());
    }

    public Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Job_title FROM Employee e JOIN User u ON  e.Emp_Id = u.Emp_Id WHERE u.User_name=?"
                , user.getUserName());
        if (resultSet.next()) {
            Employee employee = new Employee();
            employee.setJobTitle(String.valueOf(resultSet.getObject(1)));
            return employee;
        }
        return null;
    }

    public boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Phone_number FROM Employee WHERE Job_title='Admin'");
        if (resultSet.next()) {
            employee.setPhoneNumber(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    public boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Address=?,DOB=?,Phone_number=?,Description=? WHERE Job_title='Admin'"
                , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription());
    }
}
