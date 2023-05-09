package solution.game;

import solution.game.data.Event;
import solution.game.data.GameState;
import solution.game.data.StaticGameData;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

import java.util.function.Consumer;

public class GameController {
  private final StaticGameData staticGameData;
  private final Consumer<GameState> removeGame;
  private final GameState gameState;

  public GameController(StaticGameData staticGameData, Consumer<GameState> removeGame, GameState gameState) {
    this.staticGameData = staticGameData;
    this.removeGame = removeGame;
    this.gameState = gameState;
  }

  public GameState gameState() {
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

  private void terminateGame(String id) {
    removeGame.accept(gameState);
  }

  private Event currentEvent() {
    return staticGameData.events().get(gameState.deck().peek());
  }

  private int getResourceAmount(String resourceName) {
    assert gameState.resources().containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    return gameState.resources().get(resourceName);
  }

  private void modifyResourceAmount(String resourceName, int modification) {
    assert gameState.resources().containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    gameState.resources().put(resourceName, gameState.resources().get(resourceName) + modification);
  }

  private void shuffleEvent(String eventId, int offset) {
    gameState.deck().shuffle(eventId, offset);
  }
}
