package solution.game;

import solution.Config;
import solution.util.AddressedDiscRepository;
import solution.util.Cache;
import solution.util.HashMapRepositoryCache;
import solution.util.Repository;
import solution.game.protocol.Request;
import solution.game.protocol.Response;

public class Game {
  private final Repository<GameState> repository;
  private final Cache<GameState> cache;

  public Game() {
    repository = new AddressedDiscRepository<>(s -> Config.GAME_DATA_DIRECTORY + s + Config.JSON_POSTFIX, GameState.class);
    cache = new HashMapRepositoryCache<>(repository, Config.GAME_CACHE_TIMEOUT_MILLIS);
  }

  public Response handleRequest(Request request) {
    var id = request.getUserId();
    var gameState = getGameState(id);

    var gameId = gameState.getGameId();
    var eventDeck = gameState.getEventDeck();

    var nlpTokens = request.getNlpTokens();
    // todo: задействовать nlp модуль для выбора нужных GameStateModification в соотв. с ответом юзера

    cache.put(id, gameState);

    // todo: формирование финального ответа, который будет напрямую озвучен пользователю
    // Response(String responseText, String[] options)
    return null;
  }

  private GameState getGameState(String id) {
    if (cache.contains(id)) return cache.get(id);
    if (repository.contains(id)) return repository.load(id);
    return new GameState(id, Config.GAME_DECK_DEFAULT_SIZE, Config.GAME_DECK_IS_ENDLESS);
  }
}
