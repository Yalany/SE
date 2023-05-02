package solution.game;

import solution.game.gamedata.GameData;

import java.util.Map;

public class GameStateController {
  private final GameData gameData;
  private final GameState gameState;
  private final Map<String, Integer> playerResources;
  private final EventDeck eventDeck;
  private final Event currentEvent;

  public GameStateController(GameData gameData, GameState gameState) {
    this.gameData = gameData;
    this.gameState = gameState;
    this.playerResources = gameState.getPlayerResources();
    this.eventDeck = gameState.getEventDeck();
    this.currentEvent = gameData.getEventData(eventDeck.peek());
  }

  public GameState getGameState() {
    return gameState;
  }

  private int getResourceAmount(String resourceName) {
    assert playerResources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    return playerResources.get(resourceName);
  }

  private void modifyResourceAmount(String resourceName, int modification) {
    assert playerResources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    playerResources.put(resourceName, playerResources.get(resourceName) + modification);
  }

  private void shuffleEvent(String eventId, int offset) {
    eventDeck.shuffle(eventId, offset);
  }

  private Event getCurrentEventData() {
    return currentEvent;
  }
}
