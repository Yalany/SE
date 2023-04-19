package game;

import com.google.gson.annotations.SerializedName;

public class GameState {
  @SerializedName("game_id")
  public final String gameId;

  @SerializedName("player_resources")
  private PlayerResources playerResources;

  @SerializedName("event_deck")
  private EventDeck eventDeck;

  @SerializedName("deck_pointer")
  private int deckPointer;

  public GameState(final String gameId) {
    this.gameId = gameId;
    this.playerResources = new PlayerResources();
    this.eventDeck = new EventDeck(Config.DEFAULT_DECK_SIZE, true);
    this.deckPointer = 0;
  }
}
