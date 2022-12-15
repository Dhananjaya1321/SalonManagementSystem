package lk.ijse.salongeetha.controller;

import com.jfoenix.controls.JFXButton;
import com.oracle.deploy.update.UpdateCheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/appointmentReport.jrxml");
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
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/bookingReport.jrxml");
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
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/salongeetha/report/CustomerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDBConnection().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
