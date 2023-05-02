package boundary.panel.home;

import boundary.constants.ResourcePath;
import boundary.widget.Clock;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HomeUI extends JPanel {
    Clock clock = new Clock();
    Thread timerThread = new Thread(new Clock());

    public HomeUI(){
        super();
        setLayout(null);
        setMinimumSize(new Dimension(720, 720));
        setBackground(Color.WHITE);

        JLabel selamatLabel = new JLabel("Selamat");
        selamatLabel.setFont(new Font("Inter", Font.PLAIN, 53));
        selamatLabel.setBounds(278, 68, 200, 96);
        selamatLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selamatLabel.setVerticalAlignment(SwingConstants.TOP);
        selamatLabel.setForeground(new Color(2374804));

        JLabel datangLabel = new JLabel("Datang!");
        datangLabel.setFont(new Font("Inter", Font.BOLD, 53));
        datangLabel.setBounds(490, 68, 420, 96);
        datangLabel.setHorizontalAlignment(SwingConstants.LEFT);
        datangLabel.setVerticalAlignment(SwingConstants.TOP);
        datangLabel.setForeground(new Color(2374804));

        JLabel dateLabel = new JLabel("Mon, 16 Juli 2024");
        dateLabel.setFont(new Font("Inter", Font.PLAIN, 22));
        dateLabel.setBounds(390, 131, 190, 27);
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateLabel.setVerticalAlignment(SwingConstants.TOP);

        clock.setLocation(102, 174);

        try{

            BufferedImage myPicture;

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Maggie.png"));
            JLabel picMag = new JLabel(new ImageIcon(myPicture));
            picMag.setBounds(102, 314, 109, 235);
            picMag.setOpaque(false);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Lish.png"));
            JLabel picLish = new JLabel(new ImageIcon(myPicture));
            picLish.setBounds(232, 314, 109, 235);
            picLish.setOpaque(false);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/We.png"));
            JLabel picWe = new JLabel(new ImageIcon(myPicture));
            picWe.setBounds(362, 314, 109, 235);
            picWe.setOpaque(false);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Chi.png"));
            JLabel picChi = new JLabel(new ImageIcon(myPicture));
            picChi.setBounds(492, 324, 139, 235);
            picChi.setOpaque(false);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Rav.png"));
            JLabel picRav = new JLabel(new ImageIcon(myPicture));
            picRav.setBounds(622, 314, 109, 235);
            picRav.setOpaque(false);

            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Nes.png"));
            JLabel picNes = new JLabel(new ImageIcon(myPicture));
            picNes.setBounds(752, 339, 109, 235);
            picNes.setOpaque(false);

            add(picMag);
            add(picLish);
            add(picWe);
            add(picChi);
            add(picRav);
            add(picNes);
        }
        catch (Exception e){
            System.out.println("Image Loading Failure: " + e.getMessage());
        }

        this.add(selamatLabel);
        this.add(datangLabel);
        this.add(dateLabel);
        this.add(clock);

        startTimer();
    }

    public void stopTimer(){
        System.out.println("Timer stopped");
        timerThread.interrupt();
    }

    public void startTimer(){
        System.out.println("Timer started");
        timerThread = new Thread(clock);
        timerThread.start();
    }
}
