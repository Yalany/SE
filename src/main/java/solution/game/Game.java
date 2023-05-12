package solution.game;

import solution.Config;
import solution.game.data.GameState;
import solution.game.data.StaticGameData;
import solution.util.*;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

import java.util.HashMap;
import java.util.Map;

public class Game {
  private final Repository<GameState> gameStateRepository;
  private final Cache<GameController> controllerCache;
  private final StaticGameData staticGameData;

  public Game() {
    gameStateRepository = new AddressedDiscRepository<>(GameState.class,
        id -> Config.GAME_DATA_DIRECTORY + id + Config.JSON_POSTFIX);
    controllerCache = new HashMapCache<>(Config.GAME_CACHE_TIMEOUT_MILLIS,
        expired -> gameStateRepository.save(expired.gameState().id(), expired.gameState()));
    staticGameData = SerializationUtils.fromJson(FileUtils.readFile(Config.GAME_STATIC_DATA_PATH), StaticGameData.class);
  }

  public Response process(Request request) {
    return getGameController(request.userId()).process(request);
  }

  private GameController getGameController(String id) {
    return controllerCache.contains(id) ?
        controllerCache.get(id) :
        gameStateRepository.contains(id) ?
            controllerCache.put(id, loadController(id)) :
            controllerCache.put(id, newController(id));
  }

  private GameController loadController(String id) {
    return new GameController(staticGameData, this::removeGame, gameStateRepository.load(id));
  }

  private GameController newController(String id) {
    return new GameController(staticGameData, this::removeGame, gameStateRepository.save(id, newGameState(id)));
  }

  private GameState newGameState(String id) {
    return new GameState(id, getStartingResources(), new EventDeck(Config.GAME_DECK_DEFAULT_SIZE));
  }

  // todo: implement starting resources template
  private Map<String, Integer> getStartingResources() {
    return new HashMap<>();
  }

  private void removeGame(GameState game) {
    controllerCache.remove(game.id());
    gameStateRepository.remove(game.id());
  }
}
