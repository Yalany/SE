package game.gamedata;

import game.Config;
import game.GameState;
import util.FileUtils;

public class GameStateRepository {
  public static GameState get(String gameId) {
    if (FileUtils.fileExists(Config.path(gameId)))
      return Config.GSON.fromJson(FileUtils.readFile(Config.path(gameId)), GameState.class);
    return new GameState(gameId);
  }

  void save(GameState state) {
    FileUtils.writeFile(Config.path(state.gameId), Config.GSON.toJson(this));
  }
}
