package controller;

import javax.swing.*;
import boundary.MainWindow;

public interface BasePluginInterface {

    public void setupMainWindow(MainWindow mainWindow);
    public JPanel loadPanel(MainController mainController);
}