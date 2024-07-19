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

public class CreateOnePdfController {


    @FXML
    private TextField bgPicUrlField;

    @FXML
    private TextField csvFilePathField;

    @FXML
    private Button generateButton;

    @FXML
    private TextArea logArea;

    @FXML
    private MenuItem onepage;

    @FXML
    private MenuItem twopage;

    @FXML
    private MenuItem close;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    // You can initialize components here if needed
    CreatePdfController c=new CreatePdfController();

    @FXML
    private void initialize() {


    }


    @FXML
    void close(ActionEvent event) {
        if (stage != null) {
            stage.close();
        } else {
            System.out.println("Stage is not initialized.");
        }
    }

    @FXML
    void createonepage(ActionEvent event) throws IOException {

        c.loadScene(event, "createonepdf.fxml");
    }

    @FXML
    void createtwopages(ActionEvent event) throws IOException {
        c.loadScene(event, "createtwopdf.fxml");
    }
    @FXML
    void getPremium(ActionEvent event) {
        // Implement logic for premium feature
    }

    @FXML
    private void generateCards() {
        String bgPicUrl = bgPicUrlField.getText();
        String csvFilePath = csvFilePathField.getText();

        CreateCards c = new CreateCards();

        try {
            // Read names from CSV file
            List<String> names = readNamesFromCsv(csvFilePath);

            // Set default values for other parameters
            ChooseFont chooseFont = new ChooseFont();
            Font chosenFont = chooseFont.myFont("helvetica");

            // Define the directory and create it if it doesn't exist
            Path directoryPath = Paths.get(System.getProperty("user.home"), "Documents", "CARD");
            Files.createDirectories(directoryPath); // Creates directories if they do not exist

            for (String personName : names) {
                // Generate the card PDF
                logArea.appendText("Starting to create card for " + personName + "\n");
                byte[] pdfBytes = c.generateOneCard(personName, bgPicUrl,  chosenFont);
                String cardName = personName + "_card.pdf";
                cardName = cardName.toUpperCase();
                // Define the file path
                Path filePath = directoryPath.resolve(cardName);

                // Write the PDF bytes to the file
                try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                    fos.write(pdfBytes);
                    logArea.appendText("PDF created successfully at: " + filePath + "\n");
                } catch (IOException e) {
                    logArea.appendText("Failed to write PDF to file for " + personName + ": " + e.getMessage() + "\n");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            logArea.appendText("Error: Please ensure correct file types and paths "  + "\n");
            e.printStackTrace();
        }
    }

    private List<String> readNamesFromCsv(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
