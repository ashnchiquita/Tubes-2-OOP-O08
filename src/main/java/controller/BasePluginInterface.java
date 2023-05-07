package controller;

import javax.swing.*;
import boundary.MainWindow;

import java.net.URL;
import java.util.List;

public interface BasePluginInterface {

    public void setupMainWindow(MainWindow mainWindow);
    public JPanel loadPanel(MainController mainController);
    public void addURL(List<URL> urls);
    public void loadPlugin(MainWindow mainWindow);
}