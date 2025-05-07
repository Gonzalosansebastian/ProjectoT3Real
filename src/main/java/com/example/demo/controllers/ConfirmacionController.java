package com.example.demo.controllers;

import com.example.application.Main;
import com.example.session.SesionEspectaculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmacionController implements Initializable {

    @FXML
    private Label labelTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double total = SesionEspectaculo.getTotalReserva();
        labelTotal.setText("💶 Total a pagar: " + total + " €");
    }

    @FXML
    public void volverACartelera(ActionEvent event) throws IOException {
        Main.changeScene("/fxml/afterLogin.fxml");
    }
}
