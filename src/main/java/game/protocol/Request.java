package game.protocol;

public class Request {
  private final String userId;
  private final String[] nlpTokens;

  public Request(String userId, String[] nlpTokens) {
    this.userId = userId;
    this.nlpTokens = nlpTokens;
  }

  public String userId() {
    return userId;
  }

  public String[] tokens() {
    return nlpTokens;
  }
}
