package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.BujoModel;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class that serves as the entry point for the JavaFX application.
 * It extends the javafx.application.Application class and defines
 * the main method to launch the application.
 */
public class Driver extends Application {

  /**
   * The main method that launches the JavaFX application.
   * This method calls the launch method to start the JavaFX runtime and launch the application.
   *
   * @param args the command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    Application.launch();
  }

  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   * @throws Exception if something goes wrong
   */
  @Override
  public void start(Stage primaryStage)  {
    BujoModel bujoModel = new BujoModel(new Week(""));
    Controller bujoController = new BujoController(bujoModel);
    View bujoView = new BujoView(bujoController);
    primaryStage.setScene(bujoView.load());
    primaryStage.show();
    bujoController.run();
  }
}
