package solution.game;

public class Resource {
  private final String name;
  private final int startingAmount;
  private final int minimumAmount;

  public Resource(String name, int startingAmount, int minimumAmount) {
    this.name = name;
    this.startingAmount = startingAmount;
    this.minimumAmount = minimumAmount;
  }

  public String getName() {
    return name;
  }

  public int getStartingAmount() {
    return startingAmount;
  }

  public int getMinimumAmount() {
    return minimumAmount;
  }
}
