package first.plugin;

import controller.Greeter;

public class FirstImplement implements Greeter {
  @Override
  public void greetSomeone(String name) {
    System.out.println("Hello " + name + " (first implementation)");
  }
}
