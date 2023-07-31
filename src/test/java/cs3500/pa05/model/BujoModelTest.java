package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the BujoModel class
 */
public class BujoModelTest {
  private BujoModel model;
  private Week week;
  private static ArrayList<Day> days = new ArrayList<>();

  /**
   * sets up the initial conditions for testing
   */
  @BeforeEach
  public void setup() {
    days.addAll(List.of(
        new Day(Weekday.MONDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.MONDAY, new Date(54000000), 60)))),
        new Day(Weekday.TUESDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.TUESDAY, new Date(54000000), 60)))),
        new Day(Weekday.WEDNESDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.WEDNESDAY, new Date(54000000), 60)))),
        new Day(Weekday.THURSDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.THURSDAY, new Date(54000000), 60)))),
        new Day(Weekday.FRIDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.FRIDAY, new Date(54000000), 60)))),
        new Day(Weekday.SATURDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.SATURDAY, new Date(54000000), 60)))),
        new Day(Weekday.SUNDAY, new ArrayList<>(List.of(
            new Task("Test Task", "This is a test task",
                Weekday.SUNDAY, true))))));
    week = new Week("Test Week", days);
    model = new BujoModel(week);
  }

  /**
   * tests the method setMaxEvents
   */
  @Test
  public void testMaxEvents() {
    assertEquals(99999, model.getMaxEvents());
    model.setMaxEvents(10);
    assertEquals(10, model.getMaxEvents());
  }

  /**
   * tests the method setMaxTasks
   */
  @Test
  public void testMaxTasks() {
    assertEquals(99999, model.getMaxTasks());
    model.setMaxTasks(10);
    assertEquals(10, model.getMaxTasks());
  }

  /**
   * tests the method addDuty
   */
  @Test
  public void testAddDuty() {
    Task task1 = new Task("Task1", "task1", Weekday.SUNDAY, true);
    assertEquals(7, model.getWeek().getDuties().size());
    model.addDuty(task1);
    assertEquals(8, model.getWeek().getDuties().size());
  }

  /**
   * tests the method saveFileHelper
   */
  @Test
  public void testSaveFileHelper() {
    try {
      File temp = File.createTempFile("Test", ".txt");
      Scanner scanner = new Scanner(temp);
      assertFalse(scanner.hasNext());
      model.saveFileHelper(temp, "content");
      Scanner newScanner = new Scanner(temp);
      assertTrue(newScanner.hasNext());;
    } catch (IOException e) {
      System.err.println("Error testing save file helper");
    }
  }
}
