package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 4/14/16.
 */
public class PointTest2 {

  @Test
  public void testPoint(){
    Point p1 = new Point(-5, 44);
    Point p2 = new Point(100, -14.99);
    Assert.assertEquals(p1.distance(p2), 120.43595850077335);
  }

}
