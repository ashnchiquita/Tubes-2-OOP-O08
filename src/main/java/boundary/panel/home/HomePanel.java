package boundary.panel.home;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import boundary.widget.Clock;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;

public class HomePanel extends JPanel {
    Clock clock = new Clock();
    Thread timerThread = new Thread(new Clock());

    public HomePanel() {
        super();
        setLayout(null);
        setMinimumSize(new Dimension(720, 720));
        setBackground(Color.WHITE);

        JLabel selamatLabel = new JLabel("Selamat");
        selamatLabel.setFont(new Font("Inter", Font.PLAIN, 53));
        selamatLabel.setBounds(268, 38, 250, 96);
        selamatLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selamatLabel.setVerticalAlignment(SwingConstants.TOP);
        selamatLabel.setForeground(new Color(2374804));

        JLabel datangLabel = new JLabel("Datang!");
        datangLabel.setFont(new Font("Inter", Font.BOLD, 53));
        datangLabel.setBounds(490, 38, 420, 96);
        datangLabel.setHorizontalAlignment(SwingConstants.LEFT);
        datangLabel.setVerticalAlignment(SwingConstants.TOP);
        datangLabel.setForeground(new Color(2374804));


        LocalDateTime now = LocalDateTime.now();  
        now.getMonth().toString();
        
        String dayString = now.getDayOfWeek().name();
        dayString = dayString.charAt(0) + dayString.substring(1, 3).toLowerCase(getLocale());
        String monString = now.getMonth().name();
        monString = monString.charAt(0) + monString.substring(1, 3).toLowerCase(getLocale());
        JLabel dateLabel = new JLabel(dayString + ", " + now.getDayOfMonth() + " " + monString + " " + now.getYear());
        //JLabel dateLabel = new JLabel("Mon, 16 Juli 2024");
        dateLabel.setFont(new Font("Inter", Font.PLAIN, 22));
        dateLabel.setBounds(390, 101, 200, 27);
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateLabel.setVerticalAlignment(SwingConstants.TOP);

        clock.setLocation(102, 144);

        try {
            BufferedImage myPicture;

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Maggie.png"));
            JLabel picMag = new JLabel(new ImageIcon(myPicture));
            picMag.setBounds(102, 284, 109, 235);
            picMag.setOpaque(false);

            JLabel MaggieName = new JLabel("Maggie Zeta R.S.");
            MaggieName.setFont(new Font("Inter", Font.BOLD, 13));
            MaggieName.setBounds(98, 504, 120, 20);
            MaggieName.setHorizontalAlignment(SwingConstants.LEFT);
            MaggieName.setVerticalAlignment(SwingConstants.TOP);
            MaggieName.setForeground(Colors.BLACK);

            JLabel MaggieNim = new JLabel("13521117");
            MaggieNim.setFont(new Font("Inter", Font.BOLD, 13));
            MaggieNim.setBounds(125, 524, 120, 20);
            MaggieNim.setHorizontalAlignment(SwingConstants.LEFT);
            MaggieNim.setVerticalAlignment(SwingConstants.TOP);
            MaggieNim.setForeground(Colors.OCEAN_BLUE);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Lish.png"));
            JLabel picLish = new JLabel(new ImageIcon(myPicture));
            picLish.setBounds(232, 284, 109, 235);
            picLish.setOpaque(false);

            JLabel LishName = new JLabel("Alisha Listya W.");
            LishName.setFont(new Font("Inter", Font.BOLD, 13));
            LishName.setBounds(235, 504, 120, 20);
            LishName.setHorizontalAlignment(SwingConstants.LEFT);
            LishName.setVerticalAlignment(SwingConstants.TOP);
            LishName.setForeground(Colors.BLACK);

            JLabel LishNim = new JLabel("13521171");
            LishNim.setFont(new Font("Inter", Font.BOLD, 13));
            LishNim.setBounds(255, 524, 120, 20);
            LishNim.setHorizontalAlignment(SwingConstants.LEFT);
            LishNim.setVerticalAlignment(SwingConstants.TOP);
            LishNim.setForeground(Colors.OCEAN_BLUE);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/We.png"));
            JLabel picWe = new JLabel(new ImageIcon(myPicture));
            picWe.setBounds(362, 284, 120, 235);
            picWe.setOpaque(false);

            JLabel WeName = new JLabel("Muhamad Aji W.");
            WeName.setFont(new Font("Inter", Font.BOLD, 13));
            WeName.setBounds(370, 504, 120, 20);
            WeName.setHorizontalAlignment(SwingConstants.LEFT);
            WeName.setVerticalAlignment(SwingConstants.TOP);
            WeName.setForeground(Colors.BLACK);

            JLabel WeNim = new JLabel("13521095");
            WeNim.setFont(new Font("Inter", Font.BOLD, 13));
            WeNim.setBounds(396, 524, 120, 20);
            WeNim.setHorizontalAlignment(SwingConstants.LEFT);
            WeNim.setVerticalAlignment(SwingConstants.TOP);
            WeNim.setForeground(Colors.OCEAN_BLUE);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Chi.png"));
            JLabel picChi = new JLabel(new ImageIcon(myPicture));
            picChi.setBounds(492, 292, 139, 235);
            picChi.setOpaque(false);

            JLabel ChiName = new JLabel("Chiquita A.");
            ChiName.setFont(new Font("Inter", Font.BOLD, 13));
            ChiName.setBounds(512, 504, 150, 20);
            ChiName.setHorizontalAlignment(SwingConstants.LEFT);
            ChiName.setVerticalAlignment(SwingConstants.TOP);
            ChiName.setForeground(Colors.BLACK);

            JLabel ChiNim = new JLabel("13521129");
            ChiNim.setFont(new Font("Inter", Font.BOLD, 13));
            ChiNim.setBounds(518, 524, 120, 20);
            ChiNim.setHorizontalAlignment(SwingConstants.LEFT);
            ChiNim.setVerticalAlignment(SwingConstants.TOP);
            ChiNim.setForeground(Colors.OCEAN_BLUE);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Rav.png"));
            JLabel picRav = new JLabel(new ImageIcon(myPicture));
            picRav.setBounds(622, 284, 109, 235);
            picRav.setOpaque(false);

            JLabel RavName = new JLabel("Rava Maulana A.");
            RavName.setFont(new Font("Inter", Font.BOLD, 13));
            RavName.setBounds(622, 504, 150, 20);
            RavName.setHorizontalAlignment(SwingConstants.LEFT);
            RavName.setVerticalAlignment(SwingConstants.TOP);
            RavName.setForeground(Colors.BLACK);

            JLabel RavNim = new JLabel("13521149");
            RavNim.setFont(new Font("Inter", Font.BOLD, 13));
            RavNim.setBounds(645, 524, 120, 20);
            RavNim.setHorizontalAlignment(SwingConstants.LEFT);
            RavNim.setVerticalAlignment(SwingConstants.TOP);
            RavNim.setForeground(Colors.OCEAN_BLUE);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Nes.png"));
            JLabel picNes = new JLabel(new ImageIcon(myPicture));
            picNes.setBounds(752, 309, 109, 235);
            picNes.setOpaque(false);

            JLabel NesName = new JLabel("Vanessa R.W.");
            NesName.setFont(new Font("Inter", Font.BOLD, 13));
            NesName.setBounds(762, 504, 150, 20);
            NesName.setHorizontalAlignment(SwingConstants.LEFT);
            NesName.setVerticalAlignment(SwingConstants.TOP);
            NesName.setForeground(Colors.BLACK);

            JLabel NesNim = new JLabel("13521151");
            NesNim.setFont(new Font("Inter", Font.BOLD, 13));
            NesNim.setBounds(777, 524, 120, 20);
            NesNim.setHorizontalAlignment(SwingConstants.LEFT);
            NesNim.setVerticalAlignment(SwingConstants.TOP);
            NesNim.setForeground(Colors.OCEAN_BLUE);

            add(picMag);
            add(MaggieName);
            add(MaggieNim);

            add(picLish);
            add(LishName);
            add(LishNim);

            add(picWe);
            add(WeName);
            add(WeNim);

            add(picChi);
            add(ChiName);
            add(ChiNim);

            add(picRav);
            add(RavName);
            add(RavNim);

            add(picNes);
            add(NesName);
            add(NesNim);
        } catch (Exception e) {
            System.out.println("Image Loading Failure: " + e.getMessage());
        }

        this.add(selamatLabel);
        this.add(datangLabel);
        this.add(dateLabel);
        this.add(clock);

        startTimer();
    }

    public void stopTimer() {
        System.out.println("Timer stopped");
        timerThread.interrupt();
    }

    public void startTimer() {
        System.out.println("Timer started");
        timerThread = new Thread(clock);
        timerThread.start();
    }
}
