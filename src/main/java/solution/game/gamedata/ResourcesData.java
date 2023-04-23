package solution.game.gamedata;

import solution.game.Resource;

import java.util.Arrays;
import java.util.HashMap;

// хранилище для глобальной информации о ресурсах
public class ResourcesData {
  private final HashMap<String, Resource> resources;

  public ResourcesData(Resource[] resources) {
    this.resources = new HashMap<>();
    Arrays.stream(resources).forEach(r -> this.resources.put(r.getUid(), r));
  }

  Resource getResourceData(String uid) {
    return resources.get(uid);
  }
}
