package solution.util;

import java.util.function.Function;

public class AddressedDiscRepository<T> implements Repository<T> {
  private final Function<String, String> storageAddressFunction;
  private final Class<T> typeClass;

  public AddressedDiscRepository(Class<T> typeClass, Function<String, String> storageAddressFunction) {
    this.storageAddressFunction = storageAddressFunction;
    this.typeClass = typeClass;
  }

  @Override
  public T load(String id) {
    return SerializationUtils.fromJson(FileUtils.readFile(storageAddressFunction.apply(id)), typeClass);
  }

  @Override
  public T save(String id, T t) {
    assert t != null;
    FileUtils.writeFile(storageAddressFunction.apply(id), SerializationUtils.toJson(t));
    return t;
  }

  @Override
  public boolean contains(String id) {
    return FileUtils.fileExists(storageAddressFunction.apply(id));
  }

  @Override
  public T remove(String id) {
    if (!FileUtils.fileExists(storageAddressFunction.apply(id)))
      throw new IllegalArgumentException("File with id=" + id + " does not exist");
    var result = SerializationUtils.fromJson(FileUtils.readFile(storageAddressFunction.apply(id)), typeClass);
    FileUtils.deleteFile(storageAddressFunction.apply(id));
    return result;
  }
}
