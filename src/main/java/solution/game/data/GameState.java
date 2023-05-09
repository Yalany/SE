package solution.game.data;

import com.google.gson.annotations.SerializedName;
import solution.game.EventDeck;

import java.util.Map;

public record GameState(@SerializedName("id") String id,
                        @SerializedName("resources") Map<String, Integer> resources,
                        @SerializedName("deck") EventDeck deck) {}
