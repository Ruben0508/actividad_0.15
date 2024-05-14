package com.empresa.javafx_sp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_ciudad;
    @FXML
    private TextField txt_facturacion;

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            Connection conn = DriverManager.getConnection(url, "curso", "curso");
            String sp="{CALL `sp_add_clientes`(?, ?, ?)}";
            CallableStatement cst = conn.prepareCall(sp);
            cst.setString(1, txt_nombre.getText());
            cst.setString(2, txt_ciudad.getText());
            cst.setString(3, txt_facturacion.getText());
            cst.execute();
            welcomeText.setText("Cliente Guardado");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}