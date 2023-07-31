package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents a week
 *
 * @param name title of the bullet journal
 * @param days list of days in the week
 * @param notes notes for this week
 */
public record WeekJson(
    @JsonProperty("week-name") String name,
    @JsonProperty("arguments") DayJson[] days,
    @JsonProperty("notes") String notes) {
}
