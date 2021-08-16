package org.netlykos.barcode.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageUtilities {

  public static final String IMAGE_FORMAT_PNG = "png";

  private ImageUtilities() {
    /* do nothing constructor */
  }

  public static byte[] toByteArray(BufferedImage bufferedImage, String format) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, format, baos);
    byte[] bytes = baos.toByteArray();
    return bytes;
  }

  public static String toBase64Encoding(BufferedImage bufferedImage, String format) throws IOException {
    byte[] bytes = toByteArray(bufferedImage, format);
    return Base64.getEncoder().encodeToString(bytes);
  }

  public static BufferedImage toBufferedImage(byte[] bytes) throws IOException {
    InputStream is = new ByteArrayInputStream(bytes);
    BufferedImage bi = ImageIO.read(is);
    return bi;
  }

}
