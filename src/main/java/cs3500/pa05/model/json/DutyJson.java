package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.view.DutyEnum;

/**
 * represents a duty
 *
 * @param duty the duty
 * @param info information about the duty
 */
public record DutyJson(
    @JsonProperty("duty") DutyEnum duty,
    @JsonProperty("info") JsonNode info) {

}
