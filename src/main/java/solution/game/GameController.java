package solution.game;

import solution.game.gamedata.StaticGameData;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

import java.util.Map;

public class GameController {
  private final StaticGameData staticGameData;
  private final GameState gameState;
  private final Map<String, Integer> playerResources;
  private final EventDeck eventDeck;
  private final Event currentEvent;

  public GameController(StaticGameData staticGameData, GameState gameState) {
    this.staticGameData = staticGameData;
    this.gameState = gameState;
    this.playerResources = gameState.playerResources();
    this.eventDeck = gameState.eventDeck();
    this.currentEvent = staticGameData.events().get(eventDeck.peek());
  }

  public GameState getGameState() {
    return gameState;
  }

  public Response process(Request request) {
    // todo: формирование финального ответа, который будет напрямую озвучен пользователю
    // int numberOfOptions = 0;
    // String responseText;
    // String[] options = new String[numberOfOptions];
    // new Response(String responseText, String[] options)
    return null;
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
