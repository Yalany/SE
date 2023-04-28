package solution.game;

public class Event {
  private final String id;
  private final String description;
  private final GameStateModification[] immediateActions;
  private final EventReactionOption[] availableOptions;

  public Event(String id, String description, GameStateModification[] immediateActions, EventReactionOption[] availableOptions) {
    this.id = id;
    this.description = description;
    this.immediateActions = immediateActions;
    this.availableOptions = availableOptions;
  }

  public String getId() {
    return id;
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
