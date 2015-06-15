package com.aerlingus.obe.business.utils;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.aerlingus.obe.domain.reservation.air.traveler.EIAirTraveler;
import com.aerlingus.obe.domain.reservation.air.traveler.reqdetails.EISSRCodesEnum;
import com.aerlingus.obe.domain.reservation.air.traveler.reqdetails.EISpecialServiceRequest;

@RunWith(EasyMockRunner.class)
public class EIInfantExtractorTest {
  @Mock(type = MockType.NICE)
  private EISpecialServiceRequest stubSpecialServiceRequest;

  @TestSubject
  private EIInfantExtractor testedInfantExtractor = new EIInfantExtractor();
  
  
  @Test
  public void buildInfantInfo_ifNull_shouldReturnNull() {
    EIAirTraveler actual = new EIInfantExtractor(null).buildInfantInfo();
    assertThat(actual, is(nullValue()));
  }
  
  @Test
  public void buildInfantInfo_ifEmptyObject_shouldReturnNull() {
    replay(stubSpecialServiceRequest);
    
    EIAirTraveler actual = testedInfantExtractor.buildInfantInfo();
    assertThat(actual, is(nullValue()));
  }  
  
  @Test
  public void buildInfantInfo_ifNoType_shouldReturnNull() {
    expect(stubSpecialServiceRequest.getText()).andStubReturn("test_string_1");
    replay(stubSpecialServiceRequest);
    
    EIAirTraveler actual = testedInfantExtractor.buildInfantInfo();
    assertThat(actual, is(nullValue()));
  }
  
  @Test
  public void buildInfantInfo_ifNoText_shouldReturnNull() {
    expect(stubSpecialServiceRequest.getType()).andStubReturn(EISSRCodesEnum.INFANT);
    replay(stubSpecialServiceRequest);
    
    EIAirTraveler actual = testedInfantExtractor.buildInfantInfo();
    assertThat(actual, is(nullValue()));
  }  
  
  @Test
  public void buildInfantInfo_ifBlankText_shouldReturnNull() {
    expect(stubSpecialServiceRequest.getType()).andStubReturn(EISSRCodesEnum.INFANT);
    expect(stubSpecialServiceRequest.getText()).andStubReturn("   ");
    replay(stubSpecialServiceRequest);
    
    EIAirTraveler actual = testedInfantExtractor.buildInfantInfo();
    assertThat(actual, is(nullValue()));
  }  
  
  /*
   * Testing  "find" method
   */
  
  @Test
  public void find_ifStringNull_shouldReturnNull() {
    EIInfantExtractor.find(null, "", 1);
  }
  
  @Test
  public void find_ifPatternNull_shouldReturnNull() {
    EIInfantExtractor.find("test_string_1", null, 1);
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void find_ifNegativeGroup_shouldThrowException() {
    String actual = EIInfantExtractor.find("test_string_1", "", -1);
    assertThat(actual, instanceOf(String.class));
  }  

  @Test(expected=IndexOutOfBoundsException.class)
  public void find_ifNotExistingGroup_shouldThrowException() {
    String actual = EIInfantExtractor.find("test_string_1", "", 100);
    assertThat(actual, instanceOf(String.class));
  }
  
  @Test
  public void find_ifAllInRange_shouldReturnSomeString() {
    String actual = EIInfantExtractor.find("test_string_1", "", 0);
    assertThat(actual, instanceOf(String.class));
  }  
  
  @Test
  public void find_ifMachString_shouldReturnThatString() {
    String actual = EIInfantExtractor.find("test_string_1", "test", 0);
    assertThat(actual, is("test"));
  }  
  
}
