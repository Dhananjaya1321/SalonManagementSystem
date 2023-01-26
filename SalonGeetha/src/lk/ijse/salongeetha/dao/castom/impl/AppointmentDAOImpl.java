package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.entity.Appointment;
import lk.ijse.salongeetha.entity.AppointmentPayment;
import lk.ijse.salongeetha.entity.ServiceAppointmentDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Appointment (Apt_Id,Date,Time,NIC) VALUES (?,?,?,?)", appointment.getAptId()
                    , appointment.getDate(), appointment.getTime(), appointment.getNic());
    }

    @Override
    public boolean add(Appointment user) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(Appointment appointment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Appointment WHERE Apt_Id=?", appointment.getAptId());
    }

    @Override
    public ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Appointment SET Status=? WHERE Apt_Id=?", appointment.getStatus(), appointment.getAptId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM Appointment ORDER BY Apt_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> search(boolean value, Appointment to) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Appointment> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointmentDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM Appointment WHERE Status='Pending'");
        while (resultSet.next()) {
            Appointment appointmentDTO = new Appointment();
            appointmentDTO.setAptId(String.valueOf(resultSet.getObject(1)));
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    @Override
    public String getId(AppointmentPayment payment) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from Appointment where Apt_Id=?", payment.getApt_Id());
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public int getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT COUNT(Apt_Id) FROM Appointment WHERE Date=?", setDate);
        if (resultSet.next()) {
           return resultSet.getInt(1);
        }
        return -1;
    }
}
