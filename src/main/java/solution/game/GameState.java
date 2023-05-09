package solution.game;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record GameState(@SerializedName("game_id") String gameId,
                        @SerializedName("player_resources") Map<String, Integer> playerResources,
                        @SerializedName("event_deck") EventDeck eventDeck) {}
