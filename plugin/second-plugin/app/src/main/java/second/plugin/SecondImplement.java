package second.plugin;

import controller.Greeter;

public class SecondImplement implements Greeter {
  @Override
  public void greetSomeone(String name) {
    System.out.println("HALOOOOOOO " + name + " (second implementation)");
  }
}
