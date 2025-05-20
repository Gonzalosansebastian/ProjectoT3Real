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
    @FXML
    private Label labelTotalSeleccionado;
    @FXML
    private Button btnCancelarReservas;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnLogout;


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
                    actualizarPrecioTotal();
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

        if (seleccionadas.isEmpty()) {
            mostrarAlerta("Atención", "No has seleccionado ninguna butaca.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Verificar cuántas reservas tiene ya el usuario para este espectáculo
            String countSQL = "SELECT COUNT(*) FROM RESERVAS WHERE id_usuario = ? AND id_espectaculo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(countSQL)) {
                stmt.setInt(1, usuario.getId());
                stmt.setInt(2, espectaculo.getId());
                var rs = stmt.executeQuery();
                int reservasExistentes = 0;
                if (rs.next()) {
                    reservasExistentes = rs.getInt(1);
                }

                int totalReservas = reservasExistentes + seleccionadas.size();
                if (totalReservas > 4) {
                    mostrarAlerta("Límite excedido", "Solo puedes reservar un máximo de 4 butacas por espectáculo. Ya has reservado " + reservasExistentes + ".");
                    return;
                }
            }

            // Insertar nuevas reservas
            double total = 0.0;
            for (Butaca b : seleccionadas) {
                String insertSQL = "INSERT INTO RESERVAS (id_reserva, id_espectaculo, id_butaca, estado, id_usuario, precio) " +
                        "VALUES (reservas_seq.NEXTVAL, ?, ?, 'ocupada', ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setInt(1, espectaculo.getId());
                    stmt.setInt(2, b.getId());
                    stmt.setInt(3, usuario.getId());
                    double precio = b.getTipo().equalsIgnoreCase("VIP") ? espectaculo.getPrecioVip() : espectaculo.getPrecioBase();
                    stmt.setDouble(4, precio);
                    total += precio;
                    stmt.executeUpdate();
                }
            }

            SesionEspectaculo.setTotalReserva(total);
            Main.changeScene("/fxml/confirmacion.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo completar la reserva.");
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
    private void actualizarPrecioTotal() {
        double total = 0.0;
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();

        for (Butaca b : seleccionadas) {
            total += b.getTipo().equalsIgnoreCase("VIP") ?
                    espectaculo.getPrecioVip() : espectaculo.getPrecioBase();
        }

        labelTotalSeleccionado.setText("💶 Total: " + total + " €");
    }
    @FXML
    private void cancelarMisReservas() {
        Usuario usuario = SesionUsuario.getUsuario();
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar cancelación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Estás seguro de que deseas cancelar todas tus reservas para esta película?");

        confirm.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK") || response.getButtonData().isDefaultButton()) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "DELETE FROM RESERVAS WHERE id_usuario = ? AND id_espectaculo = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, usuario.getId());
                        stmt.setInt(2, espectaculo.getId());
                        int rows = stmt.executeUpdate();

                        if (rows > 0) {
                            mostrarAlerta("Reservas canceladas", "Se han cancelado correctamente tus reservas.");
                        } else {
                            mostrarAlerta("Sin reservas", "No tenías reservas para esta película.");
                        }

                        recargarButacas();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mostrarAlerta("Error", "No se pudo cancelar las reservas.");
                }
            }
        });
    }
    private void recargarButacas() {
        seleccionadas.clear(); // Limpia selección
        Espectaculo espectaculo = SesionEspectaculo.getEspectaculo();
        List<Butaca> butacas = ButacaDAO.obtenerButacasPorEspectaculo(espectaculo.getId());
        cargarButacas(butacas);
        actualizarPrecioTotal(); // Refresca el precio a 0
    }



}
