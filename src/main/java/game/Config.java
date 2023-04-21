package game;

import com.google.gson.Gson;

public interface Config {
  String GAME_DATA_DIRECTORY = "../../resources/user_data/";
  String JSON_POSTFIX = ".json";
  int DEFAULT_DECK_SIZE = 60;
  long USER_DATA_CACHE_TIMEOUT_SECONDS = 600;

  Gson GSON = new Gson();

  static String path(final String resourceId) {
    return Config.GAME_DATA_DIRECTORY + resourceId + Config.JSON_POSTFIX;
  }
}
