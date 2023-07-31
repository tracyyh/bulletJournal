package cs3500.pa05.model;

/**
 * represents the state of the bullet journal
 */
public interface BujoState {

  /**
   * returns the max amount of events a user can have in a day
   *
   * @return max events in a day
   */
  int getMaxEvents();

  /**
   * returns the max amount of tasks a user can have in a day
   *
   * @return max tasks in a day
   */
  int getMaxTasks();

  /**
   * returns the current week object that is being used in the model
   *
   * @return a week object
   */
  Week getWeek();

  /**
   * returns the week's content so that it can be parsed into JSON and saved for later
   *
   * @return a string of all of the content in a week
   */
  String getWeekContent();

  /**
   * returns the notes that are loaded from an open file
   *
   * @return a string of the opened notes
   */
  String getLoadedNotes();

  /**
   * returns the title that is loaded from a file
   *
   * @return the string of an opened title
   */
  String getLoadedTitle();
}
