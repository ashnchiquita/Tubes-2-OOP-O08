package org.example;

import org.example.widgets.Clock;

import javax.swing.*;
import java.awt.*;

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

        clock.setLocation(102, 179);

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
