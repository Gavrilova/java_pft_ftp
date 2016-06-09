package ru.stqa.pft.sandbox;

/**
 * Created by irinagavrilova on 4/12/16.
 */
public class HomeWork2 {

  public static void main(String[] args) {

    Point p1 = new Point(-1, -1);
    Point p2 = new Point(2, 3);

    System.out.println("First  point: " + "(" + p1.x + ", " + p1.y + ")");
    System.out.println("Second point: " + "(" + p2.x + ", " + p2.y + ")");
    System.out.println("Distance between points = " + p1.distance(p2));
  }

}
