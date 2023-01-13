package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceAppointmentDAO extends SQLUtil<ServiceAppointmentDetail> {
    ArrayList<ServiceAppointmentDetail> getAmountDue(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException;

    boolean addDetails(ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException;
}
