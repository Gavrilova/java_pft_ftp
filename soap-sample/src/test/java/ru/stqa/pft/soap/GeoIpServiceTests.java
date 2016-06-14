package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * Created by irinagavrilova on 6/13/2016.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }

  @Test
  public void testAustinIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("24.55.36.14");
    assertEquals(geoIP.getCountryCode(), "USA");
  }
  @Test
  public void testInvalidIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("24.55.36.1x");
    assertEquals(geoIP.getCountryCode(), "USA");
  }
}
