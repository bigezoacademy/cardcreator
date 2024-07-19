package com.bigezo.bigezojfx;

import com.itextpdf.text.Font;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BigezoApplication extends Application {
    private TextField bgPicUrlField;
    private TextField bgPicUrlField2;
    private TextField csvFilePathField;
    private Button generateButton;
    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Generator");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));

        bgPicUrlField = new TextField();
        bgPicUrlField.setPromptText("Page 1 Image path");
        inputGrid.add(new Label("Page 1 Image path:"), 0, 0);
        inputGrid.add(bgPicUrlField, 1, 0);

        bgPicUrlField2 = new TextField();
        bgPicUrlField2.setPromptText("Page 2 Image path");
        inputGrid.add(new Label("Page 2 Image path:"), 0, 1);
        inputGrid.add(bgPicUrlField2, 1, 1);

        csvFilePathField = new TextField();
        csvFilePathField.setPromptText("CSV File Path");
        inputGrid.add(new Label("CSV File Path:"), 0, 2);
        inputGrid.add(csvFilePathField, 1, 2);

        generateButton = new Button("Generate Cards");
        inputGrid.add(generateButton, 0, 3, 2, 1);

        logArea = new TextArea();
        logArea.setEditable(false);
        ScrollPane scrollPane = new ScrollPane(logArea);

        root.setTop(inputGrid);
        root.setCenter(scrollPane);

        generateButton.setOnAction(event -> generateCards());

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    private void generateCards() {
        String bgPicUrl = bgPicUrlField.getText();
        String bgPicUrlPage2 = bgPicUrlField2.getText();
        String csvFilePath = csvFilePathField.getText();

        CreateCards c = new CreateCards();

        try {
            // Read names from CSV file
            List<String> names = readNamesFromCsv(csvFilePath);

            // Set default values for other parameters
            ChooseFont chooseFont = new ChooseFont();
            Font chosenFont = chooseFont.myFont("helvetica");

            // Define the directory and create it if it doesn't exist
            Path directoryPath = Paths.get("C:\\Users\\dell\\Documents\\CARD");
            File directory = directoryPath.toFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            for (String personName : names) {
                // Generate the card PDF
                logArea.appendText("Starting to create card for " + personName + "\n");
                byte[] pdfBytes = c.generateCard(personName, bgPicUrl, bgPicUrlPage2, chosenFont);
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
            logArea.appendText("Error: " + e.getMessage() + "\n");
            System.out.println(e.getMessage());

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
