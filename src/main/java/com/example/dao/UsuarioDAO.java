package com.example.dao;

import com.example.ddbb.DatabaseConnection;
import com.example.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static Usuario autenticarUsuario(String email, String password) throws SQLException {
        String query = "SELECT * FROM USUARIOS WHERE email = ? AND contraseña = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("email");
                String pass = resultSet.getString("contraseña");

                return new Usuario(id, nombre, correo, pass);
            }
        }

        return null; // Si no encuentra el usuario
    }
}
