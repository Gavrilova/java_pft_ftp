package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 4/14/16.
 */
public class PointTest4 {

  @Test
    public void testPoint(){
      Point p1 = new Point(-1000.9, 0);
      Point p2 = new Point(-10000000, -300000000);
      Assert.assertEquals(p1.distance(p2), 3.00166587052926E8);
    }
}
