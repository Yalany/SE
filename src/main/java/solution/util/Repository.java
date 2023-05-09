package solution.util;

public interface Repository<T> {

  T load(String id);

  T save(String id, T t);

  boolean contains(String id);

  void remove(String id);
}
