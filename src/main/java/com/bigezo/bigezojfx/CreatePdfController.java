package com.bigezo.bigezojfx;

import com.itextpdf.text.Font;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

public class CreatePdfController {

    @FXML
    private TextField bgPicUrlField;

    @FXML
    private TextField bgPicUrlField2;

    @FXML
    private TextField csvFilePathField;

    @FXML
    private Button generateButton;

    @FXML
    private TextArea logArea;



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
    void createonepage(ActionEvent event) throws IOException {
        s.loadScene(event, "createonepdf.fxml");
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        s.loadScene(event, "home.fxml");
    }
    LoadBrowser loadBrowser=new LoadBrowser();
    @FXML
    void documentation(ActionEvent event) throws IOException {
        loadBrowser.loadBrowser("https://bigezo.app.netlify/card/documentation");
    }
    @FXML
    void getPremium(ActionEvent event) {

        loadBrowser.loadBrowser("https://bigezo.app.netlify/card/premium");
        //loadBrowser.showErrorDialog("String message");
    }



    @FXML
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
            Path directoryPath = Paths.get(System.getProperty("user.home"), "Documents", "CARD");
            Files.createDirectories(directoryPath); // Creates directories if they do not exist

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

    @FXML
    void getTemplate(ActionEvent event) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // Load the PDF file from resources
            inputStream = getClass().getResourceAsStream("/2PageTemplate.pdf");
            if (inputStream == null) {
                throw new IOException("PDF file not found in resources");
            }

            // Use FileChooser to save the file to a chosen location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("2PageTemplate.pdf");
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

    @FXML
    void getTemplateCsv(ActionEvent event) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // Load the PDF file from resources
            inputStream = getClass().getResourceAsStream("/SampleListOfNames.csv");
            if (inputStream == null) {
                throw new IOException("CSV file not found in resources");
            }

            // Use FileChooser to save the file to a chosen location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save CSV File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            fileChooser.setInitialFileName("SampleListOfNames.csv");
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
