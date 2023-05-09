package solution.game;

import com.google.gson.annotations.SerializedName;
import solution.Config;

import java.util.stream.IntStream;

public class EventDeck {
  /**
   * size of the deck
   */
  @SerializedName("size")
  private final int size;

  /**
   * 0 is no event present (aka random event), number is event id in db
   */
  @SerializedName("events")
  private final String[] eventIds;

  /**
   * points at next player event
   */
  @SerializedName("pointer")
  private int pointer;

  EventDeck(int size) {
    this.size = size;
    this.eventIds = new String[size];
    IntStream.range(0, size - 1).forEach(i -> this.eventIds[i] = Config.GAME_DECK_EMPTY_EVENT_SLOT_NAME);
    this.pointer = 0;
  }

  /**
   * puts event in place of first empty event slot starting from specified offset from current pointer position
   * @param eventId event to be placed in deck
   * @param offset offset from current pointer position
   */
  void shuffle(String eventId, int offset) {
    assert 0 <= offset && offset <= cardsLeft() : "offset can't be lower than 0 or greater or equal than deck size";

    var tempPointer = pointer + offset;
    while (tempPointer < size) {
      if (eventIds[tempPointer].equals(Config.GAME_DECK_EMPTY_EVENT_SLOT_NAME)) {
        eventIds[tempPointer] = eventId;
        return;
      }
      tempPointer++;
    }
  }

  String peek() {
    return eventIds[pointer];
  }

  String pop() {
    return eventIds[pointer++];
  }

  boolean isEmpty() {
    return pointer == size;
  }

  int cardsLeft() {
    return size - pointer;
  }
}
