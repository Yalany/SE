package solution.game;

public class Event {
  private final String uid;
  private final String description;
  private final GameStateModification[] immediateActions;
  private final EventReactionOption[] availableOptions;

  public Event(String uid, String description, GameStateModification[] immediateActions, EventReactionOption[] availableOptions) {
    this.uid = uid;
    this.description = description;
    this.immediateActions = immediateActions;
    this.availableOptions = availableOptions;
  }

  public String getUid() {
    return uid;
  }

  public String getDescription() {
    return description;
  }

  public GameStateModification[] getImmediateActions() {
    return immediateActions;
  }

  public EventReactionOption[] getAvailableOptions() {
    return availableOptions;
  }

  private static class EventReactionOption {
    private String buttonText;
    private String[] activationTokens;
    private GameStateModification[] optionResults;
  }
}
