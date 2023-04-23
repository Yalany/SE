package solution.game.gamedata;

import solution.game.Event;

import java.util.Arrays;
import java.util.HashMap;

// хранилище для глобальной информации об эвентах
public class EventsData {
  private final HashMap<String, Event> events = new HashMap<>();

  public EventsData(Event[] events) {
    Arrays.stream(events).forEach(e -> this.events.put(e.getUid(), e));
  }

  public Event getEventData(String eventUid) {
    return events.get(eventUid);
  }
}
