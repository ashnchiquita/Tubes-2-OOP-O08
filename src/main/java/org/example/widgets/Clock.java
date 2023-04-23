package org.example.widgets;

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
        setBackground(Color.WHITE);

        clockText = new JLabel();
        clockText.setHorizontalAlignment(SwingConstants.LEFT);
        clockText.setVerticalAlignment(SwingConstants.TOP);
        clockText.setForeground(Color.WHITE);
        clockText.setFont(new Font("Inter", Font.BOLD, 100));
        clockText.setBounds(285, 35, 763, 207);

        add(clockText);

        try{
            String rootPath = System.getProperty("user.dir");
            rootPath += "/res/img/";
            BufferedImage myPicture = ImageIO.read(new File(rootPath + "Jam.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 763, 207);
            add(picLabel);
        } catch (Exception e){
            System.out.println("Clock fails to load image");
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
