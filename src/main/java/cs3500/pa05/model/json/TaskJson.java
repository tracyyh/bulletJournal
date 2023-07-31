package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents a task
 *
 * @param name of the task
 * @param description of the task
 * @param completed whether the task has been completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("completed") Boolean completed) {
}
