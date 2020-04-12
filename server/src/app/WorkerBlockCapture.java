/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class WorkerBlockCapture {

    public static void capture() {
        final int BLOCK_X = 20, BLOCK_Y = 20;
        byte buffer[] = new byte[BLOCK_X * BLOCK_Y * 3]; // * 3 = cores RGB
        try {
            int aux = 0;

            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(1920, 1080));// pegar toda a minha tela a
                                                                                    // resolução é de cada um, pesquisar
                                                                                    // como saber.., tem

            for (int i = 0; i < BLOCK_Y; i++) {
                for (int j = 0; j < BLOCK_X; j++) {

                    // int cR = ((bi.getRGB(i, j) & 0x00FF0000) >> 16);
                    // int cG = ((bi.getRGB(i, j) & 0x0000FF00) >> 8);
                    // int cB = ((bi.getRGB(i, j) & 0x000000FF) >> 0);
                    Color cor = new Color(bi.getRGB(i, j));

                    buffer[aux++] = (byte) cor.getRed(); // R
                    buffer[aux++] = (byte) cor.getGreen(); // G
                    buffer[aux++] = (byte) cor.getBlue(); // B

                    Server.send(buffer);
                    // buffer[aux++] = (byte) ((bi.getRGB(i, j) & 0xFF000000) >> 24); //
                    // Transparencia, modificar para x 4 o tamanhodo buffer
                    // System.out.print("(" + cR + " " + cG + " " + cB + ")");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
