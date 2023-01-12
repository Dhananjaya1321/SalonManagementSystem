package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.AppointmentDAO;
import lk.ijse.salongeetha.model.castom.ServiceAppointmentDAO;
import lk.ijse.salongeetha.to.Appointment;
import lk.ijse.salongeetha.to.Payment;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;
import lk.ijse.salongeetha.to.tm.AppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentModel implements AppointmentDAO {
    ServiceAppointmentDAO serviceAppointmentDAO=new ServiceAppointmentModel();
    @Override
    public boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO Appointment (Apt_Id,Date,Time,NIC) VALUES (?,?,?,?)", appointment.getAptId()
                    , appointment.getDate(), appointment.getTime(), appointment.getNic());
            if (isAdded) {
                boolean addDetails = serviceAppointmentDAO.addDetails(serviceAppointmentDetails);
                if (addDetails) {
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
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM appointment ORDER BY Apt_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> search(Appointment supplier) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Appointment> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM Appointment WHERE Status='Pending'");
        while (resultSet.next()) {
            Appointment appointment = new Appointment();
            appointment.setAptId(String.valueOf(resultSet.getObject(1)));
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public boolean getId(Payment payment) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from appointment where Apt_Id=?", payment.getaOrBId());
        if (resultSet.next()) {
            payment.setNic(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    @Override
    public String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT COUNT(Apt_Id) FROM Appointment WHERE Date=?", setDate);
        if (resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    @Override
    public ArrayList<AppointmentTM> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentTM> appointmentTMS = new ArrayList<>();
        String quary;
        if (time.equals("Past 7 day")) {
            quary = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 7";
        } else if (time.equals("Past 30 day")) {
            quary = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 30";
        } else if (time.equals("Past 1 year")) {
            quary = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 365";
        } else {
            quary = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date";
        }
        ResultSet resultSet = CrudUtil.setQuery(quary);
        while (resultSet.next()) {
            AppointmentTM appointmentTM = new AppointmentTM();
            appointmentTM.setCount(resultSet.getInt(1));
            appointmentTM.setDate(resultSet.getString(2));
            appointmentTMS.add(appointmentTM);
        }
        return appointmentTMS;
    }
}
