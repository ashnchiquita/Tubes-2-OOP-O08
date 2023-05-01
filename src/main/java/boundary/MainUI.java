package boundary;

import boundary.panel.inventaris.DaftarBarangPanel;
import boundary.panel.inventaris.DaftarBarangPanel;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private JPanel contentPanel;
    private JPanel contentPanelView;


    public MainUI(){

        JFrame mainWindow = new JFrame();
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints contentPanelGbc = new GridBagConstraints();
        contentPanelGbc.gridx = 1;
        contentPanelGbc.gridy = 1;
        contentPanelGbc.fill = GridBagConstraints.BOTH;
        contentPanelGbc.weightx = 3;
        contentPanelGbc.weighty = 30;
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);

        //TODO: SidePanel Logics
        GridBagConstraints sidePanelGbc = new GridBagConstraints();
        sidePanelGbc.gridx = 0;
        sidePanelGbc.gridy = 0;
        sidePanelGbc.gridheight = mainPanel.getHeight();
        sidePanelGbc.weightx = 0;
        sidePanelGbc.weighty = 1;
        sidePanelGbc.fill = GridBagConstraints.BOTH;


        //TODO: Tidy up
        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.setBackground(Color.PINK);

        this.contentPanel = contentPanel;

        this.loadPage();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(1280,720));
        //mainWindow.setResizable(false);
        mainWindow.add(mainPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setTitle("TubesTubesTubesWANGYWANGYWANGYAAAAAAAAAAAA");
    }


    public void loadPage(){
        //TODO: loadPage function
        this.contentPanel.removeAll();

        contentPanelView = new DaftarBarangPanel();
        //contentPanelView.setBounds(0, 0, 1000, 1000);

        this.contentPanel.add(contentPanelView, BorderLayout.CENTER);
    }

    /*
    public void setupFont(){
        //TODO: fonts
        try{
            String rootPath = System.getProperty("user.dir");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            rootPath += "/font/Rubik/static/";
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Black.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-BlackItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Bold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-BoldItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-ExtraBold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-ExtraBoldItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Italic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Light.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-LightItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Medium.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-MediumItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-SemiBold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(rootPath + "Rubik-SemiBoldItalic.ttf")));
            System.out.println("Font loading success");

        }catch (Exception e){
            System.out.println("Font loading failure: " + e.getMessage());
        }
    }
    */

    public static void main(String[] args) {
        new MainUI();
        System.out.print("Compile Success!");
    }
}