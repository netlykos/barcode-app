package org.netlykos.barcode.config;

public class LocalConfigApplication {

  public static void main(String [] args) {
    // @formatter:off
    String [] arguments = {
      "--spring.cloud.config.server.git.uri=file:///${user.home}/barcode-config-repo"
    };
    // @formatter:on
    ConfigApplication.main(arguments);
  }

}
