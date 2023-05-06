import boundary.MainWindow;
import boundary.constants.ResourcePath;
import controller.BasePluginInterface;
import controller.MainController;
import controller.SystemPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginLoader {
    public List<URL> readPlugin(){
        BufferedReader reader;
        List<URL> urls = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader( "/Users/alishalistya/VSCode/Tubes-2-OOP-O08/src/main/resources/data/plugins.txt"));
            String line = reader.readLine();

            while (line != null) {
                final URL plugin = new File(line).toURI().toURL();
                urls.add(plugin);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urls;
    }

    public PluginLoader(MainWindow mainWindow, MainController mainController){
        List<URL> urls = readPlugin();
        ClassLoader ucl = new URLClassLoader((URL[]) urls.toArray(new URL[urls.size()]));
        ServiceLoader<BasePluginInterface> basePluginLoader = ServiceLoader.load(BasePluginInterface.class, ucl);
        ServiceLoader<SystemPlugin> systemPluginLoader = ServiceLoader.load(SystemPlugin.class, ucl);

        for (final BasePluginInterface g : basePluginLoader) {
            g.setupMainWindow(mainWindow);
        }
        for (final SystemPlugin g : systemPluginLoader) {
            System.out.println("Pancake");
        }
    }



}
