package ilveron.stuff;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static JFrame f = new JFrame("Tiler");

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "bmp", "jpeg"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Select the tile you want to repeat");

        int result = fileChooser.showOpenDialog(f);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedImage finalImage = makeTiles(selectedFile, screenSize);
            File finalFile = new File("final.jpg");
            try{
                ImageIO.write(finalImage, "bmp", finalFile);
            } catch (IOException|NullPointerException ne) {
                JOptionPane.showMessageDialog(f, "Cannot write to the destination file");
            }
        }
        f.dispose();
    }

    public static BufferedImage makeTiles(File selectedFile, Dimension screenSize){
        BufferedImage bimg = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bimg.getGraphics();

        Tile myTile = null;
        Dimension imageSize = null;
        try{
            myTile = new Tile(ImageIO.read(selectedFile));
            imageSize = myTile.getTileSize();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(f, "Cannot read from the selected file");
        }

        if (imageSize != null) {
            System.out.println("Width: " + imageSize.width);
            System.out.println("Height: " + imageSize.height);

            int x = 0, y = 0;

            while (y < screenSize.height) {
                while (x < screenSize.width) {
                    g.drawImage(myTile.getMyTile(), x, y, null);
                    x += imageSize.width;
                }
                y += imageSize.height;
                x = 0;
            }
            g.dispose();
            return bimg;
        }
        return null;
    }
}