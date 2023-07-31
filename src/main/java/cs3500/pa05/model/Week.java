package cs3500.pa05.model;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Week class represents a week in the bullet journal.
 */
public class Week {
  private StringProperty name;
  private ArrayList<Day> days;
  private StringProperty notes = new SimpleStringProperty("");

  /**
   * Constructs a Week object with the specified name and days.
   *
   * @param name the name of the week
   * @param days the list of days in the week
   */
  public Week(String name, ArrayList<Day> days) {
    this.name = new SimpleStringProperty("");
    this.days = days;
  }

  /**
   * Constructs a Week object with the specified name.
   *
   * @param name the name of the week
   */
  public Week(String name) {
    this.name = new SimpleStringProperty(name);
    this.days = new ArrayList<Day>();
  }

  /**
   * Returns the StringProperty representing the name of the week.
   *
   * @return the name property
   */
  public StringProperty nameProperty() {
    return name;
  }


  /**
   * Returns the name of the week.
   *
   * @return the name
   */
  public String getName() {
    return name.getValue();
  }

  /**
   * Returns the StringProperty representing the notes of the week.
   *
   * @return the notes property
   */
  public StringProperty notesProperty() {
    return notes;
  }

  /**
   * Returns the notes of the week.
   *
   * @return the notes
   */
  public String getNotes() {
    return notes.getValue();
  }

  /**
   * Sets the notes of the week.
   *
   * @param in the notes to be set
   */
  public void setNotes(String in) {
    notes = new SimpleStringProperty(in);
  }

  /**
   * Adds a day to the week.
   *
   * @param day the day to be added
   */
  public void addDayToWeek(Day day) {
    days.add(day);
  }

  /**
   * Returns an array of all the days in the week.
   *
   * @return an array of days
   */
  public Day[] getDays() {
    Day[] resultArray = new Day[days.size()];
    for (int i = 0; i < days.size(); i++) {
      resultArray[i] = days.get(i);
    }
    return resultArray;
  }

  /**
   * Returns the number of tasks on the specified weekday.
   *
   * @param taskDay the weekday to check
   * @return the number of tasks
   */
  public int getDayTasks(Weekday taskDay) {
    int counter = 0;
    Day checkDay = days.get(taskDay.ordinal());
    for (Duty d : checkDay.getDuties()) {
      if (d instanceof Task) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Returns the number of events on the specified weekday.
   *
   * @param eventDay the weekday to check
   * @return the number of events
   */
  public int getDayEvents(Weekday eventDay) {
    int counter = 0;
    Day checkDay = days.get(eventDay.ordinal());
    for (Duty d : checkDay.getDuties()) {
      if (d instanceof Event) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Clears all the duties from the week.
   */
  public void clearDuties() {
    for (int i = 0; i < days.size(); i++) {
      for (int s = 0; s < days.get(i).getDuties().size(); s++) {
        days.get(i).getDuties().clear();
      }
    }
  }

  /**
   * Returns an ArrayList of all the duties in the week.
   *
   * @return an ArrayList of duties
   */
  public ArrayList<Duty> getDuties() {
    ArrayList<Duty> duties = new ArrayList<>();
    for (int i = 0; i < days.size(); i++) {
      duties.addAll(days.get(i).getDuties());
    }
    return duties;
  }

  /**
   * Adds a duty to the specified day in the week.
   *
   * @param duty the duty to be added
   */
  public void addDutyToDay(Duty duty) {
    int index = getWeekdayNum(duty);
    days.get(index).addDuty(duty);
  }

  /**
   * Sets the title of the week.
   *
   * @param newTitle the new title
   */
  public void setTitle(String newTitle) {
    name = new SimpleStringProperty(newTitle);
  }

  /**
   * Returns the weekday number of the specified duty.
   *
   * @param duty the duty to check
   * @return the weekday number
   */
  public int getWeekdayNum(Duty duty) {
    switch (duty.getWeekday()) {
      case TUESDAY -> {
        return 1;
      }
      case WEDNESDAY -> {
        return 2;
      }
      case THURSDAY -> {
        return 3;
      }
      case FRIDAY -> {
        return 4;
      }
      case SATURDAY -> {
        return 5;
      }
      case SUNDAY -> {
        return 6;
      }
      default -> {
        return 0;
      }
    }
  }

  /**
   * Returns the completion percentage of the day where the specified duty is located.
   *
   * @param duty the duty to check
   * @return the completion percentage
   */
  public double getDayPercent(Duty duty) {
    return days.get(getWeekdayNum(duty)).completePercent();
  }
}
