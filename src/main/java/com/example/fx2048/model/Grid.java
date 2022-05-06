package com.example.fx2048.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A Grid contains a 2-dimensional array and supports basic operations over it. Some basic
 * operations are:
 *
 * <p>- getting a random free Spot
 *
 * <p>- transpose
 *
 * <p>- mirror
 *
 * <p>- reset values
 */
public class Grid {
  private final Spot[][] grid;
  private final int dimension;

  /**
   * Grid constructor. Initialize a 2-dimensional square grid of dimension provided with Spot
   * objects
   *
   * @param dimension size of the grid
   */
  public Grid(Integer dimension) {
    this.dimension = dimension;
    this.grid = new Spot[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        grid[i][j] = new Spot();
      }
    }
  }

  /**
   * Returns a random Spot in grid that has a zero value.
   *
   * @return Spot with zero value
   */
  public Spot getRandomEmptySpot() {
    List<Spot> emptySpots = Arrays.stream(grid)
        .flatMap(Arrays::stream)
        .filter(Spot::isEmpty)
        .toList();

    if (emptySpots.isEmpty()) {
      return null;
    }

    return emptySpots.get(new Random().nextInt(emptySpots.size()));
  }

  /**
   * It changes the values of the grid according to the following rule.
   *
   * <p>grid[row][col] = copyOfGrid[col][row]
   */
  public void transpose() {
    Spot[][] gridCopy = Arrays.stream(grid).map(Spot[]::clone).toArray(Spot[][]::new);
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        grid[i][j] = gridCopy[j][i];
      }
    }
  }

  /**
   * it changes the values of the grid as follows.
   *
   * <p>1 4 7
   *
   * <p>2 5 8
   *
   * <p>3 6 9
   *
   * <p>to
   *
   * <p>7 4 1
   *
   * <p>8 5 2
   *
   * <p>9 6 3
   */
  public void mirror() {
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension / 2; j++) {
        Spot spot = grid[i][j];
        grid[i][j] = grid[i][dimension - 1 - j];
        grid[i][dimension - 1 - j] = spot;
      }
    }
  }

  /**
   * Resets the boolean flag. It's used before any new turn
   */
  public void resetChangedValues() {
    Arrays.stream(grid)
        .flatMap(Arrays::stream)
        .forEach(spot -> spot.setHasChanged(false));
  }

  /**
   * Reset both Spot values and changedValue flags.
   */
  public void resetAll() {
    Arrays.stream(grid)
        .flatMap(Arrays::stream)
        .forEach(spot -> {
          spot.setValue(0);
          spot.setHasChanged(false);
        });
  }

  public Spot[][] getGrid() {
    return grid;
  }
}
