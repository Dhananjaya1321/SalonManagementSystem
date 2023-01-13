package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Appointment;
import lk.ijse.salongeetha.to.Payment;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;
import lk.ijse.salongeetha.to.tm.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends SQLUtil<Appointment> {
    boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException;

    ArrayList<Appointment> getIds() throws SQLException, ClassNotFoundException;

    boolean getId(Payment payment) throws SQLException, ClassNotFoundException;

    String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ArrayList<AppointmentTM> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;
}
