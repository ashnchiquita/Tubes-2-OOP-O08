package boundary;

import boundary.panel.home.HomeUI;
import boundary.panel.inventaris.DaftarBarangPanel;
import boundary.panel.laporan.LaporanPanel;
import boundary.panel.pembelian.PembelianPanel;
import boundary.widget.*;
import boundary.enums.PanelEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainUI {
    private JPanel contentPanel;
    private JPanel contentPanelView;
    private boundary.enums.PanelEnum contentEnum;
    private SideBar sidePanel;
    private TopBar topBar;
    private Integer counter = 0;
    private Map<TopBarButton, JPanel> activePanels;

    public MainUI(){
        contentEnum = PanelEnum.HOME;
        activePanels = new HashMap<>();
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
        sidePanelGbc.weighty = 0;
        sidePanelGbc.fill = GridBagConstraints.BOTH;
        sidePanel = new SideBar(287, new Color(56,100,194), Color.WHITE);

        //TODO: TopBar logics
        topBar = new TopBar(47, new Color(36, 60, 148));
        GridBagConstraints topBarGbc = new GridBagConstraints();
        topBarGbc.gridx = 1;
        topBarGbc.gridy = 0;
        topBarGbc.weightx = 0;
        topBarGbc.weighty = 0;
        topBarGbc.fill = GridBagConstraints.BOTH;

        sidePanel.addButton(new SideBarButton("Kasir.png", "Kasir"), "kasirButton");
        ((JButton) sidePanel.getComponent("kasirButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Pembayaran", new PembelianPanel(0));
            }
        } );
        sidePanel.addButton(new SideBarButton("Laporan.png", "Laporan"), "laporanButton");
        ((JButton) sidePanel.getComponent("laporanButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Laporan", new LaporanPanel());
            }
        });

        sidePanel.addButton(new SideBarButton("Member.png", "Member"), "MemberButton");
        ((JButton) sidePanel.getComponent("MemberButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Member", new JPanel());
            }
        } );
        sidePanel.addButton(new SideBarButton("Inventaris.png", "Inventaris"), "inventarisButton");
        ((JButton) sidePanel.getComponent("inventarisButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Inventaris", new DaftarBarangPanel());
            }
        } );
        sidePanel.addButton(new SideBarButton("Support.png", "Pengaturan"), "pengaturanButton");
        ((JButton) sidePanel.getComponent("pengaturanButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Pengaturan", new JPanel());
            }
        } );

        TopBarButton homeButton = (TopBarButton) topBar.getComponent("homeButton");
        activePanels.put(homeButton, new HomeUI());
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(topBar.getActive() != "homeButton"){
                    loadPage(activePanels.get(homeButton));
                }
            }
        } );

        //TODO: Tidy up
        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.add(sidePanel, sidePanelGbc);
        mainPanel.add(topBar, topBarGbc);
        mainPanel.setBackground(Color.PINK);

        this.contentPanel = contentPanel;


        loadPage(activePanels.get(homeButton));


        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(1280,720));
        mainWindow.setResizable(false);
        mainWindow.add(mainPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setTitle("Cashoria");

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

    public void addWindow(String tabLabel, JPanel panel){
        String name = "content" + counter.toString();
        TopBarTab newbutton = new TopBarTab(tabLabel);
        topBar.addButton(newbutton, name);
        activePanels.put(newbutton, panel);
        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage(activePanels.get(newbutton));
            }
        });
        newbutton.doClick();
        counter++;
    }

    public void loadPage(JPanel panel){
        //TODO: loadPage function
        contentPanel.removeAll();
        contentPanel.revalidate();

        contentPanelView = panel;
        contentPanelView.setPreferredSize(new Dimension(0, 0));

        contentPanel.add(contentPanelView, BorderLayout.CENTER);
        contentPanel.repaint();
    }


    public static void main(String[] args) {
        new MainUI();
        System.out.print("Compile Success!");
    }
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
