package solution.game;

import com.google.gson.annotations.SerializedName;

public record Event(@SerializedName("id") String id,
                    @SerializedName("description") String description,
                    @SerializedName("immediate_actions") GameStateModification[] immediateActions,
                    @SerializedName("available_options") solution.game.Event.EventReactionOption[] availableOptions) {

  public static class EventReactionOption {
    private String buttonText;
    private String[] activationTokens;
    private GameStateModification[] optionResults;
  }
}
