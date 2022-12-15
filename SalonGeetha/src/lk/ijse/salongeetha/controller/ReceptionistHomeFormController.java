package lk.ijse.salongeetha.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceptionistHomeFormController {

    public Label lblTime;
    public Label lblDate;
    @FXML
    private BorderPane fullPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private GridPane leftPane;

    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        setPane("ManageAppointment.fxml");
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        setPane("ManageBooking.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        setPane("ManageCustomer.fxml");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        setPane("HomeForm.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) fullPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        setPane("ManagePayment.fxml");
    }

    public void initialize() throws IOException {
        setPane("HomeForm.fxml");
        RunningTime();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        lblDate.setText(String.valueOf(date));
    }

    public void setPane(String formName) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/"+formName));
        rightPane.getChildren().clear();
        rightPane.getChildren().add(load);
        fullPane.setCenter(load);

    }

    private void RunningTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss aa ");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String times = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(times);
                });
            }
        });
        thread.start();
    }

}
