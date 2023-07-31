package cs3500.pa05.view;

import cs3500.pa05.model.Duty;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The DutyDialog class represents a dialog box for creating or editing a Duty
 * in a Bullet Journal application. It extends the javafx.scene.control.Dialog
 * class and provides fields for capturing the details of a Duty, such as name,
 * description, weekday, start time, duration, and completion status.
 */
public class DutyDialog extends Dialog<Duty> {
  private Stage currentStage;
  private Duty newDuty;
  private TextField name;
  private TextField description;
  private TextField weekday;
  private TextField startTime;
  private TextField duration;
  private TextField completed;
  private ArrayList<TextField> fields;
  private DutyEnum dutyEnum;
  private VBox content;

  /**
   * Constructs a DutyDialog object with the specified DutyEnum and Duty.
   *
   * @param dutyEnum the DutyEnum representing the type of duty.
   * @param duty the Duty object to be edited or created.
   */
  public DutyDialog(DutyEnum dutyEnum, Duty duty) {
    super();
    newDuty = duty;
    fields = new ArrayList<>();
    dutyEnum = dutyEnum;
    currentStage = new Stage();
    initDutyUiHelp(dutyEnum);
    bindCommonFields();
    initButtons();
    currentStage.setScene(new Scene(content, 500, 500));
  }

  /**
   * Initializes the UI components for capturing common duty fields: name, description, and weekday.
   *
   * @param dutyEnum the DutyEnum representing the type of duty.
   */
  private void initDutyUiHelp(DutyEnum dutyEnum) {
    name = new TextField();
    weekday = new TextField();
    content = new VBox();
    description = new TextField();
    Label nameLabel = new Label("Name:");
    content.getChildren().addAll(nameLabel, name, new Label("Weekday:"),
        weekday, new Label("Description:"), new TextField());
    
    if (dutyEnum.equals(DutyEnum.EVENT)) {
      initEventUi();
      bindEventFields();
    } else {
      initTaskUi();
      bindTaskFields();
    }
    content.setPadding(new Insets(10));
    fields.add(name);
    fields.add(weekday);
  }

  /**
   * Binds the common fields (name, description, weekday) to the corresponding
   * properties of the duty object.
   */
  private void bindCommonFields() {
    name.textProperty().bindBidirectional(newDuty.nameProperty());
    description.textProperty().bindBidirectional(newDuty.descriptonProperty());
    weekday.textProperty().bindBidirectional(newDuty.weekdayProperty());
  }

  /**
   * Initializes the UI components for capturing task-specific fields: completion status.
   */
  private void initTaskUi() {
    Label completeLabel = new Label("Completion Status: (enter true or false)");
    completed = new TextField();
    content.getChildren().addAll(completeLabel, completed);
    fields.add(completed);
  }

  /**
   * Binds the task-specific field (completion status) to the corresponding property
   * of the task object.
  */
  private void bindTaskFields() {
    completed.textProperty().bindBidirectional(newDuty.getTask().completedProperty());
  }

  /**
   * Binds the event-specific fields (start time, duration) to the corresponding properties
   * of the event object.
   */
  private void bindEventFields() {
    startTime.textProperty().bindBidirectional(newDuty.getEvent().startTimeProperty());
    duration.textProperty().bindBidirectional(newDuty.getEvent().durationMinutesProperty());
  }

  /**
   * Initializes the UI components for capturing event-specific fields: start time and duration.
   */
  private void initEventUi() {
    Label startLabel = new Label("Start Time (military time format - hh:mm:ss):");
    startTime = new TextField();
    Label durLabel = new Label("Duration (in full minutes):");
    duration = new TextField();
    content.getChildren().addAll(startLabel, startTime, durLabel, duration);
    fields.add(startTime);
    fields.add(duration);
  }

  /**
   * Initializes the OK and Cancel buttons and their actions.
   */
  private void initButtons() {
    getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    Button ok = (Button) getDialogPane().lookupButton(ButtonType.OK);
    Button cancel = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
    content.getChildren().addAll(ok, cancel);
    ok.setOnAction(event -> checkForEmptyFields());
    cancel.setOnAction(event -> currentStage.close());
  }

  /**
   * Checks if any of the fields are empty. If any field is empty, displays a warning.
   * Otherwise, closes the dialog.
   */
  private void checkForEmptyFields() {
    boolean anyNull = false;
    for (TextField textField : fields) {
      if (textField.getText().equals("")) {
        anyNull = true;
      }
    }

    if (anyNull) {
      AlertUtils.showNewWarning("Please fill out all information");
    } else {
      currentStage.close();
    }
  }

  /**
   * Returns the current stage of the DutyDialog.
   *
   * @return the current stage of the DutyDialog.
   */
  public Stage getCurrentStage() {
    return currentStage;
  }
}
