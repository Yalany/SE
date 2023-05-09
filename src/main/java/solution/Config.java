package solution;

public interface Config {
  String  ALICE_API_VERSION = "1.0";

  String  JSON_POSTFIX = ".json";

  int     SERVER_PORT = 8000;
  String  SERVER_CONTEXT_PATH = "/game";

  String  GAME_DATA_DIRECTORY = "../../resources/user_data/";
  int     GAME_DECK_DEFAULT_SIZE = 30;
  String  GAME_DECK_EMPTY_EVENT_SLOT_NAME = "";
  long    GAME_CACHE_TIMEOUT_MILLIS = 600 * 10000;
  String  GAME_STATIC_DATA_PATH = "../../resources/game_data/gamedata.json";
}
