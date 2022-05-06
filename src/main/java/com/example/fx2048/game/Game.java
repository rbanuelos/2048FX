package com.example.fx2048.game;

import com.example.fx2048.model.Grid;
import com.example.fx2048.model.Spot;
import java.util.Arrays;
import java.util.Random;

/**
 * 2048 Game class.
 *
 * <p>Provides methods for playing the game (move up, left, right, down), generates new values for
 * next turn and checks if game is over either by running out of free spots in the grid or by
 * reaching the WIN_SCORE
 */
public class Game {
  public static final int DIMENSION = 4;
  private static final int[] NEW_VALUES = {2, 4};
  private static final int WIN_SCORE = 2048;

  private final Grid gameGrid;

  public Game() {
    gameGrid = new Grid(DIMENSION);
  }

  /**
   * Reset changed value flag after previous turn. Checks for game completion and if is possible
   * to add new value in grid for next turn
   *
   * @return true if game is not over and a new random value was added to the grid, false otherwise
   */
  public boolean nextTurn() {
    gameGrid.resetChangedValues();
    return !isGameFinished() && generateNewValue();
  }

  private boolean generateNewValue() {
    Spot emptySpot = gameGrid.getRandomEmptySpot();
    if (emptySpot == null) {
      return false;
    }
    emptySpot.setValue(NEW_VALUES[new Random().nextInt(NEW_VALUES.length)]);
    return true;
  }

  /**
   * If WIN_SCORE is reach in any of the grid's spots the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameFinished() {
    return Arrays.stream(gameGrid.getGrid())
        .flatMap(Arrays::stream)
        .anyMatch(spot -> spot.getValue() == WIN_SCORE);
  }

  /**
   * Moves the game grid up. It sums values that are equal and shifts distinct values to the top.
   *
   * <p>It transposes the game grid before and after the 'move'
   */
  public void moveUp() {
    gameGrid.transpose();
    move();
    gameGrid.transpose();
  }

  /**
   * Moves the game grid down. It sums values that are equal and shifts distinct values to the
   * bottom.
   *
   * <p>It transposes and mirrors the game grid before and after the 'move'
   */
  public void moveDown() {
    gameGrid.transpose();
    gameGrid.mirror();
    move();
    gameGrid.mirror();
    gameGrid.transpose();
  }

  /**
   * Moves the game grid left. It sums values that are equal and shifts distinct values to the
   * left.
   */
  public void moveLeft() {
    move();
  }

  /**
   * Moves the game grid right. It sums values that are equal and shifts distinct values to the
   * right.
   *
   * <p>It mirrors the game grid before and after the 'move'
   */
  public void moveRight() {
    gameGrid.mirror();
    move();
    gameGrid.mirror();
  }

  public Grid getGameGrid() {
    return gameGrid;
  }

  public void reset() {
    gameGrid.resetAll();
  }

  /**
   * shifts and aggregates the grid. Both shift and aggregate only support left direction operation.
   * It's meant to be used with transpose and mirror to simulate up, down and right direction
   * operation.
   */
  private void move() {
    Spot[][] grid = gameGrid.getGrid();
    for (Spot[] spots : grid) {
      shift(spots);
      aggregate(spots);
      shift(spots);
    }
  }

  /**
   * Moves non-zero values to the start of the array. It applies the same operation for all rows in
   * the grid.
   */
  private void shift(Spot[] spots) {
    int index = 0;
    for (Spot spot : spots) {
      if (spot.getValue() != 0) {
        spots[index++].setValue(spot.getValue());
      }
    }
    for (int i = index; i < spots.length; i++) {
      spots[i].setValue(0);
    }
  }

  /**
   * Sums a pair of same values (left direction). Stores result in most left spot and set the
   * other value to zero. It applies the same operation for all pair values for every row in the
   * grid.
   */
  private void aggregate(Spot[] spots) {
    for (int i = 1; i < DIMENSION; i++) {
      if (spots[i].getValue() == 0) {
        continue;
      }
      if (spots[i].getValue() == spots[i - 1].getValue() && !spots[i].hasChanged()) {
        spots[i - 1].setValue(spots[i].getValue() + spots[i - 1].getValue());
        spots[i - 1].setHasChanged(true);
        spots[i].setValue(0);
      }
    }
  }
}
