package solution.game.protocol;

public record Request(String userId,
                      String[] nlpTokens) {}
