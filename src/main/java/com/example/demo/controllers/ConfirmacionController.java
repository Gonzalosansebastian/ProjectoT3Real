package com.example.demo.controllers;

import com.example.application.Main;
import com.example.ddbb.DatabaseConnection;
import com.example.models.Espectaculo;
import com.example.models.Usuario;
import com.example.session.SesionEspectaculo;
import com.example.session.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    @FXML
    public void cancelarReservas() {
        Usuario usuario = SesionUsuario.getUsuario();
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM RESERVAS WHERE id_usuario = ? AND id_espectaculo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, usuario.getId());
                stmt.setInt(2, espectaculo.getId());
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    mostrarAlerta("Éxito", "Reservas canceladas correctamente.");
                    Main.changeScene("/fxml/afterLogin.fxml"); // Vuelve al menú
                } else {
                    mostrarAlerta("Aviso", "No hay reservas para cancelar.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Hubo un problema al cancelar las reservas.");
        }
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
