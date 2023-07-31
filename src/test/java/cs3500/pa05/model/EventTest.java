package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the Event class
 */
public class EventTest {
  private Event event;
  private Event event2;

  /**
   * sets up the initial conditions for testing
   */
  @BeforeEach
  public void setup() {
    event = new Event("Test Event", "Desc", Weekday.MONDAY, new Date(54000000),
        50);
    event2 = new Event();
  }

  /**
   * tests whether the event has the right time
   */
  @Test
  public void testStartTimeProperty() {
    assertEquals("Thu Jan 01 10:00:00 EST 1970", event.startTimeProperty().getValue());
  }

  /**
   * tests whether the event has the right duration
   */
  @Test
  public void testDurationMinutesProperty() {
    assertEquals("50", event.durationMinutesProperty().getValue());
  }

  /**
   * tests the convertFieldToDate method
   */
  @Test
  public void testConvertFieldToDate() {
    assertEquals("Thu Jan 01 10:00:00 EST 1970",
        Event.convertFieldToDate("10:00:00").toString());
  }

  /**
   * tests the getStartTime method
   */
  @Test
  public void testGetStartTime() {
    assertEquals(new Date(54000000), event.getStartTime());
  }

  /**
   * tests the getDuration method
   */
  @Test
  public void testGetDuration() {
    assertEquals(50, event.getDuration());
  }

  /**
   * tests the getEvent method
   */
  @Test
  public void testGetEvent() {
    assertEquals(event, event.getEvent());
  }

  /**
   * tests the getTask method
   */
  @Test
  public void testGetTask() {
    assertNull(event.getTask());
  }
}
