package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the Week class
 */
public class WeekTest {
  private Week week;
  private Week week1;
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
    week1 = new Week("This is the second constructor");
  }

  /**
   * tests whether the week has the correct name
   */
  @Test
  public void testName() {
    assertEquals("", week.nameProperty().getValue());
    assertEquals("This is the second constructor", week1.nameProperty().getValue());
  }

  /**
   * tests the method setNotes
   */
  @Test
  public void testNotes() {
    assertEquals("", week.getNotes());
    week.setNotes("Test notes");
    assertEquals("Test notes", week.getNotes());
  }

  /**
   * tests the method addDayToWeek
   */
  @Test
  public void testAddDayToWeek() {
    assertEquals(0, week1.getDays().length);
    week1.addDayToWeek(new Day(Weekday.MONDAY, new ArrayList<>(List.of(
        new Event("Test Event", "This is a test event",
            Weekday.MONDAY, new Date(54000000), 60)))));
  }

  /**
   * tests the getDayTasks method
   */
  @Test
  public void testGetDayTasks() {
    assertEquals(1, week.getDayTasks(Weekday.SUNDAY));
    assertEquals(0, week.getDayTasks(Weekday.MONDAY));
  }

  /**
   * tests the getDayEvents method
   */
  @Test
  public void testGetDayEvents() {
    assertEquals(1, week.getDayEvents(Weekday.MONDAY));
    assertEquals(0, week.getDayEvents(Weekday.SUNDAY));
  }

  /**
   * tests the clearDuties method
   */
  @Test
  public void testClearDuties() {
    assertEquals(56, week.getDuties().size());
    week.clearDuties();
    assertEquals(0, week.getDuties().size());
  }

  /**
   * tests the method addDutyToDay
   */
  @Test
  public void testAddDutyToDay() {
    assertEquals(0, week.getDays()[0].getDuties().size());
    week.addDutyToDay(new Task("Test Adding Task", "adding task", Weekday.MONDAY, false));
    assertEquals(1, week.getDays()[0].getDuties().size());
  }

  /**
   * tests the method setTitle
   */
  @Test
  public void testSetTitle() {
    assertEquals("This is the second constructor", week1.getName());
    week1.setTitle("New title");
    assertEquals("New title", week1.getName());
  }

  /**
   * tests the method setNotes
   */
  @Test
  public void testNotesProperty() {
    assertEquals("", week1.notesProperty().getValue());
    week1.setNotes("New Notes");
    assertEquals("New Notes", week1.notesProperty().getValue());
  }

  /**
   * tests the method getWeekdayNum
   */
  @Test
  public void testWeekdayNum() {
    Task task1 = new Task("Task1", "task1", Weekday.TUESDAY, true);
    assertEquals(1, week.getWeekdayNum(task1));
    Task task2 = new Task("Task1", "task1", Weekday.WEDNESDAY, true);
    assertEquals(2, week.getWeekdayNum(task2));
    Task task3 = new Task("Task1", "task1", Weekday.THURSDAY, true);
    assertEquals(3, week.getWeekdayNum(task3));
    Task task4 = new Task("Task1", "task1", Weekday.FRIDAY, true);
    assertEquals(4, week.getWeekdayNum(task4));
    Task task5 = new Task("Task1", "task1", Weekday.SATURDAY, true);
    assertEquals(5, week.getWeekdayNum(task5));
    Task task6 = new Task("Task1", "task1", Weekday.SUNDAY, true);
    assertEquals(6, week.getWeekdayNum(task6));
  }

  /**
   * tests the method getDayPercent
   */
  @Test
  public void testDayPercent() {
    Task task1 = new Task("Task1", "task1", Weekday.SUNDAY, true);
    assertEquals(1.0, week.getDayPercent(task1));
  }

}
