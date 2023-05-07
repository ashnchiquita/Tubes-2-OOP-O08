import boundary.MainWindow;
import controller.MainController;
import util.PluginLoader;

public class Main {
  public static void main(String[] args) {
    MainController mainController = new MainController();
    MainWindow mainWindow = new MainWindow(mainController);
    new PluginLoader(mainWindow, mainController);
    //mainWindow.initUI();

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        mainWindow.pack();
        mainWindow.setVisible(true);
      }
    });
  }

}
