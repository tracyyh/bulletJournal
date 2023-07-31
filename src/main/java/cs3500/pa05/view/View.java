package cs3500.pa05.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The View interface represents the graphical user interface (GUI) component of
 * the bullet journal. It provides methods to load the GUI layout and create
 * event-related components.
 */
public interface View {

  /**
   * Loads a scene from a bujo layout.
   *
   * @return the scene
   */
  Scene load() throws IllegalStateException;

  /**
   * Creates an event-related component for the Bullet Journal application.
   * This method is responsible for creating and configuring a JavaFX Stage
   * to handle events related to the application.
   *
   * @return the created Stage for event handling.
   */
  Stage createEvent();
}
