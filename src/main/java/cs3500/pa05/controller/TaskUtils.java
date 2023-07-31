package cs3500.pa05.controller;

import cs3500.pa05.model.Task;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Utility class for handling Task-related operations in a JavaFX application.
 */
public class TaskUtils {
  private CheckBox checkBox = new CheckBox();
  private CheckBox checkBox2 = new CheckBox();
  private HBox hbox = new HBox();
  private Task task;

  /**
   * Constructs a TaskUtils object with the specified Task.
   *
   * @param tasks the Task object
   */
  TaskUtils(Task tasks) {
    setupCheckBox();
    task = tasks;
  }

  /**
   * Sets up the CheckBox objects with their properties and styles.
   */
  public void setupCheckBox() {
    Insets margins = new Insets(0, 7.0, 0, 15.0);
    checkBox.setPadding(margins);
    checkBox.setPrefSize(20.0, 20.0);
    checkBox2.setPadding(margins);
    checkBox2.setPrefSize(20.0, 20.0);
  }


  /**
   * Returns the CheckBox associated with the Task.
   *
   * @return the CheckBox for the Task
   */
  public CheckBox getTaskCheckBox() {
    return checkBox;
  }

  /**
   * Returns the CheckBox associated with the Task queue.
   *
   * @return the CheckBox for the Task queue
   */
  public CheckBox getQueueCheckBox() {
    return checkBox2;
  }

  /**
   * Initializes the CheckBox states based on the Task's completion status.
   */
  public void initCheckBox() {
    if (task.getCompleted()) {
      checkBox.setSelected(true);
      checkBox2.setSelected(true);
    } else {
      checkBox.setSelected(false);
      checkBox2.setSelected(false);
    }
  }

  /**
   * Sets up the HBox containing the CheckBox and the associated Task's name.
   *
   * @param purpose the purpose of the setup ("task" or "queue")
   * @return the configured HBox
   */
  public HBox setupBlock(String purpose) {
    CheckBox temp;
    if (purpose.equals("task")) {
      temp = checkBox;
    } else {
      temp = checkBox2;
    }
    hbox = new HBox();
    Font font = new Font("Courier New", 18.0);
    checkBox.setFont(font);
    checkBox.setWrapText(true);
    checkBox.setText(task.getName());
    checkBox2.setFont(font);
    checkBox2.setWrapText(true);
    checkBox2.setText(task.getName());
    hbox.getChildren().add(temp);
    hbox.setVisible(true);
    return hbox;
  }

  /**
   * Sets the text label for the CheckBox.
   *
   * @param text the text to set as the label
   */
  public void setLabelText(String text) {
    checkBox.setText(text);
    checkBox2.setText(text);
  }

  /**
   * Handles the change of CheckBox selection and updates the Task's completion status accordingly.
   */
  public void changeSelected() {
    if (checkBox.isSelected() && !task.getCompleted()) {
      task.setCompleted(new SimpleStringProperty("true"));
      checkBox2.setSelected(true);
    } else if (checkBox2.isSelected() && !task.getCompleted()) {
      task.setCompleted(new SimpleStringProperty("true"));
      checkBox.setSelected(true);
    } else {
      task.setCompleted(new SimpleStringProperty("false"));
      checkBox.setSelected(false);
      checkBox2.setSelected(false);
    }
  }
}
