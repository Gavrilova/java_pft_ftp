package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by irinagavrilova on 4/14/16.
 */
public class PointTest1 {

  @Test
  public void testPoint(){
    Point p1 = new Point(3, 4);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 5.0);

  }
}
