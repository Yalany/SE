package solution.game;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class GameState {
  @SerializedName("game_id")
  private final String gameId;

  @SerializedName("player_resources")
  private Map<String, Integer> playerResources;

  @SerializedName("event_deck")
  private EventDeck eventDeck;

  public GameState(final String gameId, int deckSize, boolean isEndless) {
    this.gameId = gameId;
    this.playerResources = new HashMap<>();
    this.eventDeck = new EventDeck(deckSize, isEndless);
  }

  public String getGameId() {
    return gameId;
  }

  public EventDeck getEventDeck() {
    return eventDeck;
  }

  public int getResourceAmount(String resourceName) {
    assert playerResources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    return playerResources.get(resourceName);
  }

  void modifyResourceAmount(String resourceName, int modification) {
    assert playerResources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    playerResources.put(resourceName, playerResources.get(resourceName) + modification);
  }
}
