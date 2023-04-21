package game.gamedata;

import game.Config;
import game.GameState;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public final class GameStateCache {
  private final HashMap<String, GameState> cache = new HashMap<>();
  private final HashMap<String, Timer> timeouts = new HashMap<>();

  public void cacheGameState(final String gameId, GameState gameState) {
    cache.put(gameId, gameState);
    resetTimeout(gameId);
  }

  public GameState getGameState(final String gameId) {
    assert cache.containsKey(gameId) : "attempt to get non-cached UserData from UserDataCache";
    resetTimeout(gameId);
    return cache.get(gameId);
  }

  private void resetTimeout(final String gameId) {
    if (timeouts.containsKey(gameId))
      timeouts.remove(gameId).cancel();
    var timer = new Timer();
    timeouts.put(gameId, timer);
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        GameStateRepository.save(cache.remove(gameId));
        timeouts.remove(gameId).cancel();
      }
    }, 1000 * Config.USER_DATA_CACHE_TIMEOUT_SECONDS);
  }
}
