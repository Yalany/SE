package game.gamedata;

import game.Event;

import java.util.Arrays;
import java.util.HashMap;

public class EventsData {
  private final HashMap<String, Event> events = new HashMap<>();

  public void loadEventsData(Event[] events) {
    Arrays.stream(events).forEach(e -> this.events.put(e.getUid(), e));
  }

  public Event getEventData(String eventUid) {
    return events.get(eventUid);
  }
}
