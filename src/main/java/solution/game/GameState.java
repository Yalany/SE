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

  public GameState(final String gameId, int deckSize) {
    this.gameId = gameId;
    this.playerResources = new HashMap<>();
    this.eventDeck = new EventDeck(deckSize);
  }

  public String getGameId() {
    return gameId;
  }

  public Map<String, Integer> getPlayerResources() {
    return playerResources;
  }

  public EventDeck getEventDeck() {
    return eventDeck;
  }
}
