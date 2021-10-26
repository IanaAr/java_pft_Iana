package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
  @Test
  public void testMyIp ()
  {
  GeoIp geoIP =  new GeoIpService().getGeoIpServiceSoap12().getGeoIp ("93.92.200.162");
    Assert.assertEquals(geoIp.getCountryCode(), "RUS");
  }
}
