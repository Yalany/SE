package game.gamedata;

import game.Config;
import game.GameState;
import util.FileUtils;

class GameStateRepository {
  static GameState get(String gameId) {
    if (!FileUtils.fileExists(Config.path(gameId)))
      save(new GameState(gameId));
    return Config.GSON.fromJson(FileUtils.readFile(Config.path(gameId)), GameState.class);
  }

  static void save(GameState state) {
    FileUtils.writeFile(Config.path(state.gameId), Config.GSON.toJson(state));
  }
}
