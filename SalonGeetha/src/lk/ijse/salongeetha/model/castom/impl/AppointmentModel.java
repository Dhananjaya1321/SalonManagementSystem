package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Appointment;
import lk.ijse.salongeetha.to.Payment;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;
import lk.ijse.salongeetha.to.tm.AppointmentTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentModel {
    public static boolean addAppointment(Appointment appointment, ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO Appointment (Apt_Id,Date,Time,NIC) VALUES (?,?,?,?)");
            preparedStatement.setObject(1, appointment.getAptId());
            preparedStatement.setObject(2, appointment.getDate());
            preparedStatement.setObject(3, appointment.getTime());
            preparedStatement.setObject(4, appointment.getNic());
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                boolean addDetails = ServiceAppointmentModel.addDetails(serviceAppointmentDetails);
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

    public static boolean deleteAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Appointment WHERE Apt_Id=?");
        preparedStatement.setObject(1, appointment.getAptId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Appointment SET Status=? WHERE Apt_Id=?");
//        preparedStatement.setObject(1,appointment.getDate());
//        preparedStatement.setObject(2,appointment.getTime());
        preparedStatement.setObject(1, appointment.getStatus());
        preparedStatement.setObject(2, appointment.getAptId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Apt_Id FROM appointment ORDER BY Apt_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));

        }
        return null;
    }

    public static ArrayList<Appointment> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Apt_Id FROM Appointment WHERE Status='Pending'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Appointment appointment = new Appointment();
            appointment.setAptId(String.valueOf(resultSet.getObject(1)));
            appointments.add(appointment);
        }
        return appointments;
    }

    public static boolean getId(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("select nic from appointment where Apt_Id=?");
        prepareStatement.setObject(1, payment.getaOrBId());
        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            payment.setNic(String.valueOf(resultSet.getObject(1)));
            return true;
        }
        return false;
    }

    public static String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().
                prepareStatement("SELECT COUNT(Apt_Id) FROM Appointment WHERE Date=?");
        prepareStatement.setObject(1, setDate);
        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    public static ArrayList<AppointmentTM> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().
                prepareStatement(quary);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            AppointmentTM appointmentTM = new AppointmentTM();
            appointmentTM.setCount(resultSet.getInt(1));
            appointmentTM.setDate(resultSet.getString(2));
            appointmentTMS.add(appointmentTM);
        }
        return appointmentTMS;
    }
}
