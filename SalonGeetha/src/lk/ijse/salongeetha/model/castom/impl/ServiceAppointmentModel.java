package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.ServiceAppointmentDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceAppointmentModel {

    public static boolean addDetails(ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (ServiceAppointmentDetail a : serviceAppointmentDetails) {
            PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                    .prepareStatement("INSERT INTO service_appointment_detail VALUES (?,?)");
            prepareStatement.setObject(1, a.getAptId());
            prepareStatement.setObject(2, a.getSevId());
            int executeUpdate = prepareStatement.executeUpdate();
            if (executeUpdate > 0) {
                i++;
            }
        }

        if (i == serviceAppointmentDetails.size()) {
            return true;
        }
        return false;
    }

    public static ArrayList<ServiceAppointmentDetail> getAmountDue(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException {

        ArrayList<ServiceAppointmentDetail> serviceAppointmentDetails=new ArrayList<>();
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("select s.price,s.discount from service_appointment_detail ad join service s on ad.sev_Id = s.sev_Id where Apt_Id=?");
        prepareStatement.setObject(1,serviceAppointmentDetail.getAptId());
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            ServiceAppointmentDetail setServiceAppointmentDetail=new ServiceAppointmentDetail();
            setServiceAppointmentDetail.setPrice((Double) resultSet.getObject(1));
            setServiceAppointmentDetail.setDiscount((Double) resultSet.getObject(2));
            serviceAppointmentDetails.add(setServiceAppointmentDetail);

        }
        return serviceAppointmentDetails;


    }
}
