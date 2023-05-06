import java.io.*;
import java.net.*;
import java.util.*;

import controller.Greeter;

public class Tester {
  public static void main(String[] args) {
    try {
      final URL plugin_1 = new File("./first-plugin/app/build/libs/app.jar").toURI().toURL();
      final URL plugin_2 = new File("./second-plugin/app/build/libs/app.jar").toURI().toURL();
      ClassLoader ucl = new URLClassLoader(new URL[] { plugin_1, plugin_2 });

      ServiceLoader<Greeter> greeterLoader = ServiceLoader.load(Greeter.class, ucl);

      for (Iterator<Greeter> iterator = greeterLoader.iterator(); iterator.hasNext();) {
        System.out.println(iterator.next());
      }

      greeterLoader = ServiceLoader.load(Greeter.class, ucl);
      for (final Greeter g : greeterLoader) {
        g.greetSomeone("James");
      }

    } catch (MalformedURLException e) {
      System.out.println(e);
    }

  }
}
