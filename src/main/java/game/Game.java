package game;

import game.gamedata.GameStateCache;
import game.protocol.Request;
import game.protocol.Response;

public class Game {
  private final GameStateCache gameStateCache = new GameStateCache();

  public Response handleRequest(Request request) {
    var gameState = gameStateCache.getGameState(request.getUserId());
    var nlpTokens = request.getNlpTokens();
    return null;
  }
}
