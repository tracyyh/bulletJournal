package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * represents an event
 *
 * @param name name of the event
 * @param description event description
 * @param startTime start time of the event
 * @param minutes duration of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("start-time") Date startTime,
    @JsonProperty("duration") int minutes) {
}
