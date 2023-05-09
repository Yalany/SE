package solution.game;

import solution.Config;
import solution.game.gamedata.StaticGameData;
import solution.util.*;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

import java.util.HashMap;

public class Game {
  private final Repository<GameState> gameStateRepository;
  private final Cache<GameController> controllerCache;
  private final StaticGameData staticGameData;

  public Game() {
    gameStateRepository = new AddressedDiscRepository<>(GameState.class,
        id -> Config.GAME_DATA_DIRECTORY + id + Config.JSON_POSTFIX);
    controllerCache = new HashMapCache<>(Config.GAME_CACHE_TIMEOUT_MILLIS,
        expired -> gameStateRepository.save(expired.getGameState().gameId(), expired.getGameState()));
    staticGameData = SerializationUtils.fromJson(FileUtils.readFile(Config.GAME_STATIC_DATA_PATH), StaticGameData.class);
  }

  public Response process(Request request) {
    return getGameController(request.userId()).process(request);
  }

  private GameController getGameController(String id) {
    if (controllerCache.contains(id))
      return controllerCache.get(id);
    if (gameStateRepository.contains(id))
      return controllerCache.put(id,
          new GameController(staticGameData, gameStateRepository.load(id), this::removeGame));
    return controllerCache.put(id,
        new GameController(staticGameData, gameStateRepository.save(id,
            new GameState(id,
                // todo: implement starting resources template
                new HashMap<>(),
                new EventDeck(Config.GAME_DECK_DEFAULT_SIZE))), this::removeGame));
  }

  private void removeGame(GameState game) {
    controllerCache.remove(game.gameId());
    gameStateRepository.remove(game.gameId());
  }
}
