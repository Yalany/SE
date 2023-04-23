package solution.game;

import solution.Config;
import solution.util.AddressedDiscRepository;
import solution.util.Cache;
import solution.util.HashMapRepositoryCache;
import solution.util.Repository;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

public class Game {
  private final Repository<GameState> repository = new AddressedDiscRepository<>(Game::pathFunction, GameState.class);
  private final Cache<GameState> cache = new HashMapRepositoryCache<>(repository, Config.GAME_CACHE_TIMEOUT_MILLIS);

  public Game() {
  }

  public Response handleRequest(Request request) {
    var id = request.getUserId();
    var gameState = getGameState(id);

    var gameId = gameState.getGameId();
    var playerResources = gameState.getPlayerResources();
    var eventDeck = gameState.getEventDeck();
    var deckPointer = gameState.getDeckPointer();

    var nlpTokens = request.getNlpTokens();
    // todo: задействовать nlp модуль для выбора нужных GameStateModification в соотв. с ответом юзера

    cache.put(id, gameState);

    // todo: формирование финального ответа, который будет напрямую озвучен пользователю
    // Response(String responseText, String[] options)
    return null;
  }

  private static String pathFunction(final String resourceId) {
    return Config.GAME_DATA_DIRECTORY + resourceId + Config.JSON_POSTFIX;
  }

  private GameState getGameState(String id) {
    if (cache.contains(id)) return cache.get(id);
    if (repository.contains(id)) return repository.load(id);
    return new GameState(id, Config.GAME_DECK_DEFAULT_SIZE, Config.GAME_DECK_IS_ENDLESS);
  }
}
