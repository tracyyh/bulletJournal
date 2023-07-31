package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * tests the Day class
 */
public class DayTest {
  private static Day day;
  private static Event testEvent;
  private static Task testTask;

  /**
   * sets up the initial conditions for testing
   */
  @BeforeAll
  public static void setup() {
    testEvent = new Event("Test Event", "test event", Weekday.MONDAY,
        new Date(54000000), 50);
    testTask = new Task("Test Task", "test task", Weekday.MONDAY, true);
    day = new Day(Weekday.MONDAY, new ArrayList<>(List.of(testEvent, testTask)));
  }

  /**
   * tests the method getDuties
   */
  @Test
  public void testGetDuties() {
    ArrayList<Duty> testDuties = new ArrayList<>(List.of(testEvent, testTask));
    for (int i = 0; i < testDuties.size(); i++) {
      assertEquals(day.getDuties().get(i), testDuties.get(i));
    }
  }

  /**
   * tests the method completePercent
   */
  @Test
  public void testCompletePercent() {
    assertEquals(0.5, day.completePercent());
  }

  /**
   * tests the getDutiesArray method
   */
  @Test
  public void testGetDutiesArray() {
    for (int i = 0; i < day.getDutiesArray().length; i++) {
      assertEquals(day.getDutiesArray()[i].getName(), day.getDuties().get(i).getName());
      assertEquals(day.getDutiesArray()[i].getWeekday(), day.getDuties().get(i).getWeekday());
    }
  }
}
