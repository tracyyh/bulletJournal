package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.DutyJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.view.DutyEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this represents the class to prep all saved data to dump into saved file in JSON format
 */
public class BujoJsonConverter {
  private WeekJson finalizedWeek;

  /**
   * Constructs a BujoJsonConverter with the specified Week to convert.
   *
   * @param weekToConvert the Week to convert
   */
  public BujoJsonConverter(Week weekToConvert) {
    finalizedWeek = convertWeek(weekToConvert);
  }

  /**
   * Converts a Week object to its JSON representation.
   *
   * @param inputWeek the Week object to convert
   * @return the JSON representation of the Week
   */
  private WeekJson convertWeek(Week inputWeek) {
    DayJson[] convertedDays = convertDays(inputWeek.getDays());
    return new WeekJson(inputWeek.getName(), convertedDays, inputWeek.getNotes());
  }

  /**
   * Converts an array of Day objects to their JSON representation.
   *
   * @param daysToConvert the array of Day objects to convert
   * @return an array of DayJson objects representing the converted Days
   */
  private DayJson[] convertDays(Day[] daysToConvert) {
    DayJson[] result = new DayJson[daysToConvert.length];
    for (int i = 0; i < daysToConvert.length; i++) {
      result[i] = convertDay(daysToConvert[i]);
    }
    return result;
  }

  /**
   * Converts a Day object to its JSON representation.
   *
   * @param inputDay the Day object to convert
   * @return the JSON representation of the Day
   */
  private DayJson convertDay(Day inputDay) {
    return new DayJson(inputDay.getWeekday(), convertDuties(inputDay.getDutiesArray()));
  }

  /**
   * Converts an array of Duty objects to their JSON representation.
   *
   * @param dutiesToConvert the array of Duty objects to convert
   * @return an array of DutyJson objects representing the converted Duties
   */
  private DutyJson[] convertDuties(Duty[] dutiesToConvert) {
    DutyJson[] resultJson = new DutyJson[dutiesToConvert.length];
    for (int i = 0; i < dutiesToConvert.length; i++) {
      resultJson[i] = convertDuty(dutiesToConvert[i]);
    }
    return resultJson;
  }

  /**
   * Converts a Duty object to its JSON representation.
   *
   * @param inputDuty the Duty object to convert
   * @return the JSON representation of the Duty
   */
  private DutyJson convertDuty(Duty inputDuty) {
    DutyJson resultDuty;
    if (BujoModel.convertToEnum(inputDuty).equals(DutyEnum.EVENT)) {
      EventJson tempEvent = new EventJson(
          inputDuty.getName(),
          inputDuty.getDescription(),
          inputDuty.getEvent().getStartTime(),
          inputDuty.getEvent().getDuration());
      resultDuty = new DutyJson(BujoModel.convertToEnum(inputDuty),
          JsonUtils.serializeRecord(tempEvent));
    } else {
      TaskJson tempTask = new TaskJson(
          inputDuty.getName(),
          inputDuty.getDescription(),
          inputDuty.getTask().getCompleted());
      resultDuty = new DutyJson(BujoModel.convertToEnum(inputDuty),
          JsonUtils.serializeRecord(tempTask));
    }
    return resultDuty;
  }

  /**
   * Returns the finalized WeekJson object.
   *
   * @return the finalized WeekJson object
   */
  public WeekJson getFinalizedWeek() {
    return finalizedWeek;
  }

  /**
   * Converts an array of DayJson objects to an array of Day objects.
   *
   * @param inputJson the array of DayJson objects to convert
   * @return an array of Day objects representing the converted Days
   */
  public Day[] readDayJson(DayJson[] inputJson) {
    Day[] loadedList = new Day[inputJson.length];
    for (int i = 0; i < inputJson.length; i++) {
      Weekday loadedWeekday = inputJson[i].weekday();
      ArrayList<Duty> loadedDuties = new ArrayList<>();

      loadedDuties.addAll(readDutiesJson(inputJson[i].duties(), loadedWeekday));
      loadedList[i] = new Day(loadedWeekday, loadedDuties);
    }
    return loadedList;
  }

  /**
   * Converts an array of DutyJson objects to a List of Duty objects.
   *
   * @param inputDuties the array of DutyJson objects to convert
   * @param input       the Weekday object representing the day of the Duties
   * @return a List of Duty objects representing the converted Duties
   */
  private List<Duty> readDutiesJson(DutyJson[] inputDuties, Weekday input) {
    ArrayList<Duty> convertedDuties = new ArrayList<>();
    for (int i = 0; i < inputDuties.length; i++) {
      JsonNode currNode = inputDuties[i].info();
      String name = currNode.get("name").asText();
      String desc = currNode.get("description").asText();
      if (inputDuties[i].duty().equals(DutyEnum.EVENT)) {
        Date startTime = new Date(currNode.get("start-time").asLong());
        int duration = currNode.get("duration").asInt();
        convertedDuties.add(i, new Event(name, desc, input, startTime, duration));
      } else {
        Boolean completed = Boolean.parseBoolean(currNode.get("completed").asText());
        convertedDuties.add(i, new Task(name, desc, input, completed));
      }
    }
    return convertedDuties;
  }
}
