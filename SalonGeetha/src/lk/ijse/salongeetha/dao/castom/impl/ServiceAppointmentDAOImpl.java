package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceAppointmentDAO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceAppointmentDAOImpl implements ServiceAppointmentDAO {
    @Override
    public boolean addDetails(ArrayList<ServiceAppointmentDetailDTO> serviceAppointmentDetailDTOS) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (ServiceAppointmentDetailDTO a : serviceAppointmentDetailDTOS) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO service_appointment_detail VALUES (?,?)", a.getAptId(), a.getSevId());
            if (isAdded) {
                i++;
            }
        }
        if (i == serviceAppointmentDetailDTOS.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(ServiceAppointmentDetailDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(ServiceAppointmentDetailDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ServiceAppointmentDetailDTO supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, ServiceAppointmentDetailDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }

}
