package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.AppointmentDTO;
import lk.ijse.salongeetha.to.PaymentDTO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends SQLUtil<AppointmentDTO> {
    boolean addAppointment(AppointmentDTO appointmentDTO, ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException;

    ArrayList<AppointmentDTO> getIds() throws SQLException, ClassNotFoundException;

    boolean getId(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    String getAppointmentCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getAppointmentForChart(String time) throws SQLException, ClassNotFoundException;
}
