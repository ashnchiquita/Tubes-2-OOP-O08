package dollar.plugin;

import javax.swing.*;

import controller.*;

public class DollarPlugin implements SystemPlugin {
  @Override
  public MainController getController(MainController controller) {
    System.out.println("Masuk dollar plugin");
    return controller;
  }

  @Override
  public JPanel displayPanel(MainController controller) {
    return null;
  }
}
