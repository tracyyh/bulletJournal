package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Weekday;

/**
 * represents a day
 *
 * @param weekday the day of the week
 * @param duties the duties of that day
 */
public record DayJson(
    @JsonProperty("weekday") Weekday weekday,
    @JsonProperty("duties") DutyJson[] duties) {
}
