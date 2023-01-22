package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import lk.ijse.salongeetha.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class RepostsFormController {

    @FXML
    private GridPane rigthPane;

    @FXML
    private JFXButton btnAppointmentReport;

    @FXML
    private JFXButton btnCustomerReport;

    @FXML
    private JFXButton btnBookingReport;
    @FXML
    void btnAppointmentReport(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/view/report/appointmentReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnBookingReport(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/view/report/bookingReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btncustomerReport(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/view/report/CustomerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
