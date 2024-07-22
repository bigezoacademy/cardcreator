package com.bigezo.bigezojfx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Application {

    @Override
    public void start(Stage splashStage) {
        // Load the splash screen image with transparency
        Image splashImage = new Image(getClass().getResourceAsStream("/images/splash.png"));
        ImageView splashImageView = new ImageView(splashImage);

        // Get the primary screen's dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Calculate the desired width and height of the splash screen
        double splashWidth = screenWidth / 3; // 1/3 of the screen width
        double splashHeight = splashImage.getHeight() * (splashWidth / splashImage.getWidth()); // Maintain aspect ratio

        // Resize the ImageView to fit the desired dimensions
        splashImageView.setFitWidth(splashWidth);
        splashImageView.setFitHeight(splashHeight);
        splashImageView.setPreserveRatio(true);

        // Use a StackPane to hold the ImageView
        StackPane splashPane = new StackPane(splashImageView);
        splashPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

        // Create a scene with the StackPane
        Scene splashScene = new Scene(splashPane, splashWidth, splashHeight, Color.TRANSPARENT);

        // Set the stage to have a transparent background
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.setScene(splashScene);

        // Center the splash screen on the screen
        splashStage.setX((screenWidth - splashWidth) / 2);
        splashStage.setY((screenHeight - splashHeight) / 2);

        // Show the splash screen
        splashStage.show();

        // Load the main application in a separate thread
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Show splash screen for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            javafx.application.Platform.runLater(() -> {
                try {
                    // Initialize and show the main application stage
                    Stage mainStage = new Stage();
                    new BigezoApplication().start(mainStage);
                    splashStage.close(); // Close splash screen
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
