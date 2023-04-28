package solution.game;

import com.google.gson.annotations.SerializedName;
import solution.Config;

import java.util.stream.IntStream;

public class EventDeck {
  @SerializedName("size")
  private final int size; // size of the deck

  @SerializedName("is_endless")
  private final boolean isEndless; // if true deck will loop indefinitely and popped events will be reset to 0

  @SerializedName("events")
  private final String[] eventIds; // 0 is no event present (aka random event), number is event id in db

  @SerializedName("pointer")
  private int pointer; // points at next player event

  EventDeck(int size, boolean isEndless) {
    this.size = size;
    this.isEndless = isEndless;
    this.eventIds = new String[size];
    IntStream.range(0, size - 1).forEach(i -> this.eventIds[i] = "");
    this.pointer = 0;
  }

  // puts event in place of first random event starting from specified offset
  void shuffleIn(String eventUid, int offset) {
    if (offset < 0)
      throw new IllegalArgumentException("offset can't be lower than 0");
    if (offset >= size)
      throw new IllegalArgumentException("offset can't be greater or equal than deck size");

    var currentPointer = pointer;

    // resolving case when event wanted to be shuffled in from very top of deck
    if (offset == 0) {
      if (eventIds[pointer].equals("")) {
        eventIds[pointer] = eventUid;
        return;
      }
      movePointerBy(1);
    }

    movePointerBy(offset);

    // traversing deck in search of room for event
    while (!eventIds[pointer].equals("") && pointer != currentPointer)
      movePointerBy(1);

    // if pointers match then there is no room in deck for event
    if (pointer == currentPointer)
      return;

    eventIds[pointer] = eventUid;
    pointer = currentPointer;
  }

  void putOnTop(String eventUid) {
    movePointerBy(-1);
    eventIds[pointer] = eventUid;
  }

  String pop() {
    var event = eventIds[pointer];
    if (isEndless) eventIds[pointer] = "";
    else eventIds[pointer] = Config.GAME_DECK_UNUSABLE_EVENT_SLOT_NAME;
    movePointerBy(1);
    return event;
  }

  String peek() {
    return eventIds[pointer];
  }

  private void movePointerBy(int steps) {
    if (steps < -1)
      throw new IllegalArgumentException("pointer steps can't be lower than -1");

    if (steps == -1) {
      if (pointer + steps < 0)
        pointer += size;
      pointer += steps;
    }
    if (steps > 0) {
      if (pointer + steps >= size)
        pointer -= size;
      pointer += steps;
    }
  }
}
