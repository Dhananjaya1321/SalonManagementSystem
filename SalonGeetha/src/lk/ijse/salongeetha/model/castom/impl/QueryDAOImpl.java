package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.QueryDAO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ResultSet getAmountDue(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select s.price,s.discount from service_appointment_detail ad join service s on ad.sev_Id = s.sev_Id where Apt_Id=?", serviceAppointmentDetail.getAptId());
    }
}
