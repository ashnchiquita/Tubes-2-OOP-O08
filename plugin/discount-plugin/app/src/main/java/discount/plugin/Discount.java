package discount.plugin;

import javax.swing.JPanel;

import controller.*;

public class Discount implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    System.out.println("Masuk");
    return controller;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}
