package org.netlykos.barcode.utilities;

public class Greeting {

  public static String greeting(String format, Object ... args) {
    return String.format(format, args);
  }

}
