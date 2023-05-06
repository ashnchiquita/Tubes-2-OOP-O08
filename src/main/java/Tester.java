import java.io.*;
import java.net.*;
import java.util.*;

import controller.*;

public class Tester {
  public static void main(String[] args) {
    try {
      final URL plugin_1 = new File("./plugin/discount-plugin/app/build/libs/app.jar").toURI().toURL();
      final URL plugin_2 = new File("./plugin/dollar-plugin/app/build/libs/app.jar").toURI().toURL();
      ClassLoader ucl = new URLClassLoader(new URL[] { plugin_1, plugin_2 });

      ServiceLoader<SystemPlugin> pluginLoader = ServiceLoader.load(SystemPlugin.class, ucl);

      // for (Iterator<SystemPlugin> iterator = pluginLoader.iterator();
      // iterator.hasNext();) {
      // System.out.println(iterator.next());
      // }

      // pluginLoader = ServiceLoader.load(SystemPlugin.class, ucl);
      for (final SystemPlugin g : pluginLoader) {
        g.getController(null);
      }

    } catch (MalformedURLException e) {
      System.out.println(e);
    }

  }
}
