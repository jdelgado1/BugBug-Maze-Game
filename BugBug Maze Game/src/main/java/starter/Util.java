package starter;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Util {

  /**
   * Read an image file into an Image object.
   *
   * @param address relative path to an image file inside "resources" folder.
   * @return an Image object.
   */
  public static Image getImage(String address) {
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    String path = Objects.requireNonNull(url)
        .getPath().replace("%20", " ").replace("classes", "resources");
    Path dataFile = Paths.get(path, address);
    // FIXME on Windows! Instead of the statement above, use this:
    // Path dataFile = Paths.get(path.substring(1), address);
    Image image = null;
    try {
      image = ImageIO.read(dataFile.toFile());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
