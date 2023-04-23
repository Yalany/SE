package solution;

import com.sun.net.httpserver.HttpServer;
import solution.game.Game;
import solution.game.protocol.Request;
import solution.game.protocol.Response;
import solution.network.protocol.AliceRequest;
import solution.network.protocol.AliceResponse;
import solution.util.SerializationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
  private final static Game GAME = new Game();

  public static void main(final String[] args) throws IOException {
    var server = HttpServer.create(new InetSocketAddress(Config.SERVER_PORT), 0);
    server.createContext(Config.SERVER_CONTEXT_PATH, httpExchange -> {
      var response = getResponse(new String(httpExchange.getRequestBody().readAllBytes()));
      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      httpExchange.getResponseBody().write(response.getBytes());
      httpExchange.getResponseBody().close();
    });
    server.start();
  }

  private static String getResponse(final String request) {
    var aliceRequest = SerializationUtils.GSON.fromJson(request, AliceRequest.class);
    var gameRequest = new Request(aliceRequest.session.user.userId, aliceRequest.request.nlu.tokens);
    var gameResponse = GAME.handleRequest(gameRequest);
    var aliceResponse = formAliceResponse(gameResponse);
    return SerializationUtils.GSON.toJson(aliceResponse);
  }

  private static AliceResponse formAliceResponse(final Response gameResponse) {
    var result = new AliceResponse();
    result.response.text = gameResponse.responseText;
    result.response.tts = gameResponse.responseText;
    result.response.endSession = false;
    result.response.buttons = new AliceResponse.Response.Button[gameResponse.options.length];
    for (int i = 0; i < gameResponse.options.length; i++) {
      result.response.buttons[i].title = gameResponse.options[i];
      result.response.buttons[i].hide = true;
    }
    result.version = Config.ALICE_API_VERSION;
    return result;
  }
}
