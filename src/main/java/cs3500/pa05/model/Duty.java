package cs3500.pa05.model;

import cs3500.pa05.view.AlertUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * An abstract class representing a duty.
 */
public abstract class Duty {

  private StringProperty name;
  private StringProperty description;
  private StringProperty weekday;


  /**
   * Constructs a duty with the specified name, description, and weekday.
   *
   * @param name        the name of the duty
   * @param desc        the description of the duty
   * @param day         the weekday of the duty
   */
  public Duty(String name, String desc, Weekday day) {
    this.name = new SimpleStringProperty(name);
    this.description = new SimpleStringProperty(desc);
    this.weekday = new SimpleStringProperty(day.toString());
  }

  /**
   * Constructs an empty duty.
   */
  public Duty() {
    this.name = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    this.weekday = new SimpleStringProperty("");
  }

  /**
   * Returns the property representing the weekday of the duty.
   *
   * @return the property representing the weekday
   */
  public StringProperty weekdayProperty() {

    return weekday;
  }

  /**
   * Returns the property representing the description of the duty.
   *
   * @return the property representing the description
   */
  public StringProperty descriptonProperty() {

    return description;
  }

  /**
   * Returns the name of the duty.
   *
   * @return the name of the duty
   */
  public String getName() {
    return name.getValue();
  }

  /**
   * Returns the description of the duty.
   *
   * @return the description of the duty
   */
  public String getDescription() {
    return description.getValue();
  }

  /**
   * Converts the string representation of a weekday to the corresponding Weekday enum value.
   *
   * @param enumAsString the string representation of a weekday
   * @return the Weekday enum value
   */
  private Weekday convertString(String enumAsString) {
    Weekday day = null;
    if (enumAsString.equalsIgnoreCase(Weekday.MONDAY.toString())) {
      day = Weekday.MONDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.TUESDAY.toString())) {
      day = Weekday.TUESDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.WEDNESDAY.toString())) {
      day = Weekday.WEDNESDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.THURSDAY.toString())) {
      day = Weekday.THURSDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.FRIDAY.toString())) {
      day = Weekday.FRIDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.SATURDAY.toString())) {
      day = Weekday.SATURDAY;
    } else if (enumAsString.equalsIgnoreCase(Weekday.SUNDAY.toString())) {
      day = Weekday.SUNDAY;
    } else {
      AlertUtils.showNewWarning("This is not a valid day of the week.");
    }
    return day;
  }

  /**
   * Returns the weekday of the duty.
   *
   * @return the weekday of the duty
   */
  public Weekday getWeekday() {
    return convertString(weekday.getValue());
  }

  /**
   * Returns the property representing the name of the duty.
   *
   * @return the property representing the name
   */
  public StringProperty nameProperty() {
    return name;
  }

  /**
   * Returns the associated event, or null if the duty is not an event.
   *
   * @return the associated event, or null
   */
  public abstract Event getEvent();

  /**
   * Returns the associated task, or null if the duty is not a task.
   *
   * @return the associated task, or null
   */
  public abstract Task getTask();

}
