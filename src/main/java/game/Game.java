package game;

import game.gamedata.GameStateRepository;
import game.protocol.Request;
import game.protocol.Response;

public class Game {
  public Response handleRequest(Request request) {
    var gameState = GameStateRepository.get(request.getUserId());
    var nlpTokens = request.getNlpTokens();
    return null;
  }
}
