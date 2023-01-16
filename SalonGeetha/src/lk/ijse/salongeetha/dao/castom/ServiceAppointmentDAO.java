package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.ServiceAppointmentDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceAppointmentDAO extends SQLUtil<ServiceAppointmentDetailDTO> {
    boolean addDetails(ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException;
}
