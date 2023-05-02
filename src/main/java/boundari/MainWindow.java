package boundari;

import boundari.constants.Colors;
import boundari.panel.home.HomeUI;
import boundari.panel.inventaris.DaftarBarangPanel;
import boundari.panel.laporan.LaporanPanel;
import boundari.panel.pembelian.PembelianPanel;
import boundari.widget.*;
import boundari.enums.PanelEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private JPanel contentPanel;
    private JPanel contentPanelView;
    private boundari.enums.PanelEnum contentEnum;
    private SideBar sidePanel;
    private TopBar topBar;
    private Integer counter = 0;
    private Map<String, JPanel> activePanels;

    public MainWindow(){
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e){
            System.out.println(e.getMessage()+ " failed to load, proceeding with normal skin");
        }
        contentEnum = PanelEnum.HOME;
        activePanels = new HashMap<>();
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints contentPanelGbc = new GridBagConstraints();
        contentPanelGbc.gridx = 1;
        contentPanelGbc.gridy = 1;
        contentPanelGbc.fill = GridBagConstraints.BOTH;
        contentPanelGbc.weightx = 3;
        contentPanelGbc.weighty = 30;
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Colors.BLACK);

        //TODO: SidePanel Logics
        GridBagConstraints sidePanelGbc = new GridBagConstraints();
        sidePanelGbc.gridx = 0;
        sidePanelGbc.gridy = 0;
        sidePanelGbc.gridheight = mainPanel.getHeight();
        sidePanelGbc.weightx = 0;
        sidePanelGbc.weighty = 0;
        sidePanelGbc.fill = GridBagConstraints.BOTH;
        sidePanel = new SideBar(287, Colors.LIGHT_BLUE, Color.WHITE);

        //TODO: TopBar logics
        topBar = new TopBar(47, Colors.DARK_BLUE);
        GridBagConstraints topBarGbc = new GridBagConstraints();
        topBarGbc.gridx = 1;
        topBarGbc.gridy = 0;
        topBarGbc.weightx = 0;
        topBarGbc.weighty = 0;
        topBarGbc.fill = GridBagConstraints.BOTH;

        sidePanel.addButton(new SideBarButton("/kasir.png", "Kasir"), "kasirButton");
        ((JButton) sidePanel.getComponent("kasirButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Pembayaran", new PembelianPanel(0));
            }
        } );
        sidePanel.addButton(new SideBarButton("/laporan.png", "Laporan"), "laporanButton");
        ((JButton) sidePanel.getComponent("laporanButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Laporan", new LaporanPanel());
            }
        });

        sidePanel.addButton(new SideBarButton("/member.png", "Member"), "MemberButton");
        ((JButton) sidePanel.getComponent("MemberButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Member", new JPanel());
            }
        } );
        sidePanel.addButton(new SideBarButton("/inventaris.png", "Inventaris"), "inventarisButton");
        ((JButton) sidePanel.getComponent("inventarisButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Inventaris", new DaftarBarangPanel());
            }
        } );
        sidePanel.addButton(new SideBarButton("/pengaturan.png", "Pengaturan"), "pengaturanButton");
        ((JButton) sidePanel.getComponent("pengaturanButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //activePanels.containsValue(JPanel.class);
                addWindow("Pengaturan", new JPanel());
            }
        } );

        TopBarButton homeButton = (TopBarButton) topBar.getComponent("homeButton");
        activePanels.put("homeButton", new HomeUI());
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(topBar.getActive() != "homeButton"){
                    loadPage(activePanels.get("homeButton"));
                }
            }
        } );

        //TODO: Tidy up
        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.add(sidePanel, sidePanelGbc);
        mainPanel.add(topBar, topBarGbc);
        mainPanel.setBackground(Colors.PEMBELIAN_RED);

        this.contentPanel = contentPanel;


        loadPage(activePanels.get("homeButton"));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280,720));
        setResizable(false);
        add(mainPanel);
        pack();
        setVisible(true);
        setTitle("Cashoria");

        addWindowListener(new WindowAdapter() {
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
        activePanels.put(name, panel);
        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage(activePanels.get(name));
            }
        });
        newbutton.onClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((TopBarButton) topBar.getComponent("homeButton")).doClick();
                topBar.removeButton(name);
                activePanels.remove(name);
            }
        });
        newbutton.doClick();
        counter++;
    }

    public void loadPage(JPanel panel){
        contentPanel.removeAll();
        contentPanel.revalidate();

        contentPanelView = panel;
        contentPanelView.setPreferredSize(new Dimension(0, 0));

        contentPanel.add(contentPanelView, BorderLayout.CENTER);
        contentPanel.repaint();
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
