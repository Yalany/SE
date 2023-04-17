package game;

import java.util.HashMap;
import java.util.Map;

public final class PlayerResources {
  private final Map<String, Integer> resources = new HashMap<>();

  public int getAmount(String resourceName) {
    assert resources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    return resources.get(resourceName);
  }

  void modifyAmount(String resourceName, int modification) {
    assert resources.containsKey(resourceName) : "Resource " + resourceName + " does not exist";
    resources.put(resourceName, resources.get(resourceName) + modification);
  }
}
