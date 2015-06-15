package com.aerlingus.checkin.ws.validate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.aerlingus.checkin.ws.domain.CheckInInfo;
import com.aerlingus.checkin.ws.domain.CheckinDetailsRS;
import com.aerlingus.checkin.ws.domain.FlightInfo;

public class CheckinDetailsValidatorTest {
  
  private static CheckinDetailsValidator validator;
  
  private ArrayList<FlightInfo> flightInfos;
  
  @BeforeClass
  public static void setupClass() {
    CheckInInfo checkInInfo = new CheckInInfo();
    
    CheckinDetailsRS checkinDetailsRS = new CheckinDetailsRS();
    checkinDetailsRS.setCheckInInfo(checkInInfo);

    validator = new CheckinDetailsValidator();
    validator.setCheckinDetailsRS(checkinDetailsRS);
  }
  
  @Before
  public void setup() {
    validator.getCheckinDetailsRS().getCheckInInfo().setFlightInfo(new ArrayList<FlightInfo>());
  }
  
  @Test
  public void isFlownFlight_OneSegmentFlown_shouldReturnTrue() throws Exception {
    FlightInfo info1 = new FlightInfo();
    info1.setFlightStatus("FLOWN");
    flightInfos.add(info1);
    
    boolean actual = (Boolean)Whitebox.invokeMethod(validator, "isFlownFlight");
    assertThat(actual, is(true));
  }
  
  @Test
  public void isFlownFlight_OneSegmentClosed_shouldReturnFalse() throws Exception {
    FlightInfo info1 = new FlightInfo();
    info1.setFlightStatus("CLOSED");
    flightInfos.add(info1);
    
    boolean actual = (Boolean)Whitebox.invokeMethod(validator, "isFlownFlight");
    assertThat(actual, is(false));
  }
  
  @Test
  public void isFlownFlight_ClosedAndFlown_shouldReturnTrue() throws Exception {
    FlightInfo info1 = new FlightInfo();
    info1.setFlightStatus("CLOSED");
    flightInfos.add(info1);
    
    FlightInfo info2 = new FlightInfo();
    info2.setFlightStatus("FLOWN");
    flightInfos.add(info2);
    
    boolean actual = (Boolean)Whitebox.invokeMethod(validator, "isFlownFlight");
    assertThat(actual, is(true));
  }
  
  @Test
  public void isFlownFlight_FlownAndFlown_shouldReturnTrue() throws Exception {
    FlightInfo info1 = new FlightInfo();
    info1.setFlightStatus("FLOWN");
    flightInfos.add(info1);
    
    FlightInfo info2 = new FlightInfo();
    info2.setFlightStatus("FLOWN");
    flightInfos.add(info2);
    
    boolean actual = (Boolean)Whitebox.invokeMethod(validator, "isFlownFlight");
    assertThat(actual, is(true));
  }
  
  @Test
  public void isFlownFlight_ClosedAndClosed_shouldReturnTrue() throws Exception {
    FlightInfo info1 = new FlightInfo();
    info1.setFlightStatus("FLOWN");
    flightInfos.add(info1);
    
    FlightInfo info2 = new FlightInfo();
    info2.setFlightStatus("FLOWN");
    flightInfos.add(info2);
    
    boolean actual = (Boolean)Whitebox.invokeMethod(validator, "isFlownFlight");
    assertThat(actual, is(true));
  }
  
}
