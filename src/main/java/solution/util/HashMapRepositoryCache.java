package solution.util;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public final class HashMapRepositoryCache<T> implements Cache<T> {
  private final HashMap<String, T> cache = new HashMap<>();
  private final HashMap<String, Timer> timeouts = new HashMap<>();

  private final Repository<T> repository;
  private final long storageDelay;

  public HashMapRepositoryCache(Repository<T> repository, long storageDelayMillis) {
    this.repository = repository;
    this.storageDelay = storageDelayMillis;
  }

  public void put(String id, T t) {
    cache.put(id, t);
    resetTimeout(id);
  }

  public boolean contains(String id) {
    return cache.containsKey(id);
  }

  public T get(final String id) {
    assert cache.containsKey(id) : "attempt to get non-cached data from cash with id " + id;
    resetTimeout(id);
    return cache.get(id);
  }

  private void resetTimeout(String id) {
    if (timeouts.containsKey(id))
      timeouts.remove(id).cancel();
    var timer = new Timer();
    timeouts.put(id, timer);
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        repository.save(id, cache.remove(id));
        timeouts.remove(id).cancel();
      }
    }, storageDelay);
  }
}
