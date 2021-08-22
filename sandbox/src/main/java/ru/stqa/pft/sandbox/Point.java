package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point other) {
    double a = this.x - other.x;
    double b = this.y - other.y;
    return Math.sqrt(a * a + b * b);
  }
}