package com.example.demo.controllers;

import com.example.application.Main;
import com.example.ddbb.DatabaseConnection;
import com.example.models.Usuario;
import com.example.session.SesionUsuario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public Login() {

    }
    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button button2;

    public void userLogin(ActionEvent event) {
        try {
            checkLogin();
        } catch (SQLException e) {
            e.printStackTrace();
            wrongLogIn.setText("Error al conectarse a la base de datos.");
        }
    }

    private void checkLogin() throws SQLException {
        String userInput = username.getText();
        String passInput = password.getText();

        if (userInput.isEmpty() || passInput.isEmpty()) {
            wrongLogIn.setText("Por favor introduce tus datos.");
            return;
        }

        String query = "SELECT * FROM USUARIOS WHERE email = ? AND contraseña = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userInput);
            stmt.setString(2, passInput);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String password = resultSet.getString("contraseña");

                Usuario usuario = new Usuario(id, nombre, email, password);

                // Guardar usuario en sesión
                SesionUsuario.setUsuario(usuario);

                Main.changeScene("/fxml/afterlogin.fxml");

            } else {
                wrongLogIn.setText("Usuario o contraseña inválidos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            wrongLogIn.setText("Error al acceder a la base de datos.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void registrar(ActionEvent event) throws IOException{
        Main.changeScene("/fxml/registro.fxml");
    }
    }

