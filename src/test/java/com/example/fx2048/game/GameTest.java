package com.example.fx2048.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.fx2048.model.Spot;
import java.util.Arrays;
import org.junit.Test;

public class GameTest {

  @Test()
  public void aggregateSimpleLeftShiftTest() {
    /*
      0 0 0 0     0 0 0 0
      0 0 2 4  >  2 4 0 0
      0 0 0 0     0 0 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[1][2] = new Spot(2);
    testSpots[1][3] = new Spot(4);

    testGame.moveLeft();
    assertEquals(2, testGame.getGameGrid().getGrid()[1][0].getValue());
    assertEquals(4, testGame.getGameGrid().getGrid()[1][1].getValue());
  }

  @Test()
  public void aggregateSimpleRightShiftTest() {
    /*
      2 0 0 0     0 0 0 0
      0 0 0 2  >  2 4 0 0
      0 0 0 0     0 0 0 0
      0 4 8 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[0][0] = new Spot(2);
    testSpots[1][3] = new Spot(2);
    testSpots[3][1] = new Spot(4);
    testSpots[3][2] = new Spot(8);

    testGame.moveRight();

    assertEquals(2, testGame.getGameGrid().getGrid()[0][3].getValue());
    assertEquals(2, testGame.getGameGrid().getGrid()[1][3].getValue());
    assertEquals(4, testGame.getGameGrid().getGrid()[3][2].getValue());
    assertEquals(8, testGame.getGameGrid().getGrid()[3][3].getValue());
  }

  @Test()
  public void aggregateSimpleUpShiftTest() {
    /*
      0 0 0 0     0 0 2 4
      0 0 2 4  >  0 0 0 0
      0 0 0 0     0 0 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[1][2] = new Spot(2);
    testSpots[1][3] = new Spot(4);

    testGame.moveUp();
    assertEquals(2, testGame.getGameGrid().getGrid()[0][2].getValue());
    assertEquals(4, testGame.getGameGrid().getGrid()[0][3].getValue());
  }

  @Test()
  public void aggregateSimpleDownShiftTest() {
    /*
      2 0 0 0     0 0 0 0
      0 2 0 0  >  0 0 0 0
      0 0 2 0     0 0 0 0
      0 0 0 2     2 2 2 2
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[0][0] = new Spot(2);
    testSpots[1][1] = new Spot(2);
    testSpots[2][2] = new Spot(2);
    testSpots[3][3] = new Spot(2);


    testGame.moveDown();
    assertEquals(2, testGame.getGameGrid().getGrid()[3][0].getValue());
    assertEquals(2, testGame.getGameGrid().getGrid()[3][1].getValue());
    assertEquals(2, testGame.getGameGrid().getGrid()[3][2].getValue());
    assertEquals(2, testGame.getGameGrid().getGrid()[3][3].getValue());
  }

  @Test()
  public void aggregateSimpleSumTest() {
    /*
      0 0 0 0     0 0 0 8
      0 0 0 4  >  0 0 0 0
      0 0 0 0     0 0 0 0
      0 0 0 4     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[1][3] = new Spot(4);
    testSpots[3][3] = new Spot(4);

    testGame.moveUp();
    assertEquals(8, testGame.getGameGrid().getGrid()[0][3].getValue());
  }

  @Test()
  public void aggregateOnlyOneSumPerRowTest() {
    /*
      0 0 0 0     0 0 0 0
      0 0 0 0  >  0 0 0 0
      4 4 4 4     8 8 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[2][0] = new Spot(4);
    testSpots[2][1] = new Spot(4);
    testSpots[2][2] = new Spot(4);
    testSpots[2][3] = new Spot(4);

    testGame.moveLeft();
    assertEquals(8, testGame.getGameGrid().getGrid()[2][0].getValue());
    assertEquals(8, testGame.getGameGrid().getGrid()[2][1].getValue());
    assertEquals(0, testGame.getGameGrid().getGrid()[2][2].getValue());
    assertEquals(0, testGame.getGameGrid().getGrid()[2][3].getValue());
  }

  @Test()
  public void aggregateNotSumWithPreviousResultTest() {
    /*
      0 0 0 0     0 0 0 0
      0 0 0 0  >  0 0 0 0
      8 4 4 0     8 8 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[2][0] = new Spot(8);
    testSpots[2][1] = new Spot(4);
    testSpots[2][2] = new Spot(4);
    testSpots[2][3] = new Spot(0);

    testGame.moveLeft();
    assertEquals(8, testGame.getGameGrid().getGrid()[2][0].getValue());
    assertEquals(8, testGame.getGameGrid().getGrid()[2][1].getValue());
    assertEquals(0, testGame.getGameGrid().getGrid()[2][2].getValue());
    assertEquals(0, testGame.getGameGrid().getGrid()[2][3].getValue());
  }

  @Test()
  public void aggregateLeftSideFirstTest() {
    /*
      0 0 0 0     0 0 0 0
      4 4 4 0  >  8 4 0 0
      0 0 0 0     0 0 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[1][0] = new Spot(4);
    testSpots[1][1] = new Spot(4);
    testSpots[1][2] = new Spot(4);

    testGame.moveLeft();
    assertEquals(8, testGame.getGameGrid().getGrid()[1][0].getValue());
    assertEquals(4, testGame.getGameGrid().getGrid()[1][1].getValue());
    assertEquals(0, testGame.getGameGrid().getGrid()[1][2].getValue());
  }

  @Test()
  public void nextTurnWhenMoveIsNotAllowTest() {
    /*
      0 0 0 0     0 0 0 0
      4 0 0 0  >  4 0 0 0
      0 0 0 0     0 0 0 0
      0 0 0 0     0 0 0 0
     */
    Game testGame = new Game();
    testGame.getGameGrid().resetAll(); // remove initial random new value
    Spot[][] testSpots = testGame.getGameGrid().getGrid();
    testSpots[1][0] = new Spot(4);

    testGame.moveLeft(); //move left should not allow a next turn and no new value in grid
    assertEquals(4, testGame.getGameGrid().getGrid()[1][0].getValue());
    assertFalse(testGame.nextTurn());
  }

  @Test()
  public void nextTurnWhenGridIsFullTest() {
    Game testGame = new Game();
    Arrays.stream(testGame.getGameGrid().getGrid())
        .flatMap(Arrays::stream)
        .forEach(spot -> spot.setValue(2));

    assertFalse(testGame.nextTurn());
  }

  @Test()
  public void gameFinishedWhenGridHas2048Test() {
    Game testGame = new Game();
    testGame.getGameGrid().getGrid()[0][0].setValue(2048);
    assertTrue(testGame.isGameFinished());
  }
}
