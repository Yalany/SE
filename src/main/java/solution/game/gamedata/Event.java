package solution.game.gamedata;

import com.google.gson.annotations.SerializedName;

public record Event(@SerializedName("id") String id,
                    @SerializedName("description") String description,
                    @SerializedName("immediate_actions") GameStateModification[] immediateActions,
                    @SerializedName("available_options") EventReactionOption[] availableOptions) {}
