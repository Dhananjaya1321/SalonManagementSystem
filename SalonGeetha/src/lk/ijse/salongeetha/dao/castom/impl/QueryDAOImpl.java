package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> getAmountDueServiceAppointmentDetails(ServiceAppointmentDetail detail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select s.price,s.discount from service_appointment_detail ad join service s on ad.sev_Id = s.sev_Id where Apt_Id=?", detail.getAptId());
        ArrayList<CustomEntity> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(
                    new CustomEntity(
                            resultSet.getDouble(1),
                            resultSet.getDouble(2)
                    )
            );
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomEntity> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select es.Emp_Id,es.Sev_Id,e.Name,s.Name from employee_service_detail es join \n" +
                "service s on es.Sev_Id = s.Sev_Id join employee e on es.Emp_Id = e.Emp_Id;");
        ArrayList<CustomEntity> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(
                    new CustomEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomEntity> getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select bd.Qty,bd.For_how_many_days,r.Price_pre_day,r.Discount \n" +
                "from book_rentals_detail bd join rentals r on bd.Rent_Id = r.Rent_Id where Bok_Id = ?", bookRentalsDetail.getBokId());
        ArrayList<CustomEntity> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(
                    new CustomEntity(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getDouble(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomEntity> getAllProductService() throws SQLException, ClassNotFoundException {
        //join query ekk
        ResultSet resultSet = CrudUtil.setQuery("select ps.Pro_Id,ps.Sev_Id,ps.Qty,s.Name from product_service_detail ps inner join service s on ps.Sev_Id = s.Sev_Id;");
        ArrayList<CustomEntity> arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(
                    new CustomEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4)
                    )
            );
        }
        return arrayList;
    }

    @Override
    public boolean checkEmail(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Email FROM User u JOIN Employee e ON e.Emp_Id =" +
                " u.Emp_Id WHERE u.User_name=?", user.getUserName());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public String getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Job_title FROM Employee e JOIN User u ON  e.Emp_Id = u.Emp_Id WHERE u.User_name=?"
                , user.getUserName());
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
