package solution.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtils {
  private FileUtils() { }

  /**
   * @param pathname in form /Users/al/foo/bar.txt
   * @return All the content of given file as one String
   */
  public static String readFile(final String pathname) {
    try {
      return new String(Files.readAllBytes(Paths.get(pathname)));
    } catch (IOException e) {
      System.err.println("malformed read operation: pathname=\"" + pathname + "\"");
      return null;
    }
  }

  /**
   * @param pathname in form /Users/al/foo/bar.txt
   * @param content String to be saved
   */
  public static void writeFile(final String pathname, final String content) {
    try {
      var out = new BufferedWriter(new FileWriter(pathname));
      out.write(content);
      out.close();
    } catch (IOException e) {
      System.err.println("malformed write operation: pathname=\"" + pathname + "\", content=\"" + content + "\"");
    }
  }

  /**
   * @param pathname in form /Users/al/foo/bar.txt
   * @return true if file does exists, false otherwise
   */
  public static boolean fileExists(final String pathname) {
    var file = new File(pathname);
    return file.exists() && !file.isDirectory();
  }

  /**
   * Removes a file or empty directory from disk
   * @param pathname in form /Users/al/foo/bar.txt
   */
  static void deleteFile(final String pathname) {
    try {
      Files.delete(Paths.get(pathname));
    } catch (IOException e) {
      System.err.println("malformed delete operation: pathname=\"" + pathname + "\"");
    }
  }
}
