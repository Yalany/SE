package solution.game;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record GameStateModification(@SerializedName("modification_type") String modificationType,
                                    @SerializedName("modification_data") Map<String, String>modificationData) {}
