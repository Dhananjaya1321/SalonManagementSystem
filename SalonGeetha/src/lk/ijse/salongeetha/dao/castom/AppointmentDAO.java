package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.Appointment;
import lk.ijse.salongeetha.entity.AppointmentPayment;
import lk.ijse.salongeetha.entity.ServiceAppointmentDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends SQLUtil<Appointment> {
    boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetailS) throws SQLException, ClassNotFoundException;

    ArrayList<Appointment> getIds() throws SQLException, ClassNotFoundException;

    String getId(AppointmentPayment payment) throws SQLException, ClassNotFoundException;

    int getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;
}
