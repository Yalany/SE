package solution.game.gamedata;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record GameStateModification(@SerializedName("type") String type,
                                    @SerializedName("data") Map<String, String> data) {}
