package boundary;

import boundary.panel.home.HomeUI;
import boundary.widget.SideBar;
import boundary.widget.TopBar;
import boundary.widget.TopBarButton;
import boundary.enums.PanelEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainUI {
    private JPanel contentPanel;
    private JPanel contentPanelView;
    private boundary.enums.PanelEnum contentEnum;


    public MainUI(){

        contentEnum = PanelEnum.HOME;
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
        JPanel sidePanel = new SideBar(287, new Color(56,100,194), Color.WHITE);

        //TODO: TopBar logics
        TopBar topBar = new TopBar(47);
        GridBagConstraints topBarGbc = new GridBagConstraints();
        topBarGbc.gridx = 1;
        topBarGbc.gridy = 0;
        topBarGbc.weightx = 1;
        topBarGbc.weighty = 0;
        topBarGbc.fill = GridBagConstraints.BOTH;

        TopBarButton topBar1 = new TopBarButton();
        TopBarButton topBar2 = new TopBarButton();

        topBar.add(topBar1);
        topBar.add(topBar2);


        //TODO: Tidy up
        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.add(sidePanel, sidePanelGbc);
        mainPanel.add(topBar, topBarGbc);
        mainPanel.setBackground(Color.PINK);

        this.contentPanel = contentPanel;

        loadPage();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(1280,720));
        //mainWindow.setResizable(false);
        mainWindow.add(mainPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setTitle("TubesTubesTubesWANGYWANGYWANGYAAAAAAAAAAAA");

        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                if(contentEnum == PanelEnum.HOME){
                    HomeUI home = (HomeUI) contentPanelView;
                    home.stopTimer();
                }
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                super.windowDeiconified(e);
                if(contentEnum == PanelEnum.HOME){
                    HomeUI home = (HomeUI) contentPanelView;
                    home.startTimer();
                }
            }
        });
    }

    public void loadPage(){
        //TODO: loadPage function
        this.contentPanel.removeAll();

        contentPanelView = new HomeUI();
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