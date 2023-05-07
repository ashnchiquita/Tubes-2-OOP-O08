import boundary.MainWindow;
import controller.BasePluginInterface;
import controller.MainController;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class Main {
  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
    MainController mainController = new MainController();
    new PluginLoader(mainWindow, mainController);

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        mainWindow.pack();
        mainWindow.setVisible(true);
      }
    });
  }

}
