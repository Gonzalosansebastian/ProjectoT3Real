package com.example.demo.controllers;

import com.example.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ConfirmacionController {

    @FXML
    public void volverACartelera(ActionEvent event) throws IOException {
        Main.changeScene("/fxml/afterLogin.fxml");
    }
}
