/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;

import javax.imageio.stream.ImageInputStream;

/**
 * InputStream/OutputStream Utility
 *
 * InputStream                        (java.io)
 *     ByteArrayInputStream           (java.io)
 *     FileInputStream                (java.io)
 *     FilterInputStream              (java.io)
 *         BufferedInputStream        (java.io)
 *         CheckedInputStream         (java.util.zip)
 *         DataInputStream            (java.io)
 *         DeflaterInputStream        (java.util.zip)
 *         DigestInputStream          (java.security)
 *         InflaterInputStream        (java.util.zip)
 *         LineNumberInputStream      (java.io)
 *         ProgressMonitorInputStream (javax.swing)
 *         PushbackInputStream        (java.io)
 *     ObjectInputStream              (java.io)
 *     PipedInputStream               (java.io)
 *     SequenceInputStream            (java.io)
 *     StringBufferInputStream        (java.io)
 * OutputStream                       (java.io)
 *     ByteArrayOutputStream          (java.io)
 *     FileOutputStream               (java.io)
 *
 * @author ryan131
 * @since Dec 14, 2012, 10:17:53 AM
 */
public class Streams {
  private Streams() {}

  private static final int BUFFER_SIZE = 4096;

  // Decorate

  public static BufferedInputStream buffer(InputStream inputStream) {
    return new BufferedInputStream(inputStream);
  }

  public static BufferedOutputStream buffer(OutputStream outputStream) {
    return new BufferedOutputStream(outputStream);
  }

  public static DigestInputStream digest(InputStream inputStream, MessageDigest messageDigest) {
    return new DigestInputStream(inputStream, messageDigest);
  }

  public static DigestOutputStream digest(OutputStream outputStream, MessageDigest messageDigest) {
    return new DigestOutputStream(outputStream, messageDigest);
  }

  public static Digests.InputStreamDigest digest(InputStream inputStream) throws IOException {
    return Digests.digest(inputStream);
  }

  // Open

  public static ByteArrayInputStream open(byte[] byteArray) {
    return new ByteArrayInputStream(byteArray);
  }

  public static InputStream open(URL url) throws IOException {
    return url.openConnection().getInputStream();
  }

  public static FileInputStream open(File file) throws FileNotFoundException {
    return new FileInputStream(file);
  }

  // Read

  public static InputStreamReader read(byte[] byteArray) {
    return new InputStreamReader(open(byteArray));
  }

  public static InputStreamReader read(URL url) throws IOException {
    return new InputStreamReader(open(url));
  }

  public static InputStreamReader readURL(String url) throws MalformedURLException, IOException {
    return new InputStreamReader(open(new URL(url)));
  }

  public static InputStreamReader read(File file) throws FileNotFoundException {
    return new InputStreamReader(open(file));
  }

  public static InputStreamReader readFile(String file) throws FileNotFoundException {
    return new InputStreamReader(open(new File(file)));
  }

  public static InputStreamReader read(InputStream inputStream) {
    return new InputStreamReader(inputStream);
  }

  public static InputStreamReader read(ImageInputStream imageInputStream) {
    return new InputStreamReader(new InputStreamAdapter(imageInputStream));
  }

  public static <IS extends InputStream> IS readFully(IS inputStream) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    while (inputStream.read(buffer) != -1);
    return inputStream;
  }

  public static ByteArrayWriter write(byte[] byteArray) {
    return new ByteArrayWriter(byteArray);
  }

  public static ByteArrayWriter write(InputStream inputStream) throws IOException {
    return new ByteArrayWriter(read(inputStream).toByteArray());
  }

  public static ByteArrayWriter writeURL(String url) throws IOException {
    return new ByteArrayWriter(readURL(url).toByteArray());
  }

  public static class InputStreamReader {

    private InputStream inputStream;

    private InputStreamReader(InputStream inputStream) {
      this.inputStream = inputStream;
    }

    public void to(OutputStream outputStream) throws IOException {
      int bRead;
      byte[] buffer = new byte[BUFFER_SIZE];
      while ((bRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
        outputStream.write(buffer, 0, bRead);
      }
    }

    public byte[] toByteArray() throws IOException {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      to(outputStream);
      return outputStream.toByteArray();
    }

    public ByteArrayInputStream toByteArrayInputStream() throws IOException {
      return new ByteArrayInputStream(toByteArray());
    }

  }

  public static class InputStreamAdapter extends InputStream {

    private ImageInputStream inputStream;

    private InputStreamAdapter(ImageInputStream inputStream) {
      this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
      return inputStream.read();
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
      return inputStream.read(b, off, len);
    }

  }

  public static class ByteArrayWriter {

    private byte[] byteArray;

    private ByteArrayWriter(byte[] byteArray) {
      this.byteArray = byteArray;
    }

    public void to(OutputStream outputStream) throws IOException {
      outputStream.write(byteArray);
      outputStream.flush();
    }

    public void to(File file) throws FileNotFoundException, IOException {
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(byteArray);
      outputStream.flush();
      outputStream.close();
    }

    public void to(String file) throws FileNotFoundException, IOException {
      to(new File(file));
    }

  }

}
