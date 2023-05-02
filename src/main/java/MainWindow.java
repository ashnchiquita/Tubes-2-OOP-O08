import java.awt.*;
import javax.swing.*;

import boundary.constants.Colors;
import boundary.panel.pembelian.PembelianPanel;

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

    sideNav.setBackground(Colors.LIGHT_BLUE);
    mainFrame.add(sideNav, BorderLayout.WEST);

    JPanel mainRight = new JPanel();
    mainRight.setLayout(new BorderLayout());
    mainFrame.add(mainRight, BorderLayout.CENTER);

    JPanel topPanel = new JPanel();
    topPanel.setBackground(Colors.DARK_BLUE);
    topPanel.setPreferredSize(new Dimension((int) (0.81 * vw), 48));
    mainRight.add(topPanel, BorderLayout.NORTH);

    mainRight.add(new PembelianPanel(240), BorderLayout.CENTER);
    // mainRight.add(new CheckoutPanel(), BorderLayout.CENTER);
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
