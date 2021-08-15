package org.netlykos.barcode.app;

import static org.netlykos.barcode.utilities.Greeting.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  private static final String GREETING_MESSAGE = "Hello World!";

  public static void main(String[] args) {
    System.out.println(greeting(GREETING_MESSAGE));
    SpringApplication.run(App.class, args);
  }

}
