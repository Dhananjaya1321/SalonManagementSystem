package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.to.AppointmentDTO;
import lk.ijse.salongeetha.to.PaymentDTO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Appointment (Apt_Id,Date,Time,NIC) VALUES (?,?,?,?)", appointmentDTO.getAptId()
                    , appointmentDTO.getDate(), appointmentDTO.getTime(), appointmentDTO.getNic());
    }

    @Override
    public boolean add(AppointmentDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Appointment WHERE Apt_Id=?", appointmentDTO.getAptId());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Appointment SET Status=? WHERE Apt_Id=?", appointmentDTO.getStatus(), appointmentDTO.getAptId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM Appointment ORDER BY Apt_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, AppointmentDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<AppointmentDTO> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Apt_Id FROM Appointment WHERE Status='Pending'");
        while (resultSet.next()) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setAptId(String.valueOf(resultSet.getObject(1)));
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    @Override
    public boolean getId(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from Appointment where Apt_Id=?", paymentDTO.getaOrBId());
        if (resultSet.next()) {
            paymentDTO.setNic(String.valueOf(resultSet.getObject(1)));
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
    public ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        String query;
        if (time.equals("Past 7 day")) {
            query = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 7";
        } else if (time.equals("Past 30 day")) {
            query = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 30";
        } else if (time.equals("Past 1 year")) {
            query = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date ORDER BY Date ASC LIMIT 365";
        } else {
            query = "SELECT COUNT(Apt_Id), Date FROM Appointment GROUP BY Date";
        }
        return CrudUtil.setQuery(query);
    }
}
