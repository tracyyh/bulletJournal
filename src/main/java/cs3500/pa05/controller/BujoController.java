package cs3500.pa05.controller;

import cs3500.pa05.model.BujoModel;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Duty;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.AlertUtils;
import cs3500.pa05.view.ColorDialog;
import cs3500.pa05.view.DutyDialog;
import cs3500.pa05.view.DutyEnum;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * controls the program
 */
public class BujoController implements Controller, Initializable {

  private BujoModel model;

  @FXML
  private AnchorPane background = new AnchorPane();

  @FXML
  private MenuItem addEvent;

  @FXML
  private MenuItem addTask;

  @FXML
  private MenuButton addThings;

  @FXML
  private Button openFile;

  @FXML
  private Button saveFile;

  @FXML
  private TextField maxEvents;

  @FXML
  private TextField maxTasks;

  @FXML
  private TextField bujoTitle;

  private FileChooser fileChooser;

  @FXML
  public VBox monTasks = new VBox();

  @FXML
  public VBox monEvents;

  @FXML
  public VBox tuesTasks = new VBox();

  @FXML
  public VBox tuesEvents;

  @FXML
  public VBox wedTasks = new VBox();

  @FXML
  public VBox wedEvents;

  @FXML
  public VBox thursTasks = new VBox();

  @FXML
  public VBox thursEvents;

  @FXML
  public VBox friTasks = new VBox();

  @FXML
  public VBox friEvents;

  @FXML
  public VBox satTasks = new VBox();

  @FXML
  public VBox satEvents;

  @FXML
  public VBox sunTasks = new VBox();

  @FXML
  public VBox sunEvents;

  @FXML
  public VBox totalTasks = new VBox();

  @FXML
  private VBox monBack = new VBox();

  @FXML
  private VBox tuesBack = new VBox();

  @FXML
  private VBox wedBack = new VBox();

  @FXML
  private VBox thursBack = new VBox();

  @FXML
  private VBox friBack = new VBox();

  @FXML
  private VBox satBack = new VBox();

  @FXML
  private VBox sunBack = new VBox();

  @FXML
  private VBox taskQueue = new VBox();

  @FXML
  private VBox notesBackground = new VBox();

  @FXML
  private TextArea notes = new TextArea();

  List<VBox> backgrounds = new ArrayList<>();

  @FXML
  private MenuButton theme;

  @FXML
  private MenuItem green;

  @FXML
  private MenuItem pink;

  @FXML
  private MenuItem grayscale;

  @FXML
  private MenuItem custom;

  @FXML
  private MenuItem original;

  @FXML
  private ProgressBar monProgress;

  @FXML
  private ProgressBar tuesProgress;

  @FXML
  private ProgressBar wedProgress;

  @FXML
  private ProgressBar thursProgress;

  @FXML
  private ProgressBar friProgress;

  @FXML
  private ProgressBar satProgress;

  @FXML
  private ProgressBar sunProgress;

  private List<ProgressBar> bars = new ArrayList<>();

  @FXML
  private HBox splash = new HBox();

  @FXML
  private VBox splashVbox = new VBox();

  @FXML
  private TextField username = new TextField();

  @FXML
  private Label incorrectUser = new Label();

  @FXML
  private Label splashTitle = new Label();

  @FXML
  private HBox promptUser = new HBox();

  @FXML
  private Label userLabel = new Label();

  @FXML
  private ImageView ducky = new ImageView();

  @FXML
  private ImageView flower = new ImageView();

  @FXML
  private ImageView flower1 = new ImageView();

  private int count = 0;

  private int flCount = 0;

  /**
   * constructor
   *
   * @param bujoModel the model of the program
   */
  public BujoController(BujoModel bujoModel) {
    addThings = new MenuButton();
    model = bujoModel;
    addEvent = new MenuItem();
    addTask = new MenuItem();
    addThings.getItems().add(addEvent);
    addThings.getItems().add(addTask);
    openFile = new Button();
    saveFile = new Button();
    maxEvents = new TextField();
    maxTasks = new TextField();
    fileChooser = new FileChooser();
    bujoTitle = new TextField();
    theme = new MenuButton();
    green = new MenuItem();
    pink = new MenuItem();
    grayscale = new MenuItem();
    custom = new MenuItem();
    original = new MenuItem();
    theme.getItems().add(green);
    theme.getItems().add(pink);
    theme.getItems().add(grayscale);
    theme.getItems().add(custom);
    theme.getItems().add(original);
    bindTitleAndNotes();
  }

  /**
   * runs the program
   */
  public void run() {
    splashScreen();
    initImageButtons();
    initMenuButtons();
    initCreateButtons();
    initThemeButtons();
    initMaxFields();
  }

  /**
   * sets commands for when images are clicked
   */
  private void initImageButtons() {
    ducky.setOnMouseClicked(e -> changePic());
    flower.setOnMouseClicked(e -> changeFlPic(flower));
    flower1.setOnMouseClicked(e -> changeFlPic(flower1));
  }

  /**
   * changes the pictures of ducky so that it alternates
   */
  private void changePic() {
    count++;
    if (count % 2 != 0) {
      ducky.setImage(new Image("duckyFinish.png"));
    } else {
      ducky.setImage(new Image("ducky.png"));
    }
  }

  /**
   * changes the pictures of the flowers so that it alternates
   */
  private void changeFlPic(ImageView fl) {
    flCount++;
    if (flCount % 2 != 0) {
      fl.setImage(new Image("flowerFlip.png"));
    } else {
      fl.setImage(new Image("flower.png"));
    }
  }

  /**
   * starts the splash screen
   */
  private void splashScreen() {
    FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), splashVbox);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.play();
    fadeIn.setOnFinished(e -> initUsername());
  }

  /**
   * allows user input of the username
   */
  private void initUsername() {
    username.setOnAction(e -> checkUsername());
  }

  /**
   * checks whether the inputted username is correct
   */
  @FXML
  private void checkUsername() {
    if (username.getText().equals(System.getProperty("user.name"))) {
      welcomeUser();
      incorrectUser.setVisible(false);
      promptUser.setVisible(false);
      userLabel.setVisible(false);
    } else {
      incorrectUser.setVisible(true);
      checkUsername();
    }
  }

  /**
   * welcomes the user
   */
  private void welcomeUser() {
    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), splashTitle);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    splashTitle.setVisible(true);
    fadeIn.play();
    fadeIn.setOnFinished(e -> fadeAway());
  }

  /**
   * displays the program
   */
  private void fadeAway() {
    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splash);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.play();
    fadeOut.setOnFinished(e -> splash.setVisible(false));
  }

  /**
   * sets commands for when inputs are added to the maxEvents and maxTasks labels
   */
  private void initMaxFields() {
    maxEvents.setOnAction(event -> handleMaxEvents());
    maxTasks.setOnAction(event -> handleMaxTasks());
  }

  /**
   * sets the max number of events
   */
  private void handleMaxEvents() {
    if (maxEvents.getText().equals("")) {
      AlertUtils.showNewWarning("Please fill out this field");
    } else {
      model.setMaxEvents(Integer.parseInt(maxEvents.getText()));
      System.out.println("Implement this later");
    }
  }

  /**
   * sets the max number of tasks
   */
  private void handleMaxTasks() {
    if (maxTasks.getText().equals("")) {
      AlertUtils.showNewWarning("Please fill out this field");
    } else {
      model.setMaxTasks(Integer.parseInt(maxTasks.getText()));
      System.out.println("Implement this later");
    }
  }

  /**
   * sets commands for when add event and add task buttons are clicked
   */
  private void initMenuButtons() {
    addEvent.setOnAction(event -> handleMenuEvent());
    addTask.setOnAction(event -> handleMenuTask());
  }

  /**
   * sets commands for when theme buttons are clicked
   */
  private void initThemeButtons() {
    initBacks();
    initBars();
    original.setOnAction(event -> originalTheme());
    green.setOnAction(event -> greenTheme());
    pink.setOnAction(event -> pinkTheme());
    grayscale.setOnAction(event -> grayscaleTheme());
    custom.setOnAction(event -> customTheme());
  }

  /**
   * binds the text in the associated fields to the title and the notes
   */
  private void bindTitleAndNotes() {
    bujoTitle.textProperty().bindBidirectional(model.getWeek().nameProperty());
    notes.textProperty().bindBidirectional(model.getWeek().notesProperty());
  }

  /**
   * creates a new event
   */
  private void handleMenuEvent() {
    Duty injectedEvent = new Event();
    DutyDialog newEvent = new DutyDialog(DutyEnum.EVENT, injectedEvent);
    newEvent.getCurrentStage().showAndWait();
    Weekday newDutyDay = injectedEvent.getWeekday();
    boolean overBooked = model.getWeek().getDayEvents(newDutyDay) + 1 > model.getMaxEvents();

    if (overBooked) {
      AlertUtils.showNewWarning("Max events reached for this day of the week.");
    } else {
      model.addDuty(injectedEvent);
      delegateDayEvent(injectedEvent);
    }
  }

  /**
   * creates a new task
   */
  private void handleMenuTask() {
    Task injectedTask = new Task();
    DutyDialog newTask = new DutyDialog(DutyEnum.TASK, injectedTask);
    newTask.getCurrentStage().showAndWait();

    Weekday newDutyDay = injectedTask.getWeekday();
    boolean overBooked = model.getWeek().getDayTasks(newDutyDay) + 1 > model.getMaxTasks();

    if (overBooked) {
      AlertUtils.showNewWarning("Max tasks reached for this day of the week.");
    } else {
      model.addDuty(injectedTask);
      TaskUtils taskUtil = new TaskUtils(injectedTask);
      taskUtil.initCheckBox();
      taskUtil.getTaskCheckBox().setOnAction(event -> updateAllStatuses(injectedTask, taskUtil));
      taskUtil.getQueueCheckBox().setOnAction(event -> updateAllStatuses(injectedTask, taskUtil));
      delegateDayTask(injectedTask, taskUtil);
      updateProgress(injectedTask, (model.getPercent(injectedTask)));
      showInQueue(injectedTask, taskUtil);
    }
  }

  /**
   * updates the checkboxes and the progress bar
   *
   * @param duty the task
   * @param taskUtil task utilities class
   */
  public void updateAllStatuses(Task duty, TaskUtils taskUtil) {
    taskUtil.changeSelected();
    updateProgress(duty, (model.getPercent(duty)));
  }

  /**
   * updates the progress bar according to the day of the week
   *
   * @param duty the task
   * @param percent the percent of tasks completed for that day
   */
  @FXML
  private void updateProgress(Duty duty, double percent) {
    if (duty.getWeekday().equals(Weekday.MONDAY)) {
      updateBar(monProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.TUESDAY)) {
      updateBar(tuesProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.WEDNESDAY)) {
      updateBar(wedProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.THURSDAY)) {
      updateBar(thursProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.FRIDAY)) {
      updateBar(friProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.SATURDAY)) {
      updateBar(satProgress, percent);
    } else if (duty.getWeekday().equals(Weekday.SUNDAY)) {
      updateBar(sunProgress, percent);
    }
  }

  /**
   * updates the given progress bar with the given percentage
   *
   * @param bar progress bar to be updated
   * @param percent percent to be inputted
   */
  @FXML
  private void updateBar(ProgressBar bar, double percent) {
    bar.setProgress(percent);
  }

  /**
   * Initializes the create buttons for opening and saving files.
   * Adds event handlers for button clicks.
   */
  private void initCreateButtons() {
    openFile.setOnAction(event -> handleOpenFile());
    saveFile.setOnAction(event -> handleSaveFile());
  }

  /**
   * Event handler for the "Open File" button.
   * Opens a file chooser dialog for the user to select a file to open.
   * Once a file is selected, the corresponding file handling logic can be implemented.
   */
  private void handleOpenFile() {
    File file = fileChooser.showOpenDialog(new Stage());
    if (file.getPath().endsWith(".bujo")) {
      wipeAllDays();
      delegateDays(model.readContents(file));
      bujoTitle.setText(model.getLoadedTitle());
      notes.setText(model.getLoadedNotes());
    } else {
      Alert invalidFile = new Alert(Alert.AlertType.ERROR);
      invalidFile.setContentText(
          "the file you are trying to open is not of the right format: .bujo");
      invalidFile.show();
    }
  }

  /**
   * Delegates the duties from the given array of days.
   * Adds duties to the model and performs specific actions based on the duty type.
   *
   * @param readDays An array of Day objects containing duties.
   */
  private void delegateDays(Day[] readDays) {
    for (Day day : readDays) {
      for (Duty duty : day.getDuties()) {
        model.addDuty(duty);
        if (BujoModel.convertToEnum(duty).equals(DutyEnum.EVENT)) {
          delegateDayEvent(duty);
        } else {
          TaskUtils taskUtil = new TaskUtils((Task) duty);
          delegateDayTask(duty.getTask(), taskUtil);
          updateProgress(duty.getTask(), (model.getPercent(duty.getTask())));
          showInQueue(duty.getTask(), taskUtil);

          taskUtil.initCheckBox();
          taskUtil.getTaskCheckBox().setOnAction(event ->
              updateAllStatuses(duty.getTask(), taskUtil));
          taskUtil.getQueueCheckBox().setOnAction(event ->
              updateAllStatuses(duty.getTask(), taskUtil));
        }
      }
    }
  }

  /**
   * clears the week gui of all the days events and tasks (resets progress bars)
   */
  private void wipeAllDays() {
    monEvents.getChildren().clear();
    monTasks.getChildren().clear();
    tuesEvents.getChildren().clear();
    tuesTasks.getChildren().clear();
    wedEvents.getChildren().clear();
    wedTasks.getChildren().clear();
    thursEvents.getChildren().clear();
    thursTasks.getChildren().clear();
    friEvents.getChildren().clear();
    friTasks.getChildren().clear();
    satEvents.getChildren().clear();
    satEvents.getChildren().clear();
    sunEvents.getChildren().clear();
    sunTasks.getChildren().clear();
    totalTasks.getChildren().clear();
    monProgress.setProgress(0.0);
    tuesProgress.setProgress(0.0);
    wedProgress.setProgress(0.0);
    thursProgress.setProgress(0.0);
    friProgress.setProgress(0.0);
    satProgress.setProgress(0.0);
    sunProgress.setProgress(0.0);
    model.getWeek().clearDuties();
  }

  /**
   * takes all of the information added into the current week into a bujo file
   */
  public void handleSaveFile() {
    File file = fileChooser.showSaveDialog(new Stage());
    model.getWeek().setTitle(bujoTitle.getText());
    model.getWeek().setNotes(notes.getText());
    if (file != null) {
      model.saveFileHelper(file, model.getWeekContent());
    }
  }


  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if
   *                  the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fileChooser.setInitialDirectory(new File("src/main/resources"));
  }

  /**
   * display the event in the designated weekdays box
   *
   * @param events the vbox that the event is added to
   * @param duty the event
   */
  @FXML
  public void showEvent(VBox events, Duty duty) {
    Label event = new Label();
    event.setText("  - " + duty.getName());
    Font font = new Font("Courier New", 20.0);
    event.setFont(font);
    events.getChildren().add(event);
    event.setOnMouseClicked(click -> {
      if (click.getButton() == MouseButton.SECONDARY) {
        openEvent(duty, event);
      }
    });
  }

  /**
   * opens an event dialog when right clicked
   *
   * @param duty given event
   * @param label the events label
   */
  private void openEvent(Duty duty, Label label) {
    DutyDialog newEvent = new DutyDialog(DutyEnum.EVENT, duty);
    newEvent.getCurrentStage().showAndWait();
    label.setText(" - " + duty.getName());
  }

  /**
   * opens a task dialog when right clicked
   *
   * @param duty the given task
   * @param taskUtil task util associated with that task
   */
  private void openTask(Task duty, TaskUtils taskUtil) {
    DutyDialog newTask = new DutyDialog(DutyEnum.TASK, duty);
    newTask.getCurrentStage().showAndWait();
    taskUtil.setLabelText(duty.getName());
    updateProgress(duty, model.getPercent(duty));
    taskUtil.initCheckBox();
  }

  /**
   * shows the task in the day
   *
   * @param vboxTasks day that the task is being added to
   * @param taskDuty the task to be adde
   * @param taskUtils the task utilities class
   */
  public void showInTaskBox(VBox vboxTasks, Task taskDuty, TaskUtils taskUtils) {
    HBox hbox = taskUtils.setupBlock("task");
    hbox.setVisible(true);
    vboxTasks.setVisible(true);
    vboxTasks.getChildren().add(hbox);
    taskUtils.getTaskCheckBox().setOnMouseClicked((click) -> {
      if (click.getButton() == MouseButton.SECONDARY) {
        openTask(taskDuty, taskUtils);
      }
    });
  }

  /**
   * shows the task in the task queue
   *
   * @param taskDuty task to be added
   * @param taskUtil task utilities class
   */
  public void showInQueue(Task taskDuty, TaskUtils taskUtil) {
    HBox hbox = taskUtil.setupBlock("");
    hbox.setVisible(true);
    totalTasks.getChildren().add(hbox);
    taskUtil.getQueueCheckBox().setOnMouseClicked((click) -> {
      if (click.getButton() == MouseButton.SECONDARY) {
        openTask(taskDuty, taskUtil);
      }
    });
  }

  /**
   * shows the task according to the day of the week
   *
   * @param duty task to be added
   */
  @FXML
  public void delegateDayTask(Task duty, TaskUtils taskUtil) {
    if (duty.getWeekday().equals(Weekday.MONDAY)) {
      showInTaskBox(monTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.TUESDAY)) {
      showInTaskBox(tuesTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.WEDNESDAY)) {
      showInTaskBox(wedTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.THURSDAY)) {
      showInTaskBox(thursTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.FRIDAY)) {
      showInTaskBox(friTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.SATURDAY)) {
      showInTaskBox(satTasks, duty, taskUtil);
    } else if (duty.getWeekday().equals(Weekday.SUNDAY)) {
      showInTaskBox(sunTasks, duty, taskUtil);
    }
  }

  /**
   * shows the event according to the day of the week
   *
   * @param duty event to be added
   */
  @FXML
  public void delegateDayEvent(Duty duty) {
    if (duty.getWeekday().equals(Weekday.MONDAY)) {
      showEvent(monEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.TUESDAY)) {
      showEvent(tuesEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.WEDNESDAY)) {
      showEvent(wedEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.THURSDAY)) {
      showEvent(thursEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.FRIDAY)) {
      showEvent(friEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.SATURDAY)) {
      showEvent(satEvents, duty);
    } else if (duty.getWeekday().equals(Weekday.SUNDAY)) {
      showEvent(sunEvents, duty);
    }
  }

  /**
   * adds all darker containers to list of VBox
   */
  @FXML
  public void initBacks() {
    backgrounds.add(monBack);
    backgrounds.add(tuesBack);
    backgrounds.add(wedBack);
    backgrounds.add(thursBack);
    backgrounds.add(friBack);
    backgrounds.add(satBack);
    backgrounds.add(sunBack);
    backgrounds.add(taskQueue);
    backgrounds.add(notesBackground);
  }

  /**
   * adds all progress bars to list of progress bars
   */
  @FXML
  public void initBars() {
    bars.add(monProgress);
    bars.add(tuesProgress);
    bars.add(wedProgress);
    bars.add(thursProgress);
    bars.add(friProgress);
    bars.add(satProgress);
    bars.add(sunProgress);
  }

  /**
   * sets the program to the original theme
   */
  @FXML
  public void originalTheme() {
    background.setStyle("-fx-background-color: floralwhite");
    for (VBox box : backgrounds) {
      addThings.setStyle("-fx-background-color: antiquewhite");
      theme.setStyle("-fx-background-color: antiquewhite");
      openFile.setStyle("-fx-background-color: antiquewhite");
      saveFile.setStyle("-fx-background-color: antiquewhite");
      box.setStyle("-fx-background-color: antiquewhite");
    }
    for (ProgressBar bar : bars) {
      bar.setStyle("-fx-accent:  bfd9bf");
    }
  }

  /**
   * sets the program to a green theme
   */
  @FXML
  public void greenTheme() {
    background.setStyle("-fx-background-color: darkseagreen");
    for (VBox box : backgrounds) {
      addThings.setStyle("-fx-background-color: bfd9bf");
      theme.setStyle("-fx-background-color: bfd9bf");
      openFile.setStyle("-fx-background-color: bfd9bf");
      saveFile.setStyle("-fx-background-color: bfd9bf");
      box.setStyle("-fx-background-color: bfd9bf");
    }
    for (ProgressBar bar : bars) {
      bar.setStyle("-fx-accent: #80a980");
    }
  }

  /**
   * sets the program to a pink theme
   */
  @FXML
  public void pinkTheme() {
    background.setStyle("-fx-background-color: lavenderblush");
    for (VBox box : backgrounds) {
      addThings.setStyle("-fx-background-color: ffd7e4");
      theme.setStyle("-fx-background-color: ffd7e4");
      openFile.setStyle("-fx-background-color: ffd7e4");
      saveFile.setStyle("-fx-background-color: ffd7e4");
      box.setStyle("-fx-background-color: ffd7e4");
    }
    for (ProgressBar bar : bars) {
      bar.setStyle("-fx-accent: ffbdd3");
    }
  }

  /**
   * sets the program to a black and white theme
   */
  @FXML
  public void grayscaleTheme() {
    background.setStyle("-fx-background-color: white");
    for (VBox box : backgrounds) {
      addThings.setStyle("-fx-background-color: white");
      addThings.setStyle("-fx-border-color: black");
      theme.setStyle("-fx-background-color: white");
      theme.setStyle("-fx-border-color: black");
      openFile.setStyle("-fx-background-color: white");
      openFile.setStyle("-fx-border-color: black");
      saveFile.setStyle("-fx-background-color: white");
      saveFile.setStyle("-fx-border-color: black");
      box.setStyle("-fx-background-color: white");
      box.setStyle("-fx-border-color: black");
    }
    for (ProgressBar bar : bars) {
      bar.setStyle("-fx-accent: lightgray");
    }
  }

  /**
   * changes the theme of the program based on the user's customizations
   */
  public void customTheme() {
    ColorDialog chooseColor = new ColorDialog();
    chooseColor.getCurrentStage().showAndWait();
    Color backgroundColor = chooseColor.getBackgroundColor();
    String backColor = "rgb(" + backgroundColor.getRed() * 255 + "," + backgroundColor.getGreen()
        * 255 + "," + backgroundColor.getBlue() * 255 + ");";
    background.setStyle("-fx-background-color: " + backColor);

    Color boxes = chooseColor.getBoxColor();
    for (VBox box : backgrounds) {
      String color = "rgb(" + boxes.getRed() * 255 + "," + boxes.getGreen() * 255 + ","
          + boxes.getBlue() * 255 + ");";
      addThings.setStyle("-fx-background-color: " + color);
      theme.setStyle("-fx-background-color: " + color);
      openFile.setStyle("-fx-background-color: " + color);
      saveFile.setStyle("-fx-background-color: " + color);
      box.setStyle("-fx-background-color: " + color);
    }

    Color barColor = chooseColor.getBarColor();
    for (ProgressBar bar : bars) {
      String color = "rgb(" + barColor.getRed() * 255 + "," + barColor.getGreen() * 255 + ","
          + barColor.getBlue() * 255 + ");";
      bar.setStyle("-fx-accent: " + color);
    }
  }
}
