package solution.game;

import solution.Config;
import solution.game.gamedata.GameData;
import solution.util.AddressedDiscRepository;
import solution.util.Cache;
import solution.util.HashMapRepositoryCache;
import solution.util.Repository;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

public class Game {
  private final Repository<GameState> gameStateRepository;
  private final Cache<GameState> gameStateCache;
  private final GameData gameData;

  public Game() {
    gameStateRepository = new AddressedDiscRepository<>(s -> Config.GAME_DATA_DIRECTORY + s + Config.JSON_POSTFIX, GameState.class);
    gameStateCache = new HashMapRepositoryCache<>(gameStateRepository, Config.GAME_CACHE_TIMEOUT_MILLIS);
    gameData = new GameData();
  }

  public Response handleRequest(Request request) {
    var controller = new GameStateController(gameData, getGameState(request.getUserId()));

    var nlpTokens = request.getNlpTokens();
    // todo: задействовать nlp модуль для выбора нужных GameStateModification в соотв. с ответом юзера

    gameStateCache.put(controller.getGameState().getGameId(), controller.getGameState());

    // todo: формирование финального ответа, который будет напрямую озвучен пользователю
    // Response(String responseText, String[] options)
    return null;
  }

  private GameState getGameState(String id) {
    if (gameStateCache.contains(id)) return gameStateCache.get(id);
    if (gameStateRepository.contains(id)) return gameStateRepository.load(id);
    return new GameState(id, Config.GAME_DECK_DEFAULT_SIZE);
  }
}
