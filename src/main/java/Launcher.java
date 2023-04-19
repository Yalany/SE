import com.sun.net.httpserver.HttpServer;
import game.Config;
import game.Game;
import game.protocol.Request;
import game.protocol.Response;
import network.protocol.AliceRequest;
import network.protocol.AliceResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
  private final static int PORT = 8000;
  private final static String CONTEXT_PATH = "/game";
  private final static String ALICE_API_VERSION = "1.0";
  private final static Game GAME = new Game();

  public static void main(final String[] args) throws IOException {
    var server = HttpServer.create(new InetSocketAddress(PORT), 0);
    server.createContext(CONTEXT_PATH, httpExchange -> {
      var response = getResponse(new String(httpExchange.getRequestBody().readAllBytes()));
      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      httpExchange.getResponseBody().write(response.getBytes());
      httpExchange.getResponseBody().close();
    });
    server.start();
  }

  private static String getResponse(final String request) {
    var aliceRequest = Config.GSON.fromJson(request, AliceRequest.class);
    return Config.GSON.toJson(
        formAliceResponse(
            GAME.handleRequest(
                new Request(aliceRequest.session.user.userId, aliceRequest.request.nlu.tokens))));
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
    result.version = ALICE_API_VERSION;
    return result;
  }
}
