package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.DutyEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the model of a Bullet Journal application.
 */
public class BujoModel implements BujoInteractions {
  private Week week;
  private String loadedTitle;
  private String loadedNotes;
  private ArrayList<Duty> weekDuties;
  private ObjectMapper mapper = new ObjectMapper();
  private int maxTasks;
  private int maxEvents;

  /**
   * Constructs a BujoModel with the specified week.
   *
   * @param week the week
   */
  public BujoModel(Week week) {
    this.weekDuties = new ArrayList<>();
    this.week = week;
    List<Weekday> weekdays = new ArrayList<Weekday>(Arrays.asList(Weekday.values()));
    for (Weekday day : weekdays) {
      week.addDayToWeek(new Day(day, new ArrayList<>()));
    }
    this.maxEvents = 99999;
    this.maxTasks = 99999;
  }


  /**
   * helper method for saving a file into a bujo document
   *
   * @param file the given file
   * @param content content to be saved
   */
  public void saveFileHelper(File file, String content) {
    try {
      PrintWriter printWriter = new PrintWriter(file);
      printWriter.write(content);
      printWriter.close();
    } catch (FileNotFoundException e) {
      System.err.println("There is an issue with saving this file");
    }
  }

  /**
   * Sets the maximum number of tasks.
   *
   * @param num the maximum number of tasks
   */
  public void setMaxTasks(int num) {
    maxTasks = num;
  }

  /**
   * Sets the maximum number of events.
   *
   * @param num the maximum number of events
   */
  public void setMaxEvents(int num) {
    maxEvents = num;
  }

  /**
   * Returns the maximum number of events.
   *
   * @return the maximum number of events
   */
  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * Returns the maximum number of tasks.
   *
   * @return the maximum number of tasks
   */
  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * Adds a duty to the week.
   *
   * @param duty the duty to be added
   */
  public void addDuty(Duty duty) {
    weekDuties.add(duty);
    week.addDutyToDay(duty);
  }

  /**
   * Calculates and returns the completion percentage of a duty in the week.
   *
   * @param duty the duty to calculate the completion percentage for
   * @return the completion percentage of the duty
   */
  public double getPercent(Duty duty) {
    return week.getDayPercent(duty);
  }

  /**
   * Returns the week.
   *
   * @return the week
   */
  public Week getWeek() {
    return week;
  }

  /**
   * Sets the week.
   *
   * @param bujoWeek the week to be set
   */
  public void setWeek(Week bujoWeek) {
    week = bujoWeek;
  }

  /**
   * Returns the content of the week in JSON format.
   *
   * @return the content of the week as a JSON string
   */
  public String getWeekContent() {
    String content = "";
    try {
      BujoJsonConverter converter = new BujoJsonConverter(week);
      content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
          converter.getFinalizedWeek());
    } catch (JsonProcessingException e) {
      System.err.println("There was an issue with parsing the week's content in JSON");
    }
    return content;
  }

  /**
   * Converts a Duty object to the corresponding DutyEnum value.
   *
   * @param input the Duty object to convert
   * @return the DutyEnum value
   */
  public static DutyEnum convertToEnum(Duty input) {
    return (input.getClass().getName().endsWith(".Event")) ? DutyEnum.EVENT : DutyEnum.TASK;
  }

  /**
   * Reads the contents of a JSON file and returns an array of Day objects.
   *
   * @param file the JSON file to read
   * @return an array of Day objects representing the loaded data
   */
  public Day[] readContents(File file) {
    Day[] loadedDays = new Day[7];
    BujoJsonConverter converter = new BujoJsonConverter(this.week);
    try {
      JsonParser parser = mapper.getFactory().createParser(file);
      WeekJson loadedJson = parser.readValueAs(WeekJson.class);
      setLoadedTitle(loadedJson.name());
      setLoadedNotes(loadedJson.notes());
      loadedDays = converter.readDayJson(loadedJson.days());
    } catch (IOException e) {
      System.err.println("Error loading file");
    }
    return loadedDays;
  }

  /**
   * Sets the loaded title.
   *
   * @param inp the title to be set
   */
  public void setLoadedTitle(String inp) {
    loadedTitle = inp;
  }

  /**
   * Sets the loaded notes.
   *
   * @param in the notes to be set
   */
  public void setLoadedNotes(String in) {
    loadedNotes = in;
  }

  /**
   * Returns the loaded notes.
   *
   * @return the loaded notes
   */
  public String getLoadedNotes() {
    return loadedNotes;
  }

  /**
   * Returns the loaded title.
   *
   * @return the loaded title
   */
  public String getLoadedTitle() {
    return loadedTitle;
  }
}
