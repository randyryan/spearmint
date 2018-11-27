/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Digests to make use of java.security.MessageDigest easy.
 *
 * @author ryan131
 * @since Jan 17, 2013, 4:32:59 PM
 */
public class Digests {
  private Digests() {}

  public static MessageDigest get(String algorithm) {
    try {
      return MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException nsae) {
      throw new IllegalArgumentException("No Such Algorithm: \"" + algorithm + "\".");
    }
  }

  public static MessageDigest md2() {
    return get("MD2");
  }

  public static MessageDigest md5() {
    return get("MD5");
  }

  public static MessageDigest sha1() {
    return get("SHA-1");
  }

  public static MessageDigest sha256() {
    return get("SHA-256");
  }

  public static MessageDigest sha384() {
    return get("SHA-384");
  }

  public static MessageDigest sha512() {
    return get("SHA-512");
  }

  public static MessageDigest update(MessageDigest digest, String string) {
    digest.update(string.getBytes(Charset.forName("UTF-8")));
    return digest;
  }

  public static MessageDigest update(MessageDigest digest, byte[] byteArray) {
    digest.update(byteArray);
    return digest;
  }

  public static MessageDigest update(MessageDigest digest, InputStream inputStream)
      throws IOException {
    int bytesRead;
    byte[] buffer = new byte[4096];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      digest.update(buffer, 0, bytesRead);
    }
    return digest;
  }

  public static MessageDigest update(MessageDigest digest, URL url) throws IOException {
    return update(digest, Streams.open(url));
  }

  public static MessageDigest update(MessageDigest digest, File file) throws IOException {
    return update(digest, Streams.buffer(Streams.open(file)));
  }

  public static ByteArrayDigest digest(String string) {
    return digest(string.getBytes(Charset.forName("UTF-8")));
  }

  public static ByteArrayDigest digest(byte[] byteArray) {
    return new ByteArrayDigest(byteArray);
  }

  public static InputStreamDigest digest(InputStream inputStream) throws IOException {
    return new InputStreamDigest(inputStream);
  }

  public static InputStreamDigest digest(URL url) throws IOException {
    return digest(Streams.open(url));
  }

  public static InputStreamDigest digest(File file) throws FileNotFoundException, IOException {
    return digest(Streams.buffer(Streams.open(file)));
  }

  public static class ByteArrayDigest {

    private byte[] byteArray;

    private ByteArrayDigest(byte[] byteArray) {
      this.byteArray = byteArray;
    }

    public Digest md2() {
      return new Digest(Digests.md2().digest(byteArray));
    }

    public Digest md5() {
      return new Digest(Digests.md5().digest(byteArray));
    }

    public Digest sha1() {
      return new Digest(Digests.sha1().digest(byteArray));
    }

    public Digest sha256() {
      return new Digest(Digests.sha256().digest(byteArray));
    }

    public Digest sha384() {
      return new Digest(Digests.sha384().digest(byteArray));
    }

    public Digest sha512() {
      return new Digest(Digests.sha512().digest(byteArray));
    }

  }

  public static class InputStreamDigest {

    private InputStream inputStream;

    private InputStreamDigest(InputStream inputStream) {
      this.inputStream = inputStream;
    }

    public Digest md2() throws IOException {
      return new Digest(update(Digests.md2(), inputStream).digest());
    }

    public Digest md5() throws IOException {
      return new Digest(update(Digests.md5(), inputStream).digest());
    }

    public Digest sha1() throws IOException {
      return new Digest(update(Digests.sha1(), inputStream).digest());
    }

    public Digest sha256() throws IOException {
      return new Digest(update(Digests.sha256(), inputStream).digest());
    }

    public Digest sha384() throws IOException {
      return new Digest(update(Digests.sha384(), inputStream).digest());
    }

    public Digest sha512() throws IOException {
      return new Digest(update(Digests.sha512(), inputStream).digest());
    }

  }

  public static class Digest {

    private byte[] digest;

    private Digest(byte[] digest) {
      this.digest = digest;
    }

    public byte[] get() {
      return digest;
    }

    public String getHexString() {
      return BaseEncoding.encode(digest).toBase16();
    }

    @Override
    public String toString() {
      return getHexString();
    }

  }

}
