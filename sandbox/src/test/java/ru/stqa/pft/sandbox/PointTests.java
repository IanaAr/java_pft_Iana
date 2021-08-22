package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistanceZeroResult() {
    Point p = new Point(1, 1);
    Point p1 = new Point(1, 1);
    Assert.assertEquals(p.distance(p1), 0.0);
  }

  @Test
  public void testDistanceOneXNegativeNum() {
    Point p = new Point(1, 1);
    Point p1 = new Point(-2, 2);
    Assert.assertEquals(p.distance(p1), 3.1622776601683795);
  }

  @Test
  public void testDistanceOneYNegativeNum() {
    Point p = new Point(1, -1);
    Point p1 = new Point(2, 2);
    Assert.assertEquals(p.distance(p1), 3.1622776601683795);
  }


  @Test
  public void testDistanceTwoNegativeNum() {
    Point p = new Point(-1, -1);
    Point p1 = new Point(2, 2);
    Assert.assertEquals(p.distance(p1), 4.242640687119285);
  }

  @Test
  public void testDistanceAllNegativeNum() {
    Point p = new Point(-1, -1);
    Point p1 = new Point(-2, -2);
    Assert.assertEquals(p.distance(p1), 1.4142135623730951);
  }

  @Test
  public void testDistanceAllPositiveNum() {
    Point p = new Point(1, 2);
    Point p1 = new Point(3, 4);
    Assert.assertEquals(p.distance(p1), 2.8284271247461903);
  }
}
