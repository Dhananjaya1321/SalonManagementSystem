package lk.ijse.salongeetha.controller;

import animatefx.animation.ZoomInUp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.salongeetha.model.EmployeeModel;
import lk.ijse.salongeetha.to.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class MainFormController {

    public AnchorPane popUpPane;
    public BorderPane fullPane;
    public GridPane leftPane;
    public Label lblTime;
    @FXML
    private GridPane rightPane;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblBooking;

    @FXML
    private Label lblAppointment;

    @FXML
    private Label lblMainBar;

    @FXML
    private Label lblDate;

    @FXML
    void btnBillPaymentOnAction(ActionEvent event) throws IOException {
        setPane("ManageBillPayment.fxml","rightPane");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        setPane("ManageEmployeeForm.fxml","rightPane");
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        setPane("ManageProduct.fxml","rightPane");
    }

    @FXML
    void btnRentalsOnAction(ActionEvent event) throws IOException {
        setPane("ManageRentals.fxml","rightPane");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) throws IOException {
        setPane("RepostsForm.fxml","rightPane");
    }


    @FXML
    void btnServiceOnAction(ActionEvent event) throws IOException {
        setPane("ManageService.fxml","rightPane");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        setPane("ManageSupplier.fxml","rightPane");
    }
    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        setPane("HomeForm.fxml","rightPane");
    }
    public void btnProductServicesOnAction(ActionEvent actionEvent) throws IOException {
        setPane("ManageProductServiceForm.fxml","rightPane");
    }

    public void btnEmployeeServicesOnAction(ActionEvent actionEvent) throws IOException {
        setPane("ManageEmployeeServiceForm.fxml","rightPane");
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) fullPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/LoginForm.fxml"))));
        stage.centerOnScreen();
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


    public void setPane(String formName,String paneId) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/salongeetha/view/"+formName));
        if(paneId.equals("popUpPane")) {
            popUpPane.getChildren().clear();
            popUpPane.getChildren().add(load);
        }else if (paneId.equals("rightPane")) {
            rightPane.getChildren().clear();
            rightPane.getChildren().add(load);
            fullPane.setCenter(load);
        }else if (paneId.equals("leftPane")) {
            leftPane.getChildren().clear();
            leftPane.getChildren().add(load);
            fullPane.setLeft(load);
        }
    }
    public void initialize() throws IOException {
        setPane("HomeForm.fxml","rightPane");
        RunningTime();

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        lblDate.setText(String.valueOf(date));
    }


}
