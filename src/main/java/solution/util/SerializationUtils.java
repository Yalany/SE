package solution.util;

import com.google.gson.Gson;

public class SerializationUtils {
  private static final Gson GSON = new Gson();

  public static <T> T fromJson(String json, Class<T> classOfT) {
    return GSON.fromJson(json, classOfT);
  }

  public static String toJson(Object src) {
    return GSON.toJson(src);
  }
}
