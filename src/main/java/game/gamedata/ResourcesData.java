package game.gamedata;

import game.Resource;

import java.util.Arrays;
import java.util.HashMap;

public class ResourcesData {
  private final HashMap<String, Resource> resources = new HashMap<>();

  public void loadResourceData(Resource[] resources) {
    Arrays.stream(resources).forEach(r -> this.resources.put(r.getUid(), r));
  }

  Resource getResourceDisplayName(String key) {
    return resources.get(key);
  }
}
