package boundary;

import boundary.constants.Colors;
import boundary.constants.PanelCode;
import boundary.observer.tab.TabEvent;
import boundary.observer.tab.TabListener;
import boundary.panel.home.HomePanel;
import boundary.panel.inventaris.InventarisPanel;
import boundary.panel.kasir.KasirPanel;
import boundary.panel.laporan.LaporanPanel;
import boundary.panel.member.MemberPanel;
import boundary.panel.settings.Settings;
import boundary.widget.*;
import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame implements TabListener {
    private MainController controller;
    private JPanel contentPanel;
    private JPanel contentPanelView;
    private String contentCode;
    private SideBar sidePanel;
    private TopBar topBar;
    private Integer counter = 0;
    private Map<String, JPanel> activePanels;



    public MainWindow(MainController controller) {
        this.controller = controller;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Skin failed to load, proceeding with normal skin");
        }
        contentCode = PanelCode.HOME;
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

        GridBagConstraints sidePanelGbc = new GridBagConstraints();
        sidePanelGbc.gridx = 0;
        sidePanelGbc.gridy = 0;
        sidePanelGbc.gridheight = mainPanel.getHeight();
        sidePanelGbc.weightx = 0;
        sidePanelGbc.weighty = 0;
        sidePanelGbc.fill = GridBagConstraints.BOTH;
        sidePanel = new SideBar(controller.getFixedBillDataIO() ,287, Colors.LIGHT_BLUE, Color.WHITE);

        topBar = new TopBar(47, Colors.DARK_BLUE);
        GridBagConstraints topBarGbc = new GridBagConstraints();
        topBarGbc.gridx = 1;
        topBarGbc.gridy = 0;
        topBarGbc.weightx = 0;
        topBarGbc.weighty = 0;
        topBarGbc.fill = GridBagConstraints.BOTH;

        topBar.addArrayType(PanelCode.NULL);
        topBar.addArrayType(PanelCode.KASIR);
        topBar.addArrayType(PanelCode.LAPORAN);
        topBar.addArrayType(PanelCode.INVENTARIS);
        topBar.addArrayType(PanelCode.MEMBER);
        topBar.addArrayType(PanelCode.PENGATURAN);

        sidePanel.addButton(new SideBarButton("/kasir.png", "Kasir"), "kasirButton");
        ((JButton) sidePanel.getComponent("kasirButton")).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWindow("Pembayaran", new KasirPanel(controller.getBarangDataIO(), controller.getFixedBillDataIO(),
                        controller.getMemberDataIO(), controller.getVIPDataIO(), controller.getCustomerDataIO()), PanelCode.KASIR);
            }
        });
        sidePanel.addButton(new SideBarButton("/laporan.png", "Laporan"), "laporanButton")
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!topBar.hasType(PanelCode.LAPORAN))
                            addWindow("Laporan", new LaporanPanel(controller.getFixedBillDataIO()), PanelCode.LAPORAN);
                        else {
                            TopBarTab tab = ((TopBarTab) topBar
                                    .getComponent(topBar.getTabsWithType(PanelCode.LAPORAN).get(0)));
                            if (!tab.getStatus())
                                tab.doClick();
                        }
                    }
                });

        sidePanel.addButton(new SideBarButton("/member.png", "Member"), "MemberButton")
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!topBar.hasType(PanelCode.MEMBER))
                            addWindow("Member", new MemberPanel(controller.getMemberDataIO(), controller.getVIPDataIO(),
                                    controller.getFixedBillDataIO()), PanelCode.MEMBER);
                        else {
                            TopBarTab tab = ((TopBarTab) topBar
                                    .getComponent(topBar.getTabsWithType(PanelCode.MEMBER).get(0)));
                            if (!tab.getStatus())
                                tab.doClick();
                        }
                    }
                });
        sidePanel.addButton(new SideBarButton("/inventaris.png", "Inventaris"), "inventarisButton")
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!topBar.hasType(PanelCode.INVENTARIS))
                            addWindow("Inventaris", new InventarisPanel(controller.getBarangDataIO()),
                                    PanelCode.INVENTARIS);
                        else {
                            TopBarTab tab = ((TopBarTab) topBar
                                    .getComponent(topBar.getTabsWithType(PanelCode.INVENTARIS).get(0)));
                            if (!tab.getStatus())
                                tab.doClick();
                        }
                    }
                });
        sidePanel.addButton(new SideBarButton("/pengaturan.png", "Pengaturan"), "pengaturanButton")
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // activePanels.containsValue(JPanel.class);
                        if (!topBar.hasType(PanelCode.PENGATURAN))
                            addWindow("Pengaturan", new Settings(MainWindow.this, controller), PanelCode.PENGATURAN);
                        else {
                            TopBarTab tab = ((TopBarTab) topBar
                                    .getComponent(topBar.getTabsWithType(PanelCode.PENGATURAN).get(0)));
                            if (!tab.getStatus())
                                tab.doClick();
                        }
                    }
                });

        TopBarButton homeButton = (TopBarButton) topBar.getComponent("homeButton");
        activePanels.put("homeButton", new HomePanel());
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (topBar.getActive() != "homeButton") {
                    loadPage(activePanels.get("homeButton"));
                    contentCode = PanelCode.HOME;
                }
            }
        });

        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.add(sidePanel, sidePanelGbc);
        mainPanel.add(topBar, topBarGbc);
        mainPanel.setBackground(Colors.PEMBELIAN_RED);

        this.contentPanel = contentPanel;

        loadPage(activePanels.get("homeButton"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720));
        setResizable(false);
        add(mainPanel);
        setTitle("Cashoria");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                if (contentCode == PanelCode.HOME) {
                    HomePanel home = (HomePanel) contentPanelView;
                    home.stopAll();
                }
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                super.windowDeiconified(e);
                if (contentCode == PanelCode.HOME) {
                    HomePanel home = (HomePanel) contentPanelView;
                    home.startAll();
                }
            }
        });
    }

    public SideBar getSidePanel() {
        return sidePanel;
    }
    public MainController getContoller(){ return this.controller; }

    public TopBar getTopBar() {
        return topBar;
    }

    public void addWindow(String tabLabel, TabPanel panel, String type) {
        String name = "content" + counter.toString();
        TopBarTab newbutton = new TopBarTab(tabLabel, name, type);
        topBar.addTab(newbutton, name, type);
        activePanels.put(name, panel);
        panel.assignTab(newbutton);
        newbutton.getObserver().addListener(this);
        newbutton.doClick();
        counter++;
    }

    public void loadPage(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.revalidate();

        contentPanelView = panel;
        contentPanelView.setPreferredSize(new Dimension(0, 0));

        contentPanel.add(contentPanelView, BorderLayout.CENTER);
        contentPanel.repaint();
    }

    @Override
    public void closeTab(TabEvent e, String tabname) {
        ((TopBarButton) topBar.getComponent("homeButton")).doClick();
        activePanels.remove(tabname);
    }

    @Override
    public void clickTab(TabEvent e, String tabname, String panelType) {
        JPanel tabPanel = activePanels.get(tabname);
        if (contentPanelView != tabPanel) {
            loadPage(activePanels.get(tabname));
            contentCode = panelType;
        } else {
            loadPage(activePanels.get("homeButton"));
            contentCode = PanelCode.HOME;
        }
    }
}