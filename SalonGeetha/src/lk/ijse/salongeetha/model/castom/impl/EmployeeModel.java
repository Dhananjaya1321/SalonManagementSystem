package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeModel {
    public static boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);

            boolean isAdded = CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employee.getEmpId(), employee.getName()
                    , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail()
                    , employee.getNic(), employee.getJobTitle());
            if (isAdded) {
                boolean addUser = UserModel.addUser(user);
                if (addUser) {
                    DBConnection.getDBConnection().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    public static boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", employee.getEmpId(), employee.getName(),
                employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail(),
                employee.getNic(), employee.getJobTitle());
    }

    public static boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Employee WHERE Emp_Id=?", employee.getEmpId());
    }

    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Name=?,Address=?,Phone_number=?,Description=?,Email=? WHERE Emp_Id=?"
                , employee.getName(), employee.getAddress(), employee.getPhoneNumber(), employee.getDescription(), employee.getEmail(), employee.getEmpId());
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Phone_number FROM Employee WHERE Job_title='Admin'");
        if (resultSet.next()) {
            employee.setPhoneNumber(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    public static boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Employee SET Address=?,DOB=?,Phone_number=?,Description=? WHERE Job_title='Admin'"
                , employee.getAddress(), employee.getDob(), employee.getPhoneNumber(), employee.getDescription());
    }

    public static Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Job_title FROM Employee e JOIN User u ON  e.Emp_Id = u.Emp_Id WHERE u.User_name=?"
                , user.getUserName());
        if (resultSet.next()) {
            Employee employee = new Employee();
            employee.setJobTitle(String.valueOf(resultSet.getObject(1)));
            return employee;
        }
        return null;
    }

    public static ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Employee");
        if (resultSet.next()) {
            do {
                Employee employee = new Employee();
                employee.setEmpId(String.valueOf(resultSet.getObject(1)));
                employee.setName(String.valueOf(resultSet.getObject(2)));
                employee.setAddress(String.valueOf(resultSet.getObject(3)));
                employee.setDob(String.valueOf(resultSet.getObject(4)));
                employee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
                employee.setDescription(String.valueOf(resultSet.getObject(6)));
                employee.setEmail(String.valueOf(resultSet.getObject(7)));
                employee.setNic(String.valueOf(resultSet.getObject(8)));
                employee.setJobTitle(String.valueOf(resultSet.getObject(9)));
                employees.add(employee);
            } while (resultSet.next());
            return employees;
        }
        return new ArrayList<>();
    }

    public static ArrayList<Employee> searchEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        String setColumn;
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employee.getName());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Employee WHERE Name LIKE ?";
//            System.out.println("ddd");
        } else {
            setColumn = "SELECT * FROM Employee WHERE Emp_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + employee.getName() + "%");
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployee.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployee.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployee.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployee.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployee.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployee.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployee.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employees.add(searchEmployee);
        }
        return employees;
    }

    public static ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Emp_Id FROM Employee WHERE Job_title='Beautician'");
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            employees.add(searchEmployee);
        }
        return employees;

    }

    public static ArrayList<Employee> searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Employee WHERE Emp_Id = ?", employee.getEmpId());
        while (resultSet.next()) {
            Employee searchEmployee = new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            searchEmployee.setName(String.valueOf(resultSet.getObject(2)));
            searchEmployee.setAddress(String.valueOf(resultSet.getObject(3)));
            searchEmployee.setDob(String.valueOf(resultSet.getObject(4)));
            searchEmployee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchEmployee.setDescription(String.valueOf(resultSet.getObject(6)));
            searchEmployee.setEmail(String.valueOf(resultSet.getObject(7)));
            searchEmployee.setNic(String.valueOf(resultSet.getObject(8)));
            searchEmployee.setJobTitle(String.valueOf(resultSet.getObject(9)));
            employees.add(searchEmployee);
        }
        return employees;
    }
}
