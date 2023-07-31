package cs3500.pa05.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The ColorDialog class represents a dialog box for selecting colors for different
 * components in a user interface. It extends the javafx.scene.control.Dialog
 * class and provides color pickers for selecting background color, box color, and
 * progress bar color.
 */
public class ColorDialog extends Dialog<Color> {
  private ColorPicker backgroundColor = new ColorPicker();
  private ColorPicker boxColor = new ColorPicker();
  private ColorPicker barColor = new ColorPicker();
  private Stage stage;
  private VBox specifics = new VBox();

  /**
   * Constructs a ColorDialog object.
   */
  public ColorDialog() {
    stage = new Stage();
    addSpecifics();
    initButtons();
    stage.setScene(new Scene(specifics, 300, 120));
  }

  /**
   * Adds the specific components (color pickers) to the dialog box.
   */
  public void addSpecifics() {
    HBox firstLine = new HBox();
    Label back = new Label("Change background:      ");
    firstLine.getChildren().add(back);
    firstLine.getChildren().add(backgroundColor);

    HBox secLine = new HBox();
    Label boxes = new Label("Change boxes:                  ");
    secLine.getChildren().add(boxes);
    secLine.getChildren().add(boxColor);

    HBox thirdLine = new HBox();
    Label bar = new Label("Change progress bar:     ");
    thirdLine.getChildren().add(bar);
    thirdLine.getChildren().add(barColor);

    specifics.getChildren().add(firstLine);
    specifics.getChildren().add(secLine);
    specifics.getChildren().add(thirdLine);
  }


  /**
   * Returns the current stage of the ColorDialog.
   *
   * @return the current stage of the ColorDialog.
   */
  public Stage getCurrentStage() {
    return stage;
  }

  /**
   * Initializes the OK and Cancel buttons and their actions.
   */
  private void initButtons() {
    getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    Button ok = (Button) getDialogPane().lookupButton(ButtonType.OK);
    Button cancel = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
    specifics.getChildren().addAll(ok, cancel);
    ok.setOnAction(event -> stage.close());
    cancel.setOnAction(event -> stage.close());
  }

  /**
   * Returns the selected background color.
   *
   * @return the selected background color.
   */
  public Color getBackgroundColor() {
    return backgroundColor.getValue();
  }

  /**
   * Returns the selected box color.
   *
   * @return the selected box color.
   */
  public Color getBoxColor() {
    return boxColor.getValue();
  }

  /**
   * Returns the selected progress bar color.
   *
   * @return the selected progress bar color.
   */
  public Color getBarColor() {
    return barColor.getValue();
  }
}
