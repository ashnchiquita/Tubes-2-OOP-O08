package org.example;

public class settings extends javax.swing.JPanel {

    public settings() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pengaturanLabel = new javax.swing.JLabel();
        tempatPenyimpananFileLabel = new javax.swing.JLabel();
        tempatPenyimpananFileTextField = new javax.swing.JTextField();
        pluginLabel = new javax.swing.JLabel();
        jsonButton = new javax.swing.JRadioButton();
        xmlButton = new javax.swing.JRadioButton();
        formatFileLabel = new javax.swing.JLabel();
        pluginTextField = new javax.swing.JTextField();
        tambahkanBaruButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        pengaturanLabel.setFont(new java.awt.Font("Arial", 1, 33)); // NOI18N
        pengaturanLabel.setForeground(new java.awt.Color(229, 151, 0));
        pengaturanLabel.setText("Pengaturan");

        tempatPenyimpananFileLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tempatPenyimpananFileLabel.setForeground(new java.awt.Color(36, 60, 148));
        tempatPenyimpananFileLabel.setText("Tempat Penyimpanan File");

        tempatPenyimpananFileTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(75, 79, 196), 2, true));
        tempatPenyimpananFileTextField.setPreferredSize(new java.awt.Dimension(440, 44));

        pluginLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        pluginLabel.setForeground(new java.awt.Color(36, 60, 148));
        pluginLabel.setText("Plugin");

        jsonButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jsonButton.setForeground(new java.awt.Color(36, 60, 148));
        jsonButton.setText("JSON");

        xmlButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        xmlButton.setForeground(new java.awt.Color(36, 60, 148));
        xmlButton.setLabel("XML");

        formatFileLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        formatFileLabel.setForeground(new java.awt.Color(36, 60, 148));
        formatFileLabel.setText("Format File");

        pluginTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(75, 79, 196), 2, true));
        pluginTextField.setPreferredSize(new java.awt.Dimension(440, 44));

        tambahkanBaruButton.setBackground(new java.awt.Color(76, 110, 223));
        tambahkanBaruButton.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tambahkanBaruButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahkanBaruButton.setText("+ Tambahkan Baru");
        tambahkanBaruButton.setBorderPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempatPenyimpananFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahkanBaruButton)
                    .addComponent(pluginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatFileLabel)
                    .addComponent(pluginLabel)
                    .addComponent(xmlButton)
                    .addComponent(jsonButton)
                    .addComponent(tempatPenyimpananFileLabel)
                    .addComponent(pengaturanLabel))
                .addContainerGap(479, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(pengaturanLabel)
                .addGap(18, 18, 18)
                .addComponent(tempatPenyimpananFileLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempatPenyimpananFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatFileLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsonButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xmlButton)
                .addGap(18, 18, 18)
                .addComponent(pluginLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pluginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(tambahkanBaruButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }                      


    // Variables declaration - do not modify                     
    private javax.swing.JLabel formatFileLabel;
    private javax.swing.JRadioButton jsonButton;
    private javax.swing.JLabel pengaturanLabel;
    private javax.swing.JLabel pluginLabel;
    private javax.swing.JTextField pluginTextField;
    private javax.swing.JButton tambahkanBaruButton;
    private javax.swing.JLabel tempatPenyimpananFileLabel;
    private javax.swing.JTextField tempatPenyimpananFileTextField;
    private javax.swing.JRadioButton xmlButton;
}
