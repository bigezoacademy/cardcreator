package com.bigezo.bigezojfx;

import com.itextpdf.text.Font;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeController {



    private Stage stage;

    private Scene scene;
    private Parent root;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        // You can initialize components here if needed
    }


    @FXML
    void close(ActionEvent event) {
        stage.close();
    }

    @FXML
    void createonepage(ActionEvent event) throws IOException {
        loadScene(event, "createonepdf.fxml");
    }

    @FXML
    void getTemplate(ActionEvent event) throws IOException {

    }

    @FXML
    void createtwopages(ActionEvent event) throws IOException {
        loadScene(event, "createtwopdf.fxml");
    }
    @FXML
    void getPremium(ActionEvent event) {
        // Implement logic for premium feature
    }
    public void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Get the current stage from the event source
        Stage stage;
        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            // Handle the case where the event source is not a Node
            stage = (Stage) this.stage.getScene().getWindow();
        }

        // Set the new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }


}
