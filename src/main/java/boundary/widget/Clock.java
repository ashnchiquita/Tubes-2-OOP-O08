package boundary.widget;

import boundary.constants.ResourcePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock extends JPanel implements Runnable {
    private JLabel clockText;
    public Clock(){
        super();
        setLayout(null);
        setSize(763, 207);
        setOpaque(false);

        clockText = new JLabel();
        clockText.setHorizontalAlignment(SwingConstants.LEFT);
        clockText.setVerticalAlignment(SwingConstants.TOP);
        clockText.setForeground(Color.WHITE);
        clockText.setFont(new Font("Inter", Font.BOLD, 100));
        clockText.setBounds(285, 35, 763, 207);

        add(clockText);

        try{
            BufferedImage myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/Jam.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 763, 207);
            add(picLabel);
        } catch (Exception e){
            System.out.println("Clock fails to load image: " + e.getMessage());
        }
    }
    public void run(){
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            while (true){
                LocalDateTime now = LocalDateTime.now();
                clockText.setText(dtf.format(now));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("Timer Interrupted");
        }
    }
}
