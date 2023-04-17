package game;

public class Resource {
  private final String uid;
  private final String displayName;
  private final int startingAmount;
  private final int minimumAmount;

  public Resource(String uid, String displayName, int startingAmount, int minimumAmount) {
    this.uid = uid;
    this.displayName = displayName;
    this.startingAmount = startingAmount;
    this.minimumAmount = minimumAmount;
  }

  public String getUid() {
    return uid;
  }

  public String getDisplayName() {
    return displayName;
  }

  public int getStartingAmount() {
    return startingAmount;
  }

  public int getMinimumAmount() {
    return minimumAmount;
  }
}
