package ru.stqa.pft.sandbox;

/**
 * Created by irinagavrilova on 4/12/16.
 */
public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  double dist(double x2, double y2) {

    double x0 = x2 - this.x;
    double y0 = y2 - this.y;
    //System.out.println("x0 = " + x0 + " y0 = " + y0);
    return Math.sqrt(x0 * x0 + y0 * y0);

  }

  double distance(Point p) {
    return dist(p.x, p.y);

  }


}

