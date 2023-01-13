package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ServiceAppointmentDAO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceAppointmentDAOImpl implements ServiceAppointmentDAO {
    @Override
    public boolean addDetails(ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (ServiceAppointmentDetail a : serviceAppointmentDetails) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO service_appointment_detail VALUES (?,?)", a.getAptId(), a.getSevId());
            if (isAdded) {
                i++;
            }
        }
        if (i == serviceAppointmentDetails.size()) {
            return true;
        }
        return false;
    }



    @Override
    public boolean add(ServiceAppointmentDetail user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(ServiceAppointmentDetail user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ServiceAppointmentDetail supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, ServiceAppointmentDetail to) throws SQLException, ClassNotFoundException {
        return null;
    }

}
