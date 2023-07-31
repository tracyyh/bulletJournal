package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the Task test
 */
public class TaskTest {
  private Task task;
  private Task task2;

  /**
   * sets up the initial conditions for testing
   */
  @BeforeEach
  public void setup() {
    task = new Task();
    task2 = new Task("Test Task", "Desc", Weekday.MONDAY, true);
  }

  /**
   * tests the method completedProperty
   */
  @Test
  public void testCompletedProperty() {
    assertEquals("true", task2.completedProperty().getValue());
  }

  /**
   * tests the method getCompleted
   */
  @Test
  public void testGetCompleted() {
    assertEquals(true, task2.getCompleted());
  }

  /**
   * tests the method setCompleted
   */
  @Test
  public void testSetCompleted() {
    assertEquals("true", task2.completedProperty().getValue());
    task2.setCompleted(new SimpleStringProperty("false"));
    assertEquals("false", task2.completedProperty().getValue());
  }

  /**
   * tests the method getEvent
   */
  @Test
  public void testGetEvent() {
    assertNull(task2.getEvent());
  }

  /**
   * tests the method getTask
   */
  @Test
  public void testGetTask() {
    assertEquals(task2, task2.getTask());
  }
}
