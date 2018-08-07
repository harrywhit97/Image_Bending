import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main extends JFrame {


    public static void main(String[] args){
        String imagePath = "tree.jpg";

        BufferedImage im = loadImage(imagePath);


        packImage(im, "pic");



        byte[] bytes = bufferedImageToByteArray(im);
        BufferedImage newIm = byteArrayToBufferedImage(bytes);
        makeJPG(newIm, "new");





        /*

        int width = im.getWidth();
        int height = im.getHeight();

        read image left to right and top to bottom

        e.g:

        0,1,2
        3,4,5


        for(int w = 0; w < width; w++){
            for(int h = 0; h < height; h++){
                System.out.println(pixels)
            }
        }
        */
    }

    private static BufferedImage byteArrayToBufferedImage(byte[] imageInByte){
        BufferedImage im = null;
        try {
            InputStream in = new ByteArrayInputStream(imageInByte);
            im = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return im;
    }

    private static void makeJPG(BufferedImage im, String name){
        try {
            ImageIO.write(im, "jpg", new File(name + ".jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static byte[] bufferedImageToByteArray(BufferedImage im){
        byte[] imageInByte = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(im, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return imageInByte;
    }

    private static void packImage(BufferedImage im, String title){
        JFrame frame = new JFrame(title);
        JLabel lbl = new JLabel(new ImageIcon(im));
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
