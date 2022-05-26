package com.example.fx2048;

import com.example.fx2048.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controller class.
 *
 * <p>It handles user inputs and applies to game and updates the view
 */
public class Controller {

  private Game game;

  @FXML
  private GridPane gridView;

  @FXML
  private Label labelView;

  public Controller() {
    game = new Game();
  }

  /**
   * Kick off the game with the first turn and updates the view.
   */
  public void start() {
    updateLabelView();
    updateGridView();
  }

  /**
   * moves up! and generate the next turn if possible.
   */
  public void moveUp() {
    game.moveUp();
    updateGridView();

    if (game.isGameFinished()) {
      game.increaseWinScore();
      updateLabelView();
      return;
    }

    if (game.nextTurn()) {
      updateGridView();
    }
  }

  /**
   * moves down! and generate the next turn if possible.
   */
  public void moveDown() {
    game.moveDown();
    updateGridView();

    if (game.isGameFinished()) {
      game.increaseWinScore();
      updateLabelView();
      return;
    }

    if (game.nextTurn()) {
      updateGridView();
    }
  }

  /**
   * moves left! and generate the next turn if possible.
   */
  public void moveLeft() {
    game.moveLeft();
    updateGridView();

    if (game.isGameFinished()) {
      game.increaseWinScore();
      updateLabelView();
      return;
    }

    if (game.nextTurn()) {
      updateGridView();
    }
  }

  /**
   * moves right! and generate the next turn if possible.
   */
  public void moveRight() {
    game.moveRight();
    updateGridView();

    if (game.isGameFinished()) {
      game.increaseWinScore();
      updateLabelView();
      return;
    }

    if (game.nextTurn()) {
      updateGridView();
    }
  }

  /**
   * Resets game grid and view.
   */
  public void reset() {
    game = new Game();
    start();
  }

  private void updateLabelView() {
    labelView.setText(String.valueOf(game.getWinScore()));
  }

  private void updateGridView() {
    int gridPaneNodeIndex = 0;
    TextField textNode;
    for (int i = 0; i < Game.DIMENSION; i++) {
      for (int j = 0; j < Game.DIMENSION; j++) {
        textNode = (TextField) gridView.getChildren().get(gridPaneNodeIndex++);
        String newValue = String.valueOf(game.getGameGrid().getGrid()[i][j].getValue());
        updateTextFieldView(textNode, newValue);
      }
    }
  }

  private void updateTextFieldView(TextField textField, String newValue) {
    if (!newValue.equals("0")) {
      textField.setText(newValue);
    } else {
      textField.setText("");
    }
    updateTextFieldViewCssClass(textField, newValue);
    updateTextFieldViewAnimation(textField, newValue);
  }

  private void updateTextFieldViewCssClass(TextField textField, String newValue) {
    textField.getStyleClass().set(0, "text-field-".concat(newValue));
  }

  private void updateTextFieldViewAnimation(TextField textField, String newValue) {
    String oldValue = textField.getText();
    if (!oldValue.isBlank() && !oldValue.equals(newValue)) {
      new animatefx.animation.BounceIn(textField).play();
    }
  }
}