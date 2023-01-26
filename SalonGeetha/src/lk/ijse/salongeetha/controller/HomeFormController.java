package lk.ijse.salongeetha.controller;

import animatefx.animation.ZoomInUp;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.salongeetha.bo.BOImplTypes;
import lk.ijse.salongeetha.bo.FactoryBOImpl;
import lk.ijse.salongeetha.bo.castom.HomeFormBO;
import lk.ijse.salongeetha.dto.CustomDTO;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeFormController {

    public LineChart lineChart;
    public NumberAxis y;
    public CategoryAxis x;
    public JFXComboBox cmbPastTime;
    @FXML
    private Label lblBooking;

    @FXML
    private Label lblAppointment;

    @FXML
    private AnchorPane popUpPane;
    HomeFormBO homeFormBO = (HomeFormBO) FactoryBOImpl.getFactoryBO().setBO(BOImplTypes.HOME);

    private void setLblAppointment() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String setDate = String.valueOf(date);

        try {
            int appointmentCount = homeFormBO.getAppointmentCount(setDate);
            if (appointmentCount >= 0) {
                lblAppointment.setText(String.valueOf(appointmentCount));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setLblBooking() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String setDate = String.valueOf(date);
        try {
            int bookingCount = homeFormBO.getBookingCount(setDate);
            if (bookingCount >= 0) {
                lblBooking.setText(String.valueOf(bookingCount));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws IOException {

        String[] times = new String[]{"Past 7 day", "Past 30 day", "Past 1 year", "From inception to date"};
        cmbPastTime.getItems().setAll(times);
        cmbPastTime.setValue("Past 7 day");
        setLineChart(cmbPastTime.getValue().toString());
        setLblAppointment();
        setLblBooking();
        try {
            EmployeeDTO employeeDTO = homeFormBO.checkAdmin();
            if (employeeDTO.getPhoneNumber().equals("") || employeeDTO.getPhoneNumber() == null) {
                Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/AddAdminDetailsForm.fxml"));
                popUpPane.getChildren().clear();
                popUpPane.getChildren().add(load);
                new ZoomInUp(popUpPane).play();
            } else {
                popUpPane.setVisible(false);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void setLineChart(String time) {
        lineChart.setAnimated(true);
        XYChart.Series seriesBooking = new XYChart.Series();
        XYChart.Series seriesAppointment = new XYChart.Series();
        try {
            ArrayList<CustomDTO> bookingForChart = getBookingForChart(time);
            for (CustomDTO b : bookingForChart) {
                seriesBooking.getData().add(new XYChart.Data(b.getDate(), b.getAvaliableCount()));
            }
            ArrayList<CustomDTO> appointmentForChart = getAppointmentForChart(time);
            for (CustomDTO a : appointmentForChart) {
                seriesBooking.getData().add(new XYChart.Data(a.getDate(), a.getAvaliableCount()));
            }
            seriesBooking.getData().add(new XYChart.Data("55", 555));
            lineChart.getData().addAll(seriesBooking, seriesAppointment);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<CustomDTO> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> appointmentTMS = homeFormBO.getAppointmentForChart(time);
        return appointmentTMS;
    }

    private ArrayList<CustomDTO> getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> bookTMS = homeFormBO.getBookingForChart(time);
        return bookTMS;
    }

    public void cmbPastTime(ActionEvent actionEvent) {
        setLineChart(cmbPastTime.getValue().toString());
    }
}
