package com.example.demo.controllers;

import com.example.application.Main;
import com.example.dao.ButacaDAO;
import com.example.ddbb.DatabaseConnection;
import com.example.models.Butaca;
import com.example.models.Espectaculo;
import com.example.models.Usuario;
import com.example.session.SesionEspectaculo;
import com.example.session.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ButacasController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private GridPane gridButacas;
    @FXML
    private Button logout;
    @FXML
    private Button btnVolver;

    private List<Butaca> seleccionadas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();
        Usuario usuario = SesionUsuario.getUsuario();

        if (espectaculo != null) {
            labelTitulo.setText("🎬 " + espectaculo.getNombre() + "  📅 " + espectaculo.getFecha());

            List<Butaca> butacas = ButacaDAO.obtenerButacasPorEspectaculo(espectaculo.getId());
            cargarButacas(butacas);
        }
    }

    private void cargarButacas(List<Butaca> butacas) {
        gridButacas.getChildren().clear();
        int row = 0, col = 0;

        for (Butaca b : butacas) {
            ImageView img = new ImageView();
            InputStream is;
            if (b.isOcupada()) {
                is = getClass().getResourceAsStream("/images/butacaroja.png");
            } else if (b.getTipo().equalsIgnoreCase("VIP")) {
                is = getClass().getResourceAsStream("/images/butacaamarilla.png");
            } else {
                is = getClass().getResourceAsStream("/images/butacaverde.png");
            }
            img.setImage(new Image(is));
            img.setFitHeight(40);
            img.setFitWidth(40);

            img.setOnMouseClicked(e -> {
                if (b.isOcupada()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Butaca Ocupada");
                    alert.setHeaderText(null);
                    alert.setContentText("Esta butaca ya está ocupada. Por favor, elige otra.");
                    alert.showAndWait();
                    } else {
                    if (!seleccionadas.contains(b)) {
                        seleccionadas.add(b);
                        img.setOpacity(0.5);
                    } else {
                        seleccionadas.remove(b);
                        img.setOpacity(1);
                    }
                }
            });


            gridButacas.add(img, col, row);
            col++;
            if (col >= 10) {
                col = 0;
                row++;
            }
        }
    }


    @FXML
    public void confirmarReserva() {
        Usuario usuario = SesionUsuario.getUsuario();
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();
        if (seleccionadas.size() > 4) {
            mostrarAlerta("Límite excedido", "Solo puedes reservar un máximo de 4 butacas.");
            return;
        }

        double total = 0.0;

        try (Connection conn = DatabaseConnection.getConnection()) {
            for (Butaca b : seleccionadas) {
                String sql = "INSERT INTO RESERVAS (id_reserva, id_espectaculo, id_butaca, estado, id_usuario, precio) " +
                        "VALUES (reservas_seq.NEXTVAL, ?, ?, 'ocupada', ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, espectaculo.getId());
                    stmt.setInt(2, b.getId());
                    stmt.setInt(3, usuario.getId());
                    double precio = b.getTipo().equalsIgnoreCase("VIP") ? espectaculo.getPrecioVip() : espectaculo.getPrecioBase();
                    stmt.setDouble(4, precio);
                    total += precio;
                    stmt.executeUpdate();
                }
            }

            SesionEspectaculo.setTotalReserva(total); // Guardamos el total temporalmente
            Main.changeScene("/fxml/confirmacion.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void userLogOut() throws IOException {
        SesionUsuario.cerrarSesion();
        Main.changeScene("/fxml/sample.fxml");
    }
    public void volverAtras(ActionEvent event) throws IOException {
        Main.changeScene("/fxml/afterLogin.fxml");
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
