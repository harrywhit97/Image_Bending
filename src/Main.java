import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main extends JFrame {


    public static void main(String[] args){
        String imagePath = "g.jpg";

        File imageFile = new File(imagePath);
        BufferedImage im = loadImage(imageFile);
        packImage(im, "orig");


        String[] colours = {"red", "green", "blue"};

        for(int i = 0; i < 3; i++){
            BufferedImage imageColour = extractColorFromImage(loadImage(imageFile), i+1);
            packImage(imageColour, colours[i]);
        }

    }

    /**
     *
     * @param im
     * @param colour colour to extract : 1=red, 2=blue, 3=green
     * @return buffered image with only selected colour
     */
    private static BufferedImage extractColorFromImage(BufferedImage im, int colour){
        int height = im.getHeight();
        int width = im.getWidth();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){

                int[] argb = getPixelARGB(im.getRGB(x,y));
                int newArgb = argb[0];

                //change vals
                for(int i = 1; i < 4; i++){
                    if(i != colour){
                        argb[i] = 0;
                    }
                    newArgb = (newArgb << 8) | argb[i];
                }
                im.setRGB(x,y,newArgb);
            }
        }
        return im;
    }

    private static int[] getPixelARGB(int pixel){
        int[] argb = new int[4];
        Color color = new Color(pixel);
        argb[0] = color.getAlpha();
        argb[1] = color.getRed();
        argb[2] = color.getBlue();
        argb[3] = color.getGreen();

        return argb;
    }


    private static void makeSavePackImage(byte[] bytes, String name){
        BufferedImage im = byteArrayToBufferedImage(bytes);
        //makeJPG(im, name);
        packImage(im, name);
    }

    /**
     * Turns a 1d byte array in to a buffered image
     * @param imageInByte byte[] bytes to turn in to bufferedimage
     * @return bufferedImage
     */
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

    /**
     * Gets the byte[] from a bufferdImage
     * @param im bufferedImage to extract byte[] from
     * @return 1d byte[]
     */
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

    /**
     * writes a bufferdImage to a .jpg with the specified name
     * @param im bufferedImage to write
     * @param name name of new jpg
     */
    private static void makeJPG(BufferedImage im, String name){
        try {
            ImageIO.write(im, "jpg", new File(name + ".jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * makes a JFrame and packed inputted image in to it
     * @param im image to display
     * @param title tile of JFrame
     */
    private static void packImage(BufferedImage im, String title){
        JFrame frame = new JFrame(title);
        JLabel lbl = new JLabel(new ImageIcon(im));
        frame.add(lbl);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Loads an image from a specified file
     * @param file imae file
     * @return buffered image
     */
    private static BufferedImage loadImage(File file){

        try{
            return ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }




}
