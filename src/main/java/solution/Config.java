package solution;

public interface Config {
  String  ALICE_API_VERSION = "1.0";

  String  JSON_POSTFIX = ".json";

  int     SERVER_PORT = 8000;
  String  SERVER_CONTEXT_PATH = "/game";

  String  GAME_DATA_DIRECTORY = "../../resources/user_data/";
  int     GAME_DECK_DEFAULT_SIZE = 60;
  boolean GAME_DECK_IS_ENDLESS = true;
  String  GAME_DECK_UNUSABLE_EVENT_SLOT_NAME = "blocked";
  long    GAME_CACHE_TIMEOUT_MILLIS = 600 * 10000;
}
