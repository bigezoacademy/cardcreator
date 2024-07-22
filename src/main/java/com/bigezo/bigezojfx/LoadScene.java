package com.bigezo.bigezojfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScene {
    private Stage stage;

    private Scene scene;
    private Parent root;

    public void setStage(Stage stage) {
        this.stage = stage;
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
            if (this.stage != null) {
                stage = this.stage;
            } else {
                throw new IllegalStateException("Stage not set. Call setStage() before loadScene().");
            }
        }

        // Set the new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Bigezo Card Generator - Version 1.0");
        stage.show();
    }
}
