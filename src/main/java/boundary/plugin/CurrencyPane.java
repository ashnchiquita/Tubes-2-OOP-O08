package boundary.panel.plugin;

import boundary.observer.panelflow.PanelFlowEvent;
import boundary.widget.PressedButton;
import boundary.widget.RoundBorder;
import boundary.widget.TabPane;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyPane extends TabPane {
    // Setup
    private JPanel rightPanel;
    private JPanel leftPanel;

    private void setupLeftPanel(){
        /* Setting up left panel buttons */

        leftPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        leftPanel.setBackground(new Color(255,255,255));
        leftPanel.setPreferredSize(new Dimension(522,670));
        leftPanel.setBorder(paddingBorder);

//        leftPanel.setLayout(new BorderLayout());

        // Header Atas
        JButton backButton = new JButton("<");
        backButton.setFont(new Font("Inter", Font.BOLD, 33));
        backButton.setForeground(new Color(229, 151, 0));
        backButton.setPreferredSize(new Dimension(60, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(e -> panelFlowObserver.newEvent(PanelFlowEvent.retract()));
        leftPanel.add(backButton,BorderLayout.NORTH);
        JLabel kursJudul = new JLabel("Kurs");
        kursJudul.setFont(new Font("Inter", Font.BOLD, 33));
        kursJudul.setForeground(new Color(229, 151, 0));
        kursJudul.setPreferredSize(new Dimension(375, 40));
        leftPanel.add(kursJudul,BorderLayout.NORTH);
        
        // vertical gap
        leftPanel.add(Box.createRigidArea(new Dimension(0, 90)));

        // Toggle
        // start n dest curr
        JPanel currencyTogglePanel = new JPanel(new BorderLayout(10, 0));
        currencyTogglePanel.setPreferredSize(new Dimension(475, 40));

        // start toggle button
        JToggleButton startCurrencyToggle = new JToggleButton("Kurs Awal");
        JPopupMenu startCurrencyMenu = new JPopupMenu();
        JMenuItem startCurrencyItem1 = new JMenuItem("IDR (Rp)");
        JMenuItem startCurrencyItem2 = new JMenuItem("USD ($)");
        JMenuItem startCurrencyItem3 = new JMenuItem("Pounds (£)");
        startCurrencyToggle.setPreferredSize(new Dimension(200, 50));

        startCurrencyMenu.add(startCurrencyItem1);
        startCurrencyMenu.add(startCurrencyItem2);
        startCurrencyMenu.add(startCurrencyItem3);
        startCurrencyToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCurrencyMenu.show(startCurrencyToggle, 0, startCurrencyToggle.getHeight());
            }
        });

        // dest toggle button
        JToggleButton destCurrencyToggle = new JToggleButton("Kurs Akhir");
        JPopupMenu destCurrencyMenu = new JPopupMenu();
        JMenuItem destCurrencyItem1 = new JMenuItem("IDR (Rp)");
        JMenuItem destCurrencyItem2 = new JMenuItem("USD ($)");
        JMenuItem destCurrencyItem3 = new JMenuItem("Pounds (£)");
        destCurrencyToggle.setPreferredSize(new Dimension(200, 50));
        destCurrencyMenu.add(destCurrencyItem1);
        destCurrencyMenu.add(destCurrencyItem2);
        destCurrencyMenu.add(destCurrencyItem3);
        destCurrencyMenu.setBackground(Color.gray);
        destCurrencyToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destCurrencyMenu.show(destCurrencyToggle, 0, destCurrencyToggle.getHeight());
            }
        });

        // panah <==>
        JLabel arrowLabel = new JLabel("<==>");
        arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrowLabel.setBackground(new Color(0xFFFFFF));


        currencyTogglePanel.add(startCurrencyToggle, BorderLayout.WEST);
        currencyTogglePanel.add(arrowLabel, BorderLayout.CENTER);
        currencyTogglePanel.add(destCurrencyToggle, BorderLayout.EAST);
        currencyTogglePanel.setBackground(Color.WHITE);

        // Style currency toggles
        startCurrencyToggle.setBackground(new Color(76,110,223));
        startCurrencyToggle.setForeground(Color.WHITE);
        destCurrencyToggle.setBackground(new Color(76,110,223));
        destCurrencyToggle.setForeground(Color.WHITE);

        leftPanel.add(currencyTogglePanel, BorderLayout.NORTH);

        // vertical gap
        leftPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        // text field nominal
        JTextField amountTextField = new JTextField("Masukkan Nominal Awal");
        amountTextField.setPreferredSize(new Dimension(475, 45));
        amountTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        amountTextField.setForeground(new Color(0xD9D9D9));
        amountTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        // label converted currency
        JLabel convertedCurrencyLabel = new JLabel("Nominal Akhir: ");
        convertedCurrencyLabel.setPreferredSize(new Dimension(475, 45));
        convertedCurrencyLabel.setFont(new Font("Inter", Font.BOLD, 15));
        convertedCurrencyLabel.setForeground(new Color(229, 151, 0));

        leftPanel.add(amountTextField, BorderLayout.CENTER);
        leftPanel.add(convertedCurrencyLabel, BorderLayout.SOUTH);

        // vertical gap
        leftPanel.add(Box.createRigidArea(new Dimension(0, 70)));
        
        // toggle sampe sini
        
        JButton createNewItemButton = new JButton("Batal");
        PressedButton buttonUI = new PressedButton(new Color(45,77,182));
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(236,102,102));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180,43));
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        // leftPanel.add(createNewItemButton, BorderLayout.WEST);

        JButton createNewItemButton2 = new JButton("Ubah");
        PressedButton buttonUI2 = new PressedButton(new Color(45,77,182));
        createNewItemButton2.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton2.setBackground(new Color(76,110,223));
        createNewItemButton2.setForeground(Color.WHITE);
        createNewItemButton2.setOpaque(true);
        createNewItemButton2.setFocusable(false);
        createNewItemButton2.setPreferredSize(new Dimension(180,43));
        createNewItemButton2.setUI(buttonUI2);
        createNewItemButton2.setBorder(new RoundBorder(20));
        leftPanel.add(createNewItemButton2, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.setMaximumSize(new Dimension(350, 50));
        buttonPanel.add(createNewItemButton);
        buttonPanel.add(createNewItemButton2);
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
    }

    private void setupRightPanel() {
        /* Setting up left panel buttons */

        rightPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(522, 670));
        rightPanel.setBorder(paddingBorder);


//        filePanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
//        rightPanel.setLayout(new BorderLayout());

    }

    public CurrencyPane(){
        this.setBackground(Color.WHITE);
        setupLeftPanel();
        this.add(leftPanel, BorderLayout.NORTH);
        setupRightPanel();
        this.add(rightPanel, BorderLayout.NORTH);

    }    
}
