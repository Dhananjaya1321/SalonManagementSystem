package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.entity.User;

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
    public ArrayList<Employee> search(boolean value, Employee employee) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Employee WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Employee WHERE Emp_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + employee.getName() + "%");
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(
                    new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                    )
            );
        }
        return employees;
    }

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Employee");
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(
                    new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                    )
            );
        }
        return employees;
    }


    public ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM Employee WHERE Job_title='Beautician'");
        ArrayList<Employee> employeeDTOS = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmpId(resultSet.getString(1));
            employeeDTOS.add(employee);
        }
        return employeeDTOS;
    }

    @Override
    public ArrayList<Employee> searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Employee WHERE Emp_Id = ?", employee.getEmpId());
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(
                    new Employee(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                    )
            );
        }
        return employees;
    }


    public boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employee.getEmpId(), employee.getName()
                , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail()
                , employee.getNic(), employee.getJobTitle());
    }


    public Employee checkAdmin() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Phone_number FROM Employee WHERE Job_title='Admin'");
        Employee employee = new Employee();
        if (resultSet.next()) {
            employee.setPhoneNumber(resultSet.getString(1));
        }
        return employee;
    }

    public boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Address=?,DOB=?,Phone_number=?,Description=? WHERE Job_title='Admin'"
                , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription());
    }
}
