package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceAppointmentDAO;
import lk.ijse.salongeetha.entity.ServiceAppointmentDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceAppointmentDAOImpl implements ServiceAppointmentDAO {
    @Override
    public boolean addDetails(ArrayList<ServiceAppointmentDetail> details) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (ServiceAppointmentDetail a : details) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO service_appointment_detail VALUES (?,?)", a.getAptId(), a.getSevId());
            if (isAdded) {
                i++;
            }
        }
        if (i == details.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(ServiceAppointmentDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(ServiceAppointmentDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<ServiceAppointmentDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ServiceAppointmentDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ServiceAppointmentDetail> search(boolean value, ServiceAppointmentDetail detail) throws SQLException, ClassNotFoundException {
        return null;
    }

}
