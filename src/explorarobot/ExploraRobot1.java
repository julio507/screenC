/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explorarobot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author wolfi
 */
public class ExploraRobot1 extends JFrame {

    byte buffer[] = null;

    ExploraRobot1(byte buffer[]) {
        this.buffer = buffer;
        setSize(320, 200);
        setTitle("Mostrador");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int scale = 1;
        int offset = 40;
        int aux = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                // porque & 0xFF --> byte -128 a 127 .... 
                // int i = 255;  byte b = (byte) i; <- -1
                //r g b a
                Color cor = new Color(buffer[aux++] & 0xFF, buffer[aux++] & 0xFF, buffer[aux++] & 0xFF, buffer[aux++] & 0xFF);                
                g2.setColor(cor);
                g2.drawRect(offset + i * scale, offset + j * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {
        final int BLOCK_X = 20;
        final int BLOCK_Y = 20;

        byte buffer[] = new byte[BLOCK_X * BLOCK_Y * 4]; // a r g b  (a=alpha)

        try {
            int aux = 0;

            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(1920, 1080));// pegar toda a minha tela a resolução é de cada um, pesquisar como saber.., tem 

            for (int i = 0; i < BLOCK_Y; i++) {
                for (int j = 0; j < BLOCK_X; j++) {
                    
                    Color cor = new Color(bi.getRGB(i, j));
                    
                    buffer[aux++] = (byte) cor.getRed();
                    buffer[aux++] = (byte) cor.getGreen();
                    buffer[aux++] = (byte) cor.getBlue();
                    buffer[aux++] = (byte) cor.getAlpha();
                    
                    //int pixel = bi.getRGB(i, j);
                    //buffer[aux++] = (byte)((pixel & 0xFF000000 >> 24));
                    //buffer[aux++] = (byte)((pixel & 0x00FF0000 >> 16));
                    //buffer[aux++] = (byte)((pixel & 0x0000FF00 >> 8));
                    //buffer[aux++] = (byte)((pixel & 0x000000FF >> 0));                                        
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ExploraRobot1 m = new ExploraRobot1(buffer);
        m.setVisible(true);

        System.out.println("Terminou");
    }
}
