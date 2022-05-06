package com.example.fx2048;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main App class.
 *
 * <p>Initialize the view using the FXML file and initialize main Controller to support user key
 * inputs
 */
public class Application extends javafx.application.Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
    Parent root = loader.load();
    Controller controller = loader.getController();
    controller.start();
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      switch (keyEvent.getCode()) {
        case A -> controller.moveLeft();
        case W -> controller.moveUp();
        case S -> controller.moveDown();
        case D -> controller.moveRight();
        default -> {
        }
      }
    });
    stage.setTitle("FX 2048");
    stage.setScene(scene);
    stage.show();
    scene.getRoot().requestFocus();
  }
}