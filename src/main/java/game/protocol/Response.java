package game.protocol;

public class Response {
  public final String responseText;
  public final String[] options;

  public Response(String responseText, String[] options) {
    this.responseText = responseText;
    this.options = options;
  }
}
