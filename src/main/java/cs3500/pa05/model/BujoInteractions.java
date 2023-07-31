package cs3500.pa05.model;

import java.io.File;

/**
 * represents the interactions a user can have on the bullet journal
 */
public interface BujoInteractions extends BujoState {

  /**
   * saves the contents of this week to a designated file
   *
   * @param file the file to be saved to
   * @param content the string content of this week
   */
  void saveFileHelper(File file, String content);

  /**
   * sets the max number of tasks for each day
   *
   * @param num the max number of tasks a user can make per day
   */
  void setMaxTasks(int num);

  /**
   * sets the max number of events for each day
   *
   * @param num the max number of events as user can make per day
   */
  void setMaxEvents(int num);

  /**
   * Adds a duty to the week.
   *
   * @param duty the duty to be added
   */
  void addDuty(Duty duty);

  /**
   * Calculates and returns the completion percentage of a duty in the week.
   *
   * @param duty the duty to calculate the completion percentage for
   * @return the completion percentage of the duty
   */
  double getPercent(Duty duty);

  /**
   * Sets the week.
   *
   * @param bujoWeek the week to be set
   */
  void setWeek(Week bujoWeek);

  /**
   * Reads the contents of a JSON file and returns an array of Day objects.
   *
   * @param file the JSON file to read
   * @return an array of Day objects representing the loaded data
   */
  Day[] readContents(File file);

  /**
   * Sets the loaded title.
   *
   * @param inp the title to be set
   */
  void setLoadedTitle(String inp);

  /**
   * Sets the loaded notes.
   *
   * @param in the notes to be set
   */
  void setLoadedNotes(String in);
}
