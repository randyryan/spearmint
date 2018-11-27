/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * BaseEncoding
 *
 * @author ryan131
 * @since Jan 21, 2013, 12:09:54 AM
 */
public class BaseEncoding {

  public static final char[] BASE16 = "0123456789abcdef".toCharArray();

  public static final char[] BASE31 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ23456".toCharArray();

  public static final char[] BASE31HEX = "0123456789abcdefghijklmnopqrstu".toCharArray();

  public static final char[] BASE32 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();

  public static final char[] BASE32HEX = "0123456789abcdefghijklmnopqrstuv".toCharArray();

  public static final char[] BASE58 =
      "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz123456789".toCharArray();

  public static final char[] BASE64 =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

  public static final char[] BASE64URL =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

  public static BaseEncoder encode(byte[] byteArray) {
    return new BaseEncoder(byteArray);
  }

  public static BaseEncoder encode(InputStream inputstream) throws IOException {
    return new BaseEncoder(Streams.read(inputstream).toByteArray());
  }

  public static BaseDecoder decode(String string) {
    return new BaseDecoder(string);
  }

  public static class BaseEncoder {

    private final byte[] byteArray;

    private BaseEncoder(byte[] byteArray) {
      this.byteArray = byteArray;
    }

    public String toBase16() {
      char[] charArray = new char[byteArray.length << 1];
      for (int byteIndex = 0, charIndex = 0; byteIndex < byteArray.length; byteIndex++) {
        int current = byteArray[byteIndex] & 0xff;
        charArray[charIndex++] = BASE16[current >>> 4];
        charArray[charIndex++] = BASE16[current & 0xf];
      }
      return new String(charArray);
    }

    public String toBase31() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public String toBase32() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public String toBase32Hex() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public String toBase58() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public String toBase64() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public String toBase64Url() {
      throw new UnsupportedOperationException("To be implemented");
    }

  }

  public static class BaseDecoder {

    private BaseDecoder(String string) {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase16() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase31() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase32() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase32Hex() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase58() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase64() {
      throw new UnsupportedOperationException("To be implemented");
    }

    public byte[] byBase64Url() {
      throw new UnsupportedOperationException("To be implemented");
    }

  }

}
