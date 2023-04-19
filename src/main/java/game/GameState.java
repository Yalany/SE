package game;

import com.google.gson.annotations.SerializedName;
import util.FileUtils;

public class GameState {
  @SerializedName("user_id")
  private String userId;

  @SerializedName("player_resources")
  private PlayerResources playerResources;

  @SerializedName("event_deck")
  private EventDeck eventDeck;

  @SerializedName("deck_pointer")
  private int deckPointer;

  GameState(final String userId) {
    this.userId = userId;
    if (FileUtils.fileExists(path(userId)))
      Config.GSON.fromJson(FileUtils.readFile(path(userId)), GameState.class);
  }

  void save() {
    FileUtils.writeFile(path(userId), Config.GSON.toJson(this));
  }

  private static String path(final String userId) {
    return Config.GAME_DATA_DIRECTORY + userId + Config.JSON_POSTFIX;
  }
}
