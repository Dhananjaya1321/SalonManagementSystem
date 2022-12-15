package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeModel {
    public static boolean addReceptionist(Employee employee,User user) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);

            PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, employee.getEmpId());
            preparedStatement.setObject(2, employee.getName());
            preparedStatement.setObject(3, employee.getAddress());
            preparedStatement.setObject(4, employee.getDob());
            preparedStatement.setObject(5, employee.getPhoneNumber());//unique wenna  one
            preparedStatement.setObject(6, employee.getDescription());
            preparedStatement.setObject(7, employee.getEmail());//unique wenna  one
            preparedStatement.setObject(8, employee.getNic());//unique wenna  one
            preparedStatement.setObject(9, employee.getJobTitle());
            int executeUpdate = preparedStatement. executeUpdate();
//            System.out.println("DB"+executeUpdate);
            if (executeUpdate > 0) {
                    boolean addUser = UserModel.addUser(user);
//                System.out.println("user add"+addUser);
                    if (addUser) {
                        DBConnection.getDBConnection().getConnection().commit();
                        return true;
                    }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    public static boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
            PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, employee.getEmpId());
            preparedStatement.setObject(2, employee.getName());
            preparedStatement.setObject(3, employee.getAddress());
            preparedStatement.setObject(4, employee.getDob());
            preparedStatement.setObject(5, employee.getPhoneNumber());//unique wenna  one
            preparedStatement.setObject(6, employee.getDescription());
            preparedStatement.setObject(7, employee.getEmail());//unique wenna  one
            preparedStatement.setObject(8, employee.getNic());//unique wenna  one
            preparedStatement.setObject(9, employee.getJobTitle());
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                    return true;
                }
            return false;
    }

    public static boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
//        searchEmployee(employee);
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Employee WHERE Emp_Id=?");
        preparedStatement.setObject(1,employee.getEmpId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }
    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Employee SET Name=?,Address=?,Phone_number=?" +
                        ",Description=?,Email=? WHERE Emp_Id=?");
        preparedStatement.setObject(1,employee.getName());
        preparedStatement.setObject(2,employee.getAddress());
        preparedStatement.setObject(3,employee.getPhoneNumber());
        preparedStatement.setObject(4,employee.getDescription());
        preparedStatement.setObject(5,employee.getEmail());
//        preparedStatement.setObject(6,employee.getJobTitle());
        preparedStatement.setObject(6,employee.getEmpId());

        int executeUpdate = preparedStatement.executeUpdate();
        if(executeUpdate > 0){
            return true;
        }
        return false;
    }
//    public static boolean searchEmployee(Employee employee) throws SQLException, ClassNotFoundException {
//        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
//                .prepareStatement("SELECT * FROM Employee WHERE Emp_Id=?");
//        preparedStatement.setObject(1,employee.getEmpId());
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            employee.setName(String.valueOf(resultSet.getObject(2)));
//            employee.setAddress(String.valueOf(resultSet.getObject(3)));
//            employee.setDob(String.valueOf(resultSet.getObject(4)));
//            employee.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
//            employee.setDescription(String.valueOf(resultSet.getObject(6)));
//            employee.setEmail(String.valueOf(resultSet.getObject(7)));
//            employee.setNic(String.valueOf(resultSet.getObject(8)));
//            employee.setJobTitle(String.valueOf(resultSet.getObject(9)));
//            return true;
//        }
//        return false;
//    }
    public static String checkId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));

        }
        return null;
    }
//    public static String getAdminUserName() throws SQLException, ClassNotFoundException {
//        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
//                .prepareStatement("SELECT User_name FROM Employee WHERE Job_title='Admin'");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            return String.valueOf(resultSet.getObject(1));
//        }
//        return null;
//    }
    public static boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Phone_number FROM Employee WHERE Job_title='Admin'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            employee.setPhoneNumber(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    public static boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Employee SET Address=?,DOB=?,Phone_number=?,Description=? WHERE Job_title='Admin';");
        preparedStatement.setObject(1,employee.getAddress());
        preparedStatement.setObject(2,employee.getDob());
        preparedStatement.setObject(3,employee.getPhoneNumber());
        preparedStatement.setObject(4,employee.getDescription());
        boolean executeUpdate = preparedStatement.executeUpdate() > 0;
        if(executeUpdate){
            return true;
        }
        return false;
    }

    public static Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT e.Job_title FROM Employee e JOIN User u ON  e.Emp_Id = u.Emp_Id WHERE u.User_name=?");
        preparedStatement.setObject(1,user.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Employee employee=new Employee();
            employee.setJobTitle(String.valueOf(resultSet.getObject(1)));
            return employee;
        }
        return null;
    }

    public static ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees=new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM Employee");
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            do {
                Employee employee=new Employee();
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
            }while(resultSet.next());
            return employees;
        }
        return new ArrayList<>();
    }

    public static ArrayList<Employee> searchEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees=new ArrayList<>();
        String setColumn;
        Pattern namePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = namePattern.matcher(employee.getName());
        if (matcher.matches()) {
            setColumn="SELECT * FROM Employee WHERE Name LIKE ?";
//            System.out.println("ddd");
        }else {
            setColumn="SELECT * FROM Employee WHERE Emp_Id LIKE ?";
        }
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1,"%"+employee.getName()+"%");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Employee searchEmployee=new Employee();
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Emp_Id FROM Employee WHERE Job_title='Beautician'");
        ResultSet resultSet = prepareStatement.executeQuery();
        ArrayList<Employee> employees=new ArrayList<>();
        while (resultSet.next()) {
            Employee searchEmployee=new Employee();
            searchEmployee.setEmpId(String.valueOf(resultSet.getObject(1)));
            employees.add(searchEmployee);
        }
        return employees;

    }

    public static ArrayList<Employee> searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees=new ArrayList<>();
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement("SELECT * FROM Employee WHERE Emp_Id = ?");
        prepareStatement.setObject(1,employee.getEmpId());
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Employee searchEmployee=new Employee();
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
