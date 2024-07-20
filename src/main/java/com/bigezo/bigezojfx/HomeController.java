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
    LoadScene s=new LoadScene();
    public void setStage(Stage stage) {
        this.stage = stage;
        s.setStage(stage); // Ensure the stage is set in LoadScene
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
        s.loadScene(event, "createonepdf.fxml");
    }

    @FXML
    void getTemplate(ActionEvent event) throws IOException {

    }

    @FXML
    void createtwopages(ActionEvent event) throws IOException {
        s.loadScene(event, "createpdf.fxml");
    }
    @FXML
    void getPremium(ActionEvent event) {
        // Implement logic for premium feature
    }


}
