package solution.game;

import com.google.gson.annotations.SerializedName;

public class GameState {
  @SerializedName("game_id")
  private final String gameId;

  @SerializedName("player_resources")
  private PlayerResources playerResources;

  @SerializedName("event_deck")
  private EventDeck eventDeck;

  public GameState(final String gameId, int deckSize, boolean isEndless) {
    this.gameId = gameId;
    this.playerResources = new PlayerResources();
    this.eventDeck = new EventDeck(deckSize, isEndless);
  }

  public String getGameId() {
    return gameId;
  }

  public PlayerResources getPlayerResources() {
    return playerResources;
  }

  public EventDeck getEventDeck() {
    return eventDeck;
  }
}
