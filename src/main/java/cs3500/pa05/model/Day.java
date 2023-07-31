package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Represents a day in a week.
 */
public class Day {

  private Weekday day;
  private ArrayList<Duty> duties;

  /**
   * Constructs a Day with the specified weekday and duties.
   *
   * @param weekday     the weekday of the day
   * @param inputDuties the duties of the day
   */
  public Day(Weekday weekday, ArrayList<Duty> inputDuties) {
    day = weekday;
    duties = inputDuties;
  }

  /**
   * Returns the duties of the day.
   *
   * @return the duties of the day
   */
  public ArrayList<Duty> getDuties() {
    return duties;
  }

  /**
   * Calculates and returns the completion percentage of the day's tasks.
   *
   * @return the completion percentage
   */
  public double completePercent() {
    double count = 0.0;
    for (Duty d : getDuties()) {
      if (d.getTask() != null && d.getTask().getCompleted()) {
        count++;
      }
    }
    return count / duties.size();
  }

  /**
   * Returns an array containing the duties of the day.
   *
   * @return an array of duties
   */
  public Duty[] getDutiesArray() {
    Duty[] resultArray = new Duty[duties.size()];
    for (int i = 0; i < duties.size(); i++) {
      resultArray[i] = duties.get(i);
    }
    return resultArray;
  }

  /**
   * Adds a duty to the day.
   *
   * @param duty the duty to be added
   */
  public void addDuty(Duty duty) {
    duties.add(duty);
  }

  /**
   * Returns the weekday of the day.
   *
   * @return the weekday
   */
  public Weekday getWeekday() {
    return day;
  }

}
