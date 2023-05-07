package boundary.widget;

import boundary.constants.ResourcePath;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Logo extends JLabel implements Runnable {
    ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
    Integer counter = 0;
    Integer frameCount = 0;

    public Logo(){
        try{
            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream stream = ImageIO.createImageInputStream(new File(ResourcePath.ASSETS + "/logo/logo.gif"));
            reader.setInput(stream);
            frameCount = reader.getNumImages(true);
            for (int i = 0; i < frameCount; i++) {
                frames.add(reader.read(i));
            }
            ImageIcon logo = new ImageIcon(frames.get(0));
            setIcon(logo);
        } catch(Exception e){
            System.out.println("Gif loading error: " + e);
        }
    }

    @Override
    public void run(){
        if (frames.isEmpty()){
            return;
        }
        try {
            while (true){
                ImageIcon logo = new ImageIcon(frames.get(counter));

                setIcon(logo);

                Thread.sleep(67);
                if(counter < frameCount - 1) counter++;
                else counter = 0;
            }
        } catch (InterruptedException e){
            System.out.println("Logo stopped");
        }
    }
}
