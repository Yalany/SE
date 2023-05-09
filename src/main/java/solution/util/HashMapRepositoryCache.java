package solution.util;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public final class HashMapRepositoryCache<T> implements Cache<T> {
  private final HashMap<String, T> cache = new HashMap<>();
  private final HashMap<String, Timer> timeouts = new HashMap<>();
  private final long ttl;
  private final Consumer<T> expirationCallback;

  public HashMapRepositoryCache(long ttl, Consumer<T> expirationCallback) {
    this.ttl = ttl;
    this.expirationCallback = expirationCallback;
  }

  @Override
  public T put(String id, T t) {
    cache.put(id, t);
    resetTimeout(id);
    return t;
  }

  @Override
  public boolean contains(String id) {
    return cache.containsKey(id);
  }

  @Override
  public T get(final String id) {
    assert cache.containsKey(id) : "attempt to get non-cached data from cash with id:" + id;
    resetTimeout(id);
    return cache.get(id);
  }

  private void resetTimeout(String id) {
    if (timeouts.containsKey(id))
      timeouts.remove(id).cancel();
    var timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        expirationCallback.accept(cache.remove(id));
        timeouts.remove(id).cancel();
      }
    }, ttl);
    timeouts.put(id, timer);
  }
}
