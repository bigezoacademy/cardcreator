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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    void createtwopages(ActionEvent event) throws IOException {
        s.loadScene(event, "createpdf.fxml");
    }
    @FXML
    void getPremium(ActionEvent event) {
        // Implement logic for premium feature
    }

    @FXML
    void getTemplate(ActionEvent event) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // Load the PDF file from resources
            inputStream = getClass().getResourceAsStream("/template.pdf");
            if (inputStream == null) {
                throw new IOException("PDF file not found in resources");
            }

            // Use FileChooser to save the file to a chosen location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            File saveFile = fileChooser.showSaveDialog(stage);

            if (saveFile != null) {
                outputStream = new FileOutputStream(saveFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // On Android and iOS, trigger an intent or appropriate action to open the file
                openFile(saveFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openFile(File file) {
        // Platform-specific code to open the file
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("win")) {
                new ProcessBuilder("cmd", "/c", file.getAbsolutePath()).start();
            } else if (osName.contains("mac")) {
                new ProcessBuilder("open", file.getAbsolutePath()).start();
            } else if (osName.contains("nux")) {
                new ProcessBuilder("xdg-open", file.getAbsolutePath()).start();
            } else {
                System.out.println("Unsupported platform");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
