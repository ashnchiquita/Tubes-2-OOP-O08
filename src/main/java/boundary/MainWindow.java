package boundary;

import boundary.constants.Colors;
import boundary.observer.tab.TabEvent;
import boundary.observer.tab.TabListener;
import boundary.panel.home.HomeUI;
import boundary.panel.inventaris.InventarisPanel;
import boundary.panel.kasir.KasirPanel;
import boundary.panel.laporan.LaporanPanel;
import boundary.panel.member.MemberPanel;
import boundary.widget.*;
import boundary.enums.PanelEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame implements TabListener {
    private JPanel contentPanel;
    private JPanel contentPanelView;
    private boundary.enums.PanelEnum contentEnum;
    private SideBar sidePanel;
    private TopBar topBar;
    private Integer counter = 0;
    private Map<String, JPanel> activePanels;

    public MainWindow(){
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e){
            System.out.println(e.getMessage()+ "Skin failed to load, proceeding with normal skin");
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
                addWindow("Pembayaran", new KasirPanel(), PanelEnum.KASIR);
            }
        } );
        sidePanel.addButton(new SideBarButton("/laporan.png", "Laporan"), "laporanButton").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!topBar.hasType(PanelEnum.LAPORAN))
                    addWindow("Laporan", new LaporanPanel(), PanelEnum.LAPORAN);
                else{
                    TopBarTab tab = ((TopBarTab) topBar.getComponent(topBar.getTabsWithType(PanelEnum.LAPORAN).get(0)));
                    if(!tab.getStatus())
                        tab.doClick();
                }
            }
        });

        sidePanel.addButton(new SideBarButton("/member.png", "Member"), "MemberButton").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!topBar.hasType(PanelEnum.MEMBER))
                    addWindow("Member", new MemberPanel(), PanelEnum.MEMBER);
                else{
                    TopBarTab tab = ((TopBarTab) topBar.getComponent(topBar.getTabsWithType(PanelEnum.MEMBER).get(0)));
                    if(!tab.getStatus())
                        tab.doClick();
                }
            }
        } );
        sidePanel.addButton(new SideBarButton("/inventaris.png", "Inventaris"), "inventarisButton").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!topBar.hasType(PanelEnum.INVENTARIS))
                    addWindow("Inventaris", new InventarisPanel(), PanelEnum.INVENTARIS);
                else{
                    TopBarTab tab = ((TopBarTab) topBar.getComponent(topBar.getTabsWithType(PanelEnum.INVENTARIS).get(0)));
                    if(!tab.getStatus())
                        tab.doClick();
                }
            }
        } );
        sidePanel.addButton(new SideBarButton("/pengaturan.png", "Pengaturan"), "pengaturanButton").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //activePanels.containsValue(JPanel.class);
                if(!topBar.hasType(PanelEnum.PENGATURAN))
                    addWindow("Pengaturan", new TabPanel(), PanelEnum.PENGATURAN);
                else{
                    TopBarTab tab = ((TopBarTab) topBar.getComponent(topBar.getTabsWithType(PanelEnum.PENGATURAN).get(0)));
                    if(!tab.getStatus())
                        tab.doClick();
                }
            }
        } );

        TopBarButton homeButton = (TopBarButton) topBar.getComponent("homeButton");
        activePanels.put("homeButton", new HomeUI());
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(topBar.getActive() != "homeButton"){
                    loadPage(activePanels.get("homeButton"));
                    contentEnum = PanelEnum.HOME;
                }
            }
        } );

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

    public void addWindow(String tabLabel, TabPanel panel, PanelEnum type){
        String name = "content" + counter.toString();
        TopBarTab newbutton = new TopBarTab(tabLabel, name);
        topBar.addTab(newbutton, name, type);
        activePanels.put(name, panel);
        panel.assignTab(newbutton);
        newbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage(activePanels.get(name));
                contentEnum = type;
            }
        });
        newbutton.getObserver().addListener(this);
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

    @Override
    public void closeTab(TabEvent e, String tabname) {
        System.out.println(tabname);
        ((TopBarButton) topBar.getComponent("homeButton")).doClick();
        activePanels.remove(tabname);
    }
}