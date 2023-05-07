package boundary.panel.member.subpanel;

import boundary.observer.panelflow.PanelFlowEvent;
import boundary.panel.inventaris.subpanel.DaftarBarangPane;
import boundary.widget.PressedButton;
import boundary.widget.RoundBorder;
import boundary.widget.TabPane;

import model.*;
import controller.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDataMemberPane extends TabPane {
    private Member member;
    private GenericDataIO<Member> memberDataIO;
    private GenericDataIO<VIP> VIPDataIO;
    private GenericDataIO<FixedBill> fixedBillDataIO;
    // Setup
    private JPanel rightPanel;
    private JPanel leftPanel;

    private void setupLeftPanel() {
        /* Setting up left panel buttons */

        leftPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setPreferredSize(new Dimension(522, 670));
        leftPanel.setBorder(paddingBorder);

        // leftPanel.setLayout(new BorderLayout());

        // Header Atas
        JButton backButton = new JButton("<");
        backButton.setFont(new Font("Inter", Font.BOLD, 33));
        backButton.setForeground(new Color(229, 151, 0));
        backButton.setPreferredSize(new Dimension(60, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(e -> panelFlowObserver.newEvent(PanelFlowEvent.retract()));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(backButton, BorderLayout.NORTH);
        JLabel editDataMember = new JLabel("Edit data member");
        editDataMember.setFont(new Font("Inter", Font.BOLD, 33));
        editDataMember.setForeground(new Color(229, 151, 0));
        editDataMember.setPreferredSize(new Dimension(375, 40));
        leftPanel.add(editDataMember, BorderLayout.NORTH);

        // Nama
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("Inter", Font.BOLD, 15));
        namaLabel.setForeground(new Color(0x243C94));
        namaLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(namaLabel, BorderLayout.NORTH);
        JTextField namaTextField = new JTextField("Nama");
        namaTextField.setPreferredSize(new Dimension(475, 45));
        namaTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        namaTextField.setForeground(new Color(0xD9D9D9));
        namaTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        namaTextField.setText(member.getName());
        leftPanel.add(namaTextField, BorderLayout.NORTH);

        // Kategori
        JLabel kategoriLabel = new JLabel("Telephone Number");
        kategoriLabel.setFont(new Font("Inter", Font.BOLD, 15));
        kategoriLabel.setForeground(new Color(0x243C94));
        kategoriLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(kategoriLabel, BorderLayout.NORTH);
        JTextField kategoriTextField = new JTextField("Telephone Number");
        kategoriTextField.setPreferredSize(new Dimension(475, 45));
        kategoriTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        kategoriTextField.setForeground(new Color(0xD9D9D9));
        kategoriTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        kategoriTextField.setText(member.getPhone());
        leftPanel.add(kategoriTextField, BorderLayout.NORTH);

        JRadioButton memberRadio = new JRadioButton("Member");
        memberRadio.setBackground(Color.WHITE);

        JRadioButton vipRadio = new JRadioButton("VIP");
        vipRadio.setBackground(Color.WHITE);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(memberRadio);
        buttonGroup.add(vipRadio);

        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(Color.WHITE);
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setPreferredSize(new Dimension(475, 45));
        radioPanel.setFont(new Font("Inter", Font.PLAIN, 15));

        if (member instanceof VIP) {
            vipRadio.setSelected(true);
        } else {
            memberRadio.setSelected(true);
        }

        radioPanel.add(memberRadio);
        radioPanel.add(vipRadio);

        leftPanel.add(radioPanel, BorderLayout.NORTH);

        JButton deactivateButton = new JButton("Deaktivasi");
        PressedButton buttonUI = new PressedButton(new Color(45, 77, 182));
        deactivateButton.setFont(new Font("Inter", Font.PLAIN, 15));
        deactivateButton.setBackground(new Color(236, 102, 102));
        deactivateButton.setForeground(Color.WHITE);
        deactivateButton.setOpaque(true);
        deactivateButton.setFocusable(false);
        deactivateButton.setPreferredSize(new Dimension(180, 43));
        deactivateButton.setUI(buttonUI);
        deactivateButton.setBorder(new RoundBorder(20));
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (member instanceof VIP) {
                    VIPDataIO.delete(member.getId());
                } else {
                    memberDataIO.delete(member.getId());
                }
                panelFlowObserver.newEvent(new PanelFlowEvent(
                        new DaftarMemberPane(memberDataIO, VIPDataIO, fixedBillDataIO), false));
            }
        });
        // leftPanel.add(deactivateButton, BorderLayout.WEST);

        JButton confirmButton = new JButton("Simpan");
        PressedButton buttonUI2 = new PressedButton(new Color(45, 77, 182));
        confirmButton.setFont(new Font("Inter", Font.PLAIN, 15));
        confirmButton.setBackground(new Color(76, 110, 223));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setOpaque(true);
        confirmButton.setFocusable(false);
        confirmButton.setPreferredSize(new Dimension(180, 43));
        confirmButton.setUI(buttonUI2);
        confirmButton.setBorder(new RoundBorder(20));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vipRadio.isSelected()) {
                    if (member instanceof VIP) {
                        VIP b = (VIP) member;
                        b.setName(namaTextField.getText());
                        b.setPhone(kategoriTextField.getText());
                        VIPDataIO.update(b);
                    } else {
                        // TODO: gantengin
                        VIP b = VIP
                                .builder()
                                .id()
                                .point(member.getPoint())
                                .transactions(member.getTransactions())
                                .name(namaTextField.getText())
                                .phone(kategoriTextField.getText())
                                .active(false)
                                .build();
                        b.setId(member.getId());
                        VIPDataIO.insert(b);
                        memberDataIO.delete(member.getId());
                    }
                } else {
                    if (member instanceof VIP) {
                        Member b = Member.builder()
                                .id()
                                .point(member.getPoint())
                                .transactions(member.getTransactions())
                                .name(namaTextField.getText())
                                .phone(kategoriTextField.getText())
                                .active(false)
                                .build();
                        b.setId(member.getId());
                        memberDataIO.insert(b);
                        VIPDataIO.delete(member.getId());
                    } else {
                        Member b = member;
                        b.setName(namaTextField.getText());
                        b.setPhone(kategoriTextField.getText());
                        memberDataIO.update(b);
                    }
                }
                panelFlowObserver.newEvent(new PanelFlowEvent(
                        new DaftarMemberPane(memberDataIO, VIPDataIO, fixedBillDataIO), false));
            }
        });
        leftPanel.add(confirmButton, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        buttonPanel.setMaximumSize(new Dimension(350, 50));
        buttonPanel.add(deactivateButton);
        buttonPanel.add(confirmButton);
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
    }

    private void setupRightPanel() {
        /* Setting up left panel buttons */

        rightPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(522, 670));
        rightPanel.setBorder(paddingBorder);

        // filePanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
        // rightPanel.setLayout(new BorderLayout());

    }

    public EditDataMemberPane(Member member, GenericDataIO<Member> memberDataIO, GenericDataIO<VIP> VIPDataIO) {
        this.member = member;
        this.memberDataIO = memberDataIO;
        this.VIPDataIO = VIPDataIO;
        this.setBackground(Color.WHITE);
        setupLeftPanel();
        this.add(leftPanel, BorderLayout.NORTH);
        setupRightPanel();
        this.add(rightPanel, BorderLayout.NORTH);
    }
}
