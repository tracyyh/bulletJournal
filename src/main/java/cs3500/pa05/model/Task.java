package cs3500.pa05.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Task class represents a task in the bullet journal.
 */
public class Task extends Duty {

  private StringProperty completed;

  /**
   * Constructs a Task object with the specified name, description, weekday, and completion status.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param weekday     the weekday of the task
   * @param compl       the completion status of the task
   */
  public Task(String name, String description, Weekday weekday, Boolean compl) {
    super(name, description, weekday);
    this.completed = new SimpleStringProperty(String.valueOf(compl));
  }

  /**
   * Constructs a Task object with default values.
   */
  public Task() {
    this.completed = new SimpleStringProperty("");
  }

  /**
   * Returns the StringProperty representing the completion status of the task.
   *
   * @return the completed property
   */
  public StringProperty completedProperty() {
    return completed;
  }

  /**
   * Returns the completion status of the task.
   *
   * @return the completion status
   */
  public boolean getCompleted() {
    return Boolean.parseBoolean(completed.getValue());
  }

  /**
   * Sets the completion status of the task.
   *
   * @param prop the completion status to be set
   */
  public void setCompleted(StringProperty prop) {
    completed = prop;
  }

  /**
   * Returns the event associated with the task.
   * Since tasks do not have an associated event, this method returns null.
   *
   * @return null, as tasks do not have an associated event
   */
  @Override
  public Event getEvent() {
    return null;
  }

  /**
   * Returns the task itself.
   *
   * @return the task itself
   */
  @Override
  public Task getTask() {
    return this;
  }

}
