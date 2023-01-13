package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select s.price,s.discount from service_appointment_detail ad join service s on ad.sev_Id = s.sev_Id where Apt_Id=?", serviceAppointmentDetail.getAptId());
    }

    @Override
    public ResultSet getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select es.Emp_Id,es.Sev_Id,e.Name,s.Name from employee_service_detail es join \n" +
                "service s on es.Sev_Id = s.Sev_Id join employee e on es.Emp_Id = e.Emp_Id;");
    }
    @Override
    public ResultSet getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select bd.Qty,bd.For_how_many_days,r.Price_pre_day,r.Discount \n" +
                "from book_rentals_detail bd join rentals r on bd.Rent_Id = r.Rent_Id where Bok_Id = ?", bookRentalsDetail.getBokId());
    }
}
