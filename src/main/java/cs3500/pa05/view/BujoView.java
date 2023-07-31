package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The BujoView class represents the view component of a bullet journal GUI.
 * It implements the {@link View} interface
 * and is responsible for loading the GUI layout from an FXML file.
 */
public class BujoView implements View {

  private FXMLLoader loader;

  /**
   * Constructs a BujoView object with the specified controller.
   *
   * @param controller the controller associated with the view.
   */
  public BujoView(Controller controller) {
    loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("OriginalState.fxml"));
    loader.setController(controller);
  }

  /**
   * Loads the bullet journal scene from the GUI layout file.
   *
   * @return the loaded scene.
   * @throws IllegalStateException if the layout file cannot be loaded.
   */
  @Override
  public Scene load() {
    try {
      return loader.load();
    } catch (IOException ex) {
      throw new IllegalStateException("Unable to load layout");
    }
  }

  /**
   * Creates a new event in the bullet journal.
   *
   * @return the stage for the event creation.
   */
  @Override
  public Stage createEvent() {
    return null;
  }
}
