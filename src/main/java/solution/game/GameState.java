package solution.game;

import com.google.gson.annotations.SerializedName;

public class GameState {
  @SerializedName("game_id")
  private final String gameId;

  @SerializedName("player_resources")
  private PlayerResources playerResources;

  @SerializedName("event_deck")
  private EventDeck eventDeck;

  @SerializedName("deck_pointer")
  private int deckPointer;

  public GameState(final String gameId, int deckSize, boolean isEndless) {
    this.gameId = gameId;
    this.playerResources = new PlayerResources();
    this.eventDeck = new EventDeck(deckSize, isEndless);
    this.deckPointer = 0;
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

  public int getDeckPointer() {
    return deckPointer;
  }
}
