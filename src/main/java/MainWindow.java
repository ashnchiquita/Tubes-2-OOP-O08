import java.awt.*;
import javax.swing.*;

import PembelianUI.CheckoutPanel;
import PembelianUI.CreateMemberPanel;
import PembelianUI.PembelianPanel;
import PembelianUI.TerimakasihPanel;

public class MainWindow {
  private static void showUI() {
    JFrame mainFrame = new JFrame("Test Window");
    mainFrame.setSize(1280, 720);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

    int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

    JPanel sideNav = new JPanel();
    sideNav.setPreferredSize(new Dimension(230, vh));

    sideNav.setBackground(new Color(56, 100, 194));
    mainFrame.add(sideNav, BorderLayout.WEST);

    JPanel mainRight = new JPanel();
    mainRight.setLayout(new BorderLayout());
    mainFrame.add(mainRight, BorderLayout.CENTER);

    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(36, 60, 148));
    topPanel.setPreferredSize(new Dimension((int) (0.81 * vw), 48));
    mainRight.add(topPanel, BorderLayout.NORTH);

    // mainRight.add(new PembelianPanel(240), BorderLayout.CENTER);
    mainRight.add(new CheckoutPanel(), BorderLayout.CENTER);
    // mainRight.add(new TerimakasihPanel("Kim Jisoo"), BorderLayout.CENTER);
    // mainRight.add(new TerimakasihPanel(""), BorderLayout.CENTER);
    // mainRight.add(new CreateMemberPanel(), BorderLayout.CENTER);

    mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        showUI();
      }

    });
  }

}
