package com.bigezo.bigezojfx;

import javafx.scene.control.Alert;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoadBrowser {
    void loadBrowser(String link){

            try {
                // Check if Desktop is supported on the current platform
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    // Check if browsing is supported
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(new URI(link));
                    } else {
                        showErrorDialog("Browsing not supported on this platform.");
                    }
                } else {
                    showErrorDialog("Desktop is not supported on this platform.");
                }
            } catch (IOException | URISyntaxException e) {
                showErrorDialog("Failed to open the website: " + e.getMessage());
            }
        }

         void showErrorDialog(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

}
