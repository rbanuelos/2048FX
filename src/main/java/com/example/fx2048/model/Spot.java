package com.example.fx2048.model;

/**
 * Spot class represents a position in Game grid. It holds a numeric value (2, 4, 8, ...) and a
 * boolean flag to check if the Spot was changed already on any given turn
 */
public class Spot {
  private int value;
  private boolean hasChanged;

  public Spot() {
    this.value = 0;
    this.hasChanged = false;
  }

  public Spot(int value) {
    this.value = value;
    this.hasChanged = false;
  }

  public boolean hasChanged() {
    return hasChanged;
  }

  public void setHasChanged(boolean hasChanged) {
    this.hasChanged = hasChanged;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean isEmpty() {
    return this.value == 0;
  }
}
