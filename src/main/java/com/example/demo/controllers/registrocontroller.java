package com.example.demo.controllers;

import com.example.application.Main;
import com.example.ddbb.DatabaseConnection;
import com.example.session.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registrocontroller {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    public void registrarUsuario(ActionEvent event) {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Completa todos los campos.");
            return;
        }

        if (emailExiste(email)) {
            statusLabel.setText("Este correo ya está registrado.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO USUARIOS (id_usuario, nombre, email, contraseña) VALUES (usuarios_seq.NEXTVAL, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                statusLabel.setText("Usuario registrado con éxito.");
            } else {
                statusLabel.setText("Error al registrar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Error en la base de datos.");
        }
    }

    private boolean emailExiste(String email) {
        String query = "SELECT COUNT(*) FROM USUARIOS WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @FXML
    public void volverAtras(ActionEvent event) throws IOException {
        Main.changeScene("/fxml/sample.fxml"); // Tu pantalla de login
    }
}
