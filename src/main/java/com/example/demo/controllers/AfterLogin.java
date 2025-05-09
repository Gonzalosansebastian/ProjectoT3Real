package com.example.demo.controllers;

import com.example.application.Main;
import com.example.dao.EspectaculoDAO;
import com.example.models.Espectaculo;
import com.example.models.Usuario;
import com.example.session.SesionEspectaculo;
import com.example.session.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfterLogin implements Initializable {

    @FXML
    private Label bienvenidaLabel;

    @FXML
    private VBox carteleraVBox;

    @FXML
    private Button logout;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usuario usuario = SesionUsuario.getUsuario();
        if (usuario != null) {
            bienvenidaLabel.setText("¡Bienvenido, " + usuario.getNombre() + "!");
        } else {
            bienvenidaLabel.setText("Usuario no identificado.");
        }

        cargarCartelera();
    }

    private void cargarCartelera() {
        List<Espectaculo> espectaculos = EspectaculoDAO.obtenerTodos();

        for (Espectaculo e : espectaculos) {
            VBox item = new VBox();
            item.setSpacing(5);
            item.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #0F1B2B;");

            // Cargar imagen
            String imagePath = "/images/" + e.getNombre().toLowerCase().replace(" ", "") + ".jpg";
            ImageView poster = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
            poster.setFitWidth(200);
            poster.setPreserveRatio(true);

            Text titulo = new Text("🎬 " + e.getNombre());
            titulo.setStyle("-fx-fill: white;");
            Text fecha = new Text("📅 Fecha: " + e.getFecha().toString());
            fecha.setStyle("-fx-fill: white;");
            Text precio = new Text("💶 Base: " + e.getPrecioBase() + " | VIP: " + e.getPrecioVip());
            precio.setStyle("-fx-fill: white;");

            Button comprarBtn = new Button("Comprar");
            comprarBtn.setStyle("-fx-background-color:  #E6E6FA");
            comprarBtn.setOnAction(evt -> comprarEspectaculo(e));

            item.getChildren().addAll(poster, titulo, fecha, precio, comprarBtn);
            carteleraVBox.getChildren().add(item);
        }
    }


    private void comprarEspectaculo(Espectaculo espectaculo) {

        SesionEspectaculo.setEspectaculo(espectaculo);

        try {
            Main.changeScene("/fxml/butacas.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userLogOut(ActionEvent event) throws IOException {
        SesionUsuario.cerrarSesion();
        Main.changeScene("/fxml/sample.fxml");
    }
}
