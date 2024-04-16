package org.javastart.main.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.javastart.main.Database.DbConnect;

import java.sql.*;

public class ControllerAddUser {
    @FXML
    private Button btn_AddConfirm;
    @FXML
    private Button btn_AddCancel;
    @FXML
    private TextField tf_AddUsername;
    @FXML
    private TextField tf_AddPassword;

    //function to add user into database.

    public void AddUser(ActionEvent event){
        DbConnect connection = new DbConnect();
        try (Connection connectDB = connection.getDbConnection()) {
            String Query = "INSERT INTO user (usernames, password, waktu) VALUES (?, ?, 0)";
            try (PreparedStatement st = connectDB.prepareStatement(Query)) {
                st.setString(1, tf_AddUsername.getText());
                st.setString(2, tf_AddPassword.getText());
                st.executeUpdate();
                Node sources=(Node) event.getSource();
                Stage stage=(Stage) sources.getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                System.err.println("Gagal menambahkan data pengguna: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database: " + e.getMessage());
        }
    }


    //function to close popup window.
    public void cancelAction(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
