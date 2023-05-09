package solution.game.gamedata;

import com.google.gson.annotations.SerializedName;
import solution.game.Event;
import solution.game.Resource;

import java.util.HashMap;

public class StaticGameData {
  @SerializedName("events")
  private HashMap<String, Event> events;

  @SerializedName("resources")
  private HashMap<String, Resource> resources;

  public Event getEventData(String eventUid) {
    return events.get(eventUid);
  }

  public Resource getResourceData(String uid) {
    return resources.get(uid);
  }
}
