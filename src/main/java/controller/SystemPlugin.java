package controller;

import javax.swing.*;

public interface SystemPlugin {
  public MainController getController(MainController controller);

  public JPanel displayPanel(MainController controller);
}
