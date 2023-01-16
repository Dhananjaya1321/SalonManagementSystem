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
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.tm.AppointmentTM;
import lk.ijse.salongeetha.to.tm.BookTM;

import java.io.IOException;
import java.sql.ResultSet;
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
            String appointmentCount = homeFormBO.getAppointmentCount(setDate);
            if (appointmentCount != null) {
                lblAppointment.setText(appointmentCount);
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
            String bookingCount = homeFormBO.getBookingCount(setDate);
            if (bookingCount != null) {
                lblBooking.setText(bookingCount);
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
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            boolean isCheckedAdmin = homeFormBO.checkAdmin(employeeDTO);
            if (isCheckedAdmin) {
                if (employeeDTO.getPhoneNumber().equals("")) {
                    Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/AddAdminDetailsForm.fxml"));
                    popUpPane.getChildren().clear();
                    popUpPane.getChildren().add(load);
                    new ZoomInUp(popUpPane).play();
                } else {
                    popUpPane.setVisible(false);
                }
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
            ArrayList<BookTM> bookingForChart = getBookingForChart(time);
            for (BookTM b : bookingForChart) {
                seriesBooking.getData().add(new XYChart.Data(b.getDate(), b.getQty()));
            }
            ArrayList<AppointmentTM> appointmentForChart = getAppointmentForChart(time);
            for (AppointmentTM a : appointmentForChart) {
                seriesBooking.getData().add(new XYChart.Data(a.getDate(), a.getCount()));
            }
            seriesBooking.getData().add(new XYChart.Data("55", 555));
            lineChart.getData().addAll(seriesBooking, seriesAppointment);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<AppointmentTM> getAppointmentForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentTM> appointmentTMS = new ArrayList<>();
        ResultSet resultSet = homeFormBO.getAppointmentForChart(time);
        while (resultSet.next()) {
            AppointmentTM appointmentTM = new AppointmentTM();
            appointmentTM.setCount(resultSet.getInt(1));
            appointmentTM.setDate(resultSet.getString(2));
            appointmentTMS.add(appointmentTM);
        }
        return appointmentTMS;
    }

    private ArrayList<BookTM> getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<BookTM> bookTMS = new ArrayList<>();
        ResultSet resultSet = homeFormBO.getBookingForChart(time);
        while (resultSet.next()) {
            BookTM bookTM = new BookTM();
            bookTM.setQty(resultSet.getInt(1));
            bookTM.setDate(resultSet.getString(2));
            bookTMS.add(bookTM);
        }
        return bookTMS;
    }

    public void cmbPastTime(ActionEvent actionEvent) {
        setLineChart(cmbPastTime.getValue().toString());
    }
}
