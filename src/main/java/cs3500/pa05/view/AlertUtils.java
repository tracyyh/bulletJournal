package cs3500.pa05.view;

import javafx.scene.control.Alert;

/**
 * The AlertUtils class provides utility methods for displaying warning alerts.
 * It extends the JavaFX Alert class.
 */
public class AlertUtils extends Alert {

  /**
   * Constructs an AlertUtils object with the warning alert type.
   */
  public AlertUtils() {
    super(AlertType.WARNING);
  }

  /**
   * Displays a new warning alert with the specified content.
   *
   * @param content the content of the warning alert.
   */
  public static void showNewWarning(String content) {
    Alert a = new Alert(AlertType.WARNING, content);
    a.show();
  }
}
