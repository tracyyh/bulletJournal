package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the Duty class
 */
public class DutyTest {
  private Duty event;
  private Duty task;

  /**
   * sets up the initial condiitons for testing
   */
  @BeforeEach
  public void setup() {
    event = new Event("Test Event", "description",
        Weekday.TUESDAY, new Date(54000000), 50);
    task = new Task("Test Task", "task", Weekday.WEDNESDAY, true);
  }

  /**
   * tests whether the duty has the right weekday
   */
  @Test
  public void testWeekday() {
    assertEquals(Weekday.TUESDAY.toString(), event.weekdayProperty().getValue());
    assertEquals(Weekday.WEDNESDAY.toString(), task.weekdayProperty().getValue());
  }

  /**
   * tests whether the duty has the right description
   */
  @Test
  public void testDescription() {
    assertEquals("description", event.descriptonProperty().getValue());
    assertEquals("task", task.descriptonProperty().getValue());
  }

  /**
   * tests whether the duty has the right name
   */
  @Test
  public void testName() {
    assertEquals("Test Event", event.nameProperty().getValue());
    assertEquals("Test Task", task.nameProperty().getValue());
  }

  /**
   * tests the method convertString through getWeekday
   */
  @Test
  public void testConvertWeekday() {
    Task task1 = new Task("Task1", "task1", Weekday.THURSDAY, true);
    assertEquals(Weekday.THURSDAY, task1.getWeekday());
    Task task2 = new Task("Task1", "task1", Weekday.FRIDAY, true);
    assertEquals(Weekday.FRIDAY, task2.getWeekday());
    Task task3 = new Task("Task1", "task1", Weekday.SATURDAY, true);
    assertEquals(Weekday.SATURDAY, task3.getWeekday());
    Task task4 = new Task("Task1", "task1", Weekday.SUNDAY, true);
    assertEquals(Weekday.SUNDAY, task4.getWeekday());
    assertEquals(Weekday.TUESDAY, event.getWeekday());
    assertEquals(Weekday.WEDNESDAY, task.getWeekday());
  }
}
