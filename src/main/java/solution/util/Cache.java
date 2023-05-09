package solution.util;

public interface Cache<T> {

  T get(String id);

  T put(String id, T t);

  boolean contains(String id);

  T remove(String id);
}
