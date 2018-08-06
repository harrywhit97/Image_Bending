import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    static final String IMAGE_NAME = "tree.jpg";

    static JFrame frame;
    static BufferedImage im;
    static JLabel lbl;



    public static void main(String[] args){
        loadAndPackImage("tree");

        Raster r = im.getData();
        r.
    }

    private static void loadAndPackImage(String title){
        frame = new JFrame(title);
        im = loadImage(IMAGE_NAME);
        lbl = new JLabel(new ImageIcon(im));
        frame.add(lbl);
        frame.pack();
        frame.setVisible(true);
    }

    private static BufferedImage loadImage(String fileName){

        try{
            return ImageIO.read(new File(fileName));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }




}
