package solution.util;

public interface Cache<T> {

  T get(String id);

  void put(String id, T t);

  boolean contains(String id);
}
