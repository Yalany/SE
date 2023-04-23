package solution.util;

public interface Repository<T> {

  T load(String id);

  void save(String id, T t);

  boolean contains(String id);
}
