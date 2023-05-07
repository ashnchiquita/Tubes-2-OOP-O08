package boundary.panel.settings;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import boundary.widget.PlainScrollBar;
import boundary.widget.TabPanel;
import controller.MainController;
import controller.barang.BarangAdapterJSON;
import controller.barang.BarangAdapterOBJ;
import controller.barang.BarangAdapterXML;
import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerAdapterOBJ;
import controller.customer.CustomerAdapterXML;
import controller.fixedbill.FixedBillAdapterJSON;
import controller.fixedbill.FixedBillAdapterOBJ;
import controller.fixedbill.FixedBillAdapterXML;
import controller.member.MemberAdapterJSON;
import controller.member.MemberAdapterOBJ;
import controller.member.MemberAdapterXML;
import controller.vip.VIPAdapterJSON;
import controller.vip.VIPAdapterOBJ;
import controller.vip.VIPAdapterXML;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Settings extends TabPanel {
    MainController controller;

    public Settings(MainController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setBackground(Colors.WHITE);
        setLayout(null);
        pengaturanLabel = new javax.swing.JLabel();
        tempatPenyimpananFileLabel = new javax.swing.JLabel();
        tempatPenyimpananFileTextField = new javax.swing.JTextField();
        pluginLabel = new javax.swing.JLabel();
        jsonButton = new javax.swing.JRadioButton();
        xmlButton = new javax.swing.JRadioButton();
        formatFileLabel = new javax.swing.JLabel();
        pluginTextField = new JTextArea();
        tambahkanBaruButton = new javax.swing.JButton();
        radioPanel = new JPanel();
        buttonGroup = new ButtonGroup();
        JSONRadio = new JRadioButton("JSON");
        XMLRadio = new JRadioButton("XML");
        OBJRadio = new JRadioButton("OBJ");

        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        pengaturanLabel.setFont(new java.awt.Font("Inter", 1, 33)); // NOI18N
        pengaturanLabel.setForeground(new java.awt.Color(229, 151, 0));
        pengaturanLabel.setText("Pengaturan");
        pengaturanLabel.setBounds(85, 35, 300, 50);
        add(pengaturanLabel);

        tempatPenyimpananFileLabel.setFont(new java.awt.Font("Inter", 1, 15)); // NOI18N
        tempatPenyimpananFileLabel.setForeground(new java.awt.Color(36, 60, 148));
        tempatPenyimpananFileLabel.setText("Tempat Penyimpanan File");
        tempatPenyimpananFileLabel.setBounds(85, 95, 300, 40);
        add(tempatPenyimpananFileLabel);

        tempatPenyimpananFileTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(75, 79, 196), 2, true));
        tempatPenyimpananFileTextField.setPreferredSize(new java.awt.Dimension(440, 44));
        tempatPenyimpananFileTextField.setBounds(85, 135, 400, 40);
        tempatPenyimpananFileTextField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tempatPenyimpananFileTextField.setEditable(false);
        add(tempatPenyimpananFileTextField);

        seekConfigButton = new JButton("Cari file");
        seekConfigButton.setBackground(Colors.DARK_BLUE);
        seekConfigButton.setForeground(Colors.WHITE);
        seekConfigButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        seekConfigButton.setBorder(null);
        seekConfigButton.setFocusPainted(false);
        seekConfigButton.setBounds(500, 135, 150, 40);
        seekConfigButton.addActionListener(e -> seekConfig());
        add(seekConfigButton);

        formatFileLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        formatFileLabel.setForeground(new java.awt.Color(36, 60, 148));
        formatFileLabel.setText("Format File");
        formatFileLabel.setBounds(85, 180, 100, 40);
        add(formatFileLabel);

        jsonButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jsonButton.setForeground(new Color(36, 60, 148));
        jsonButton.setText("JSON");

        xmlButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        xmlButton.setForeground(new Color(36, 60, 148));
        xmlButton.setLabel("XML");

        JSONRadio.setFont(new Font("Inter", Font.PLAIN, 15));
        JSONRadio.setBackground(Color.WHITE);
        JSONRadio.setForeground(new Color(36, 60, 148));

        XMLRadio.setFont(new Font("Inter", Font.PLAIN, 15));
        XMLRadio.setBackground(Color.WHITE);
        XMLRadio.setForeground(new Color(36, 60, 148));

        OBJRadio.setFont(new Font("Inter", Font.PLAIN, 15));
        OBJRadio.setBackground(Color.WHITE);
        OBJRadio.setForeground(new Color(36, 60, 148));

        try{
            BufferedReader br = new BufferedReader(new FileReader(dataStore.getPath()));

            tempatPenyimpananFileTextField.setText(br.readLine());
            String extension = br.readLine();
            if(extension.equals("OBJ")){
                OBJRadio.setSelected(true);
            }
            else if(extension.equals("XML")){
                XMLRadio.setSelected(true);
            }
            else if(extension.equals("JSON")){
                JSONRadio.setSelected(true);
            }

        }catch (Exception e){
            System.out.println("Data loading failed");
        }

        buttonGroup.add(JSONRadio);
        buttonGroup.add(XMLRadio);
        buttonGroup.add(OBJRadio);

        radioPanel.setBackground(Color.WHITE);
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setPreferredSize(new Dimension(475, 45));
        radioPanel.setFont(new Font("Inter", Font.PLAIN, 15));
        radioPanel.setBounds(85, 215, 500, 80);
        radioPanel.add(JSONRadio);
        radioPanel.add(XMLRadio);
        radioPanel.add(OBJRadio);
        add(radioPanel);

        pluginLabel.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        pluginLabel.setForeground(new Color(36, 60, 148));
        pluginLabel.setText("Plugin");
        pluginLabel.setBounds(85, 305, 100, 40);
        add(pluginLabel);

        JScrollPane pluginScrollPane = new JScrollPane();


        pluginTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(75, 79, 196), 2, true));
        pluginTextField.setPreferredSize(new java.awt.Dimension(300, 44));
        pluginTextField.setBounds(85, 340, 500, 200);
        pluginTextField.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        pluginTextField.setEditable(false);
        try {
            BufferedReader br = new BufferedReader(new FileReader(pluginStore.getPath()));
            String line;
            StringBuilder builder = new StringBuilder();
            Integer rows = 0;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
                rows++;
            }
            pluginTextField.setRows(rows);
            pluginTextField.setText(builder.toString());
        } catch (Exception e){
            System.out.println("Plugin loading failed");
        }
        pluginScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(75, 79, 196), 2, true));
        pluginScrollPane.setViewportView(pluginTextField);
        pluginScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        pluginScrollPane.getVerticalScrollBar().setUI(new PlainScrollBar(Colors.WHITE, Colors.SIDE_SLIDER_BLUE));
        pluginScrollPane.setBounds(85, 340, 500, 200);
        add(pluginScrollPane);


        tambahkanBaruButton.setBackground(new java.awt.Color(76, 110, 223));
        tambahkanBaruButton.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tambahkanBaruButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahkanBaruButton.setText("+ Tambahkan Baru");
        tambahkanBaruButton.setBorderPainted(false);
        tambahkanBaruButton.setBounds(85, 550, 200, 40);
        tambahkanBaruButton.addActionListener(e -> seekPlugin());
        add(tambahkanBaruButton);
    }

    private void seekConfig() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Pilih Tempat Penyimpanan File");
        folderChooser.setCurrentDirectory(new java.io.File("."));
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        folderChooser.setAcceptAllFileFilterUsed(false);

        if (folderChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String strpath = "";
                String extension = "";
                //TODO: input handling
                File fileToSave = folderChooser.getSelectedFile();
                FileWriter fw = new FileWriter(dataStore.getPath(), false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);

                strpath = fileToSave.getPath();
                Path path = Paths.get(strpath);
                Path rootPath = Paths.get(ResourcePath.ABSOLUTE);

                out.println(rootPath.relativize(path));

                if(OBJRadio.isSelected()){
                    out.println("OBJ");
                }
                else if(XMLRadio.isSelected()){
                    out.println("XML");
                }
                else if(JSONRadio.isSelected()){
                    out.println("JSON");
                }

                out.flush();

                BufferedReader br = new BufferedReader(new FileReader(dataStore.getPath()));
                tempatPenyimpananFileTextField.setText(br.readLine());
                controller = new MainController();
                fw.close();
                out.close();
                br.close();
                bw.close();
            } catch (IOException e) {
                System.out.println("Plugin loading failed");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("No Selection");
        }
    }

    private void seekPlugin() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Plugin");
        fileChooser.setCurrentDirectory(new java.io.File("."));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = fileChooser.getSelectedFile();
                //TODO: input handling jenis plugin
                //TODO: reload program aja
                FileWriter fw = new FileWriter(pluginStore.getPath(), true );
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);

                Path path = Paths.get(fileToSave.getPath());
                Path rootPath = Paths.get(ResourcePath.ABSOLUTE);

                out.println(rootPath.relativize(path));
                out.flush();

                BufferedReader br = new BufferedReader(new FileReader(pluginStore.getPath()));
                String line;
                StringBuilder builder = new StringBuilder();
                Integer rows = 0;
                while ((line = br.readLine()) != null) {
                    builder.append(line).append("\n");
                    rows++;
                }

                pluginTextField.setRows(rows);
                pluginTextField.setText(builder.toString());
                fw.close();
                out.close();
                br.close();
                bw.close();
            } catch (IOException e) {
                System.out.println("Plugin loading failed");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("No Selection");
        }
    }

    // Variables declaration - do not modify
    public static File dataStore = new File(ResourcePath.DATA + "/datapath.txt");
    public static File pluginStore = new File(ResourcePath.DATA + "/plugins.txt");
    private JButton seekConfigButton;
    private JRadioButton OBJRadio;
    private JRadioButton XMLRadio;
    private JRadioButton JSONRadio;
    private ButtonGroup buttonGroup;
    private JPanel radioPanel;
    private JLabel formatFileLabel;
    private JRadioButton jsonButton;
    private JLabel pengaturanLabel;
    private JLabel pluginLabel;
    private JTextArea pluginTextField;
    private JButton tambahkanBaruButton;
    private JLabel tempatPenyimpananFileLabel;
    private JTextField tempatPenyimpananFileTextField;
    private JRadioButton xmlButton;
}