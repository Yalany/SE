package solution.game.gamedata;

import solution.game.Event;
import solution.game.Resource;

import java.util.Arrays;
import java.util.HashMap;

// хранилище статичной информации об эвентах и ресурсах
public class GameData {
  private final HashMap<String, Event> events;
  private final HashMap<String, Resource> resources;

  public GameData() {
    this.events = new HashMap<>();
    this.resources = new HashMap<>();
  }

  // todo: read from file
  public GameData(Event[] events, Resource[] resources) {
    this.events = new HashMap<>();
    this.resources = new HashMap<>();
    Arrays.stream(events).forEach(e -> this.events.put(e.getId(), e));
    Arrays.stream(resources).forEach(r -> this.resources.put(r.getName(), r));
  }

  public Event getEventData(String eventUid) {
    return events.get(eventUid);
  }

  public Resource getResourceData(String uid) {
    return resources.get(uid);
  }
}
