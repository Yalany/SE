package solution.game.gamedata;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record StaticGameData(@SerializedName("events") Map<String, Event> events,
                             @SerializedName("resources") Map<String, Resource> resources) {}
