package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.DayJson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * tests the BujoJsonConverter class
 */
public class BujoJsonConverterTest {
  private static BujoJsonConverter converter;
  private static Week week;
  private static ArrayList<Day> days = new ArrayList<>();
  private static ObjectMapper mapper;

  /**
   * sets up the initial conditions for testing
   */
  @BeforeAll
  public static void setup() {
    days.addAll(List.of(
        new Day(Weekday.MONDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
            Weekday.MONDAY, new Date(54000000), 60)))),
        new Day(Weekday.TUESDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.TUESDAY, new Date(54000000), 60)))),
        new Day(Weekday.WEDNESDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.WEDNESDAY, new Date(54000000), 60)))),
        new Day(Weekday.THURSDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.THURSDAY, new Date(54000000), 60)))),
        new Day(Weekday.FRIDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.FRIDAY, new Date(54000000), 60)))),
        new Day(Weekday.SATURDAY, new ArrayList<>(List.of(
            new Event("Test Event", "This is a test event",
                Weekday.SATURDAY, new Date(54000000), 60)))),
        new Day(Weekday.SUNDAY, new ArrayList<>(List.of(
            new Task("Test Task", "This is a test task",
                Weekday.SUNDAY, false))))));
    week = new Week("Test Week", days);
    converter = new BujoJsonConverter(week);
    mapper = new ObjectMapper();
  }

  @Test
  public void testGetFinalizedWeek() {
    try {
      String expectedJson = "{\n"
          + "  \"week-name\" : \"\",\n"
          + "  \"arguments\" : [ {\n"
          + "    \"weekday\" : \"MONDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"TUESDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"WEDNESDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"THURSDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"FRIDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"SATURDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"EVENT\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Event\",\n"
          + "        \"description\" : \"This is a test event\",\n"
          + "        \"start-time\" : 54000000,\n"
          + "        \"duration\" : 60\n"
          + "      }\n"
          + "    } ]\n"
          + "  }, {\n"
          + "    \"weekday\" : \"SUNDAY\",\n"
          + "    \"duties\" : [ {\n"
          + "      \"duty\" : \"TASK\",\n"
          + "      \"info\" : {\n"
          + "        \"name\" : \"Test Task\",\n"
          + "        \"description\" : \"This is a test task\",\n"
          + "        \"completed\" : false\n"
          + "      }\n"
          + "    } ]\n"
          + "  } ],\n"
          + "  \"notes\" : \"\"\n"
          + "}";
      assertEquals(expectedJson, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
          converter.getFinalizedWeek()));
    } catch (JsonProcessingException e) {
      System.err.println("Error in testing reading a json");
    }
  }

  @Test
  public void testReadDayJson() {
    DayJson[] dayJsons = converter.getFinalizedWeek().days();
    Day[] daysAsArray = new Day[days.size()];
    for (int i = 0; i < daysAsArray.length; i++) {
      daysAsArray[i] = days.get(i);
      assertEquals(daysAsArray[i].getDutiesArray().length,
          converter.readDayJson(dayJsons)[i].getDutiesArray().length);
      assertEquals(daysAsArray[i].getWeekday(), converter.readDayJson(dayJsons)[i].getWeekday());
    }
  }
}
