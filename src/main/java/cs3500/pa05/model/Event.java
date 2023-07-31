package cs3500.pa05.model;

import cs3500.pa05.view.AlertUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents an event in a bullet journal.
 */
public class Event extends Duty {
  private StringProperty startTime;
  private StringProperty durationMinutes;

  /**
   * constructor
   *
   * @param name of event
   * @param description of event
   * @param weekday event is occurring on
   * @param startTime of event
   * @param duration of event
   */
  public Event(String name, String description, Weekday weekday, Date startTime, int duration) {
    super(name, description, weekday);
    this.startTime = new SimpleStringProperty(startTime.toString());
    this.durationMinutes = new SimpleStringProperty(String.valueOf(duration));
  }

  /**
   * constructor
   */
  public Event() {
    this.startTime = new SimpleStringProperty("");
    this.durationMinutes = new SimpleStringProperty("");
  }

  /**
   * Returns the start time property of the event.
   *
   * @return the start time property
   */
  public StringProperty startTimeProperty() {
    return startTime;
  }

  /**
   * Returns the duration minutes property of the event.
   *
   * @return the duration minutes property
   */
  public StringProperty durationMinutesProperty() {
    return durationMinutes;
  }

  /**
   * Converts a string representation of the start time to a Date object.
   *
   * @param startAsString the string representation of the start time
   * @return the converted Date object
   */
  public static Date convertFieldToDate(String startAsString) {
    Date startingTime = null;
    try {
      if (startAsString.contains("Thu")) {
        startAsString = startAsString.substring(startAsString.indexOf("01") + 2,
            startAsString.indexOf("EST"));
      }
      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
      startingTime = timeFormat.parse(startAsString);
    } catch (ParseException e) {
      AlertUtils.showNewWarning("Start time is not in the correct format. Make sure to enter in"
          + " military time HH:mm:ss. (EX 18:22:06).");
    }
    return startingTime;
  }

  /**
   * Returns the start time of the event
   *
   * @return the start time
   */
  public Date getStartTime() {
    return convertFieldToDate(startTime.getValue());
  }

  /**
   * Returns the duration of the event.
   *
   * @return the duration
   */
  public int getDuration() {
    return Integer.parseInt(durationMinutes.getValue());
  }

  /** Returns the event
   *
   * @return this event
   */
  @Override
  public Event getEvent() {
    return this;
  }

  /** returns the associated task
   *
   * @return null because there is no associated task
   */
  @Override
  public Task getTask() {
    return null;
  }
}
