/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * Images
 *
 * @author ryan131
 * @since Dec 14, 2012, 4:54:03 PM
 */
public class Images {

  private static final Logger logger = Logger.getLogger(Images.class.getName());

  public static Reader read(InputStream is) throws IOException {
    return new Reader(is);
  }

  public static Reader read(ImageInputStream iis) throws IOException {
    return new Reader(iis);
  }

  public static Reader readFile(String file) throws FileNotFoundException, IOException {
    return new Reader(new FileInputStream(file));
  }

  public static Reader readFile(File file) throws FileNotFoundException, IOException {
    return new Reader(new FileInputStream(file));
  }

  public static Reader readUrl(String url) throws MalformedURLException, IOException {
    return new Reader(new URL(url).openStream());
  }

  public static Reader readUrl(URL url) throws IOException {
    return new Reader(url.openStream());
  }

  /**
   * ImageReader tries to more thing with less read from outside like file or web.
   */
  public static class Reader {

    private final byte[] byteArray;

    private Reader(InputStream is) throws IOException {
      byteArray = Streams.read(is).toByteArray();
      is.close();
    }

    private Reader(ImageInputStream iis) throws IOException {
      byteArray = Streams.read(iis).toByteArray();
      iis.close();
    }

    public byte[] toByteArray()  {
      return Arrays.copyOf(byteArray, byteArray.length);
    }

    public ByteArrayInputStream toByteArrayInputStream() {
      return new ByteArrayInputStream(toByteArray());
    }

    public BufferedImage toBufferedImage() {
      BufferedImage bufferedImage = null;
      try {
        InputStream bis = toByteArrayInputStream();
        bufferedImage = ImageIO.read(bis);
        bis.close();
      } catch (IOException ioe) {
        logger.log(Level.SEVERE, ioe.getMessage(), ioe.getCause());
      }
      return bufferedImage;
    }

    public Digests.ByteArrayDigest digest() {
      return Digests.digest(byteArray);
    }

  }

}
