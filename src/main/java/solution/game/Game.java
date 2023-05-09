package solution.game;

import solution.Config;
import solution.game.gamedata.StaticGameData;
import solution.util.*;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

public class Game {
  private final Repository<GameState> gameStateRepository;
  private final Cache<GameController> controllerCache;
  private final StaticGameData staticGameData;

  public Game() {
    gameStateRepository = new AddressedDiscRepository<>(GameState.class,
        id -> Config.GAME_DATA_DIRECTORY + id + Config.JSON_POSTFIX);
    controllerCache = new HashMapRepositoryCache<>(Config.GAME_CACHE_TIMEOUT_MILLIS,
        expired -> gameStateRepository.save(expired.getGameState().getGameId(), expired.getGameState()));
    staticGameData = SerializationUtils.fromJson(FileUtils.readFile(Config.GAME_STATIC_DATA_PATH), StaticGameData.class);
  }

  public Response process(Request request) {
    var controller = getGameController(request.getUserId());
    var response = controller.process(request);
    cacheGameController(controller);
    return response;
  }

  private void cacheGameController(GameController gameController) {
    controllerCache.put(gameController.getGameState().getGameId(), gameController);
  }

  private GameController getGameController(String id) {
    if (controllerCache.contains(id))
      return controllerCache.get(id);
    if (gameStateRepository.contains(id))
      return controllerCache.put(id,
          new GameController(staticGameData, gameStateRepository.load(id)));
    return controllerCache.put(id,
        new GameController(staticGameData, gameStateRepository.save(id,
            new GameState(id, Config.GAME_DECK_DEFAULT_SIZE))));
  }
}
