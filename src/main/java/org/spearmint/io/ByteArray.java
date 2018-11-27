/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ByteArray
 *
 * @author ryan131
 * @since Aug 14, 2013, 3:22:29 PM
 */
public class ByteArray {

  private String name;
  byte[] byteArray;

  public ByteArray(byte[] byteArray) {
    this.byteArray = byteArray;
  }

  public ByteArray(byte[] byteArray, String name) {
    this.byteArray = byteArray;
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public byte[] read() {
    return byteArray;
  }

  public ByteArrayInputStream openStream() {
    return Streams.open(byteArray);
  }

  public void writeTo(OutputStream outputStream) throws IOException {
    Streams.write(byteArray).to(outputStream);
  }

  public File writeTo(File file) throws FileNotFoundException, IOException {
    if (file.isDirectory()) {
      file = Files.append(file, name);
    }
    Streams.write(byteArray).to(file);
    return file;
  }

}
