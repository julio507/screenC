/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;

public class WorkerBlockCapture {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    final static int BLOCK_X = 20, BLOCK_Y = 20;

    public static void capture() {

        int RES_X = (int) screenSize.getWidth(), RES_Y = (int) screenSize.getHeight();

        byte buffer[] = new byte[ Server.TAM_BUFFER ]; // * 3 = cores RGB

        try {
            int aux = 16;

            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle( RES_X, RES_Y));// pegar toda a minha tela a
                                                                                    // resolução é de cada um, pesquisar
                                                                                    // como saber.., tem

            int iX = 0;
            int iY = 0;

            for (int x = 0; x < RES_X; x++) {
                for (int y = 0; y < RES_Y; y++) {

                    if( ( aux + 4 ) >= Server.TAM_BUFFER || ( y + 1 ) == RES_Y  )
                    {
                        buffer[0] = (byte)((iX >> 24) & 0xff); 
                        buffer[1] = (byte)((iX >> 16) & 0xff);
                        buffer[2] = (byte)((iX >> 8) & 0xff);
                        buffer[3] = (byte)((iX >> 0) & 0xff);

                        buffer[4] = (byte)((iY >> 24) & 0xff); 
                        buffer[5] = (byte)((iY >> 16) & 0xff);
                        buffer[6] = (byte)((iY >> 8) & 0xff);
                        buffer[7] = (byte)((iY >> 0) & 0xff);

                        iX = new Integer( x );
                        iY = new Integer( y );

                        buffer[8] = (byte)((iX >> 24) & 0xff); 
                        buffer[9] = (byte)((iX >> 16) & 0xff);
                        buffer[10] = (byte)((iX >> 8) & 0xff);
                        buffer[11] = (byte)((iX >> 0) & 0xff);

                        buffer[12] = (byte)((iY >> 24) & 0xff); 
                        buffer[13] = (byte)((iY >> 16) & 0xff);
                        buffer[14] = (byte)((iY >> 8) & 0xff);
                        buffer[15] = (byte)((iY >> 0) & 0xff);

                        Server.send( buffer );

                        Thread.sleep( 100 );

                        buffer = new byte[ Server.TAM_BUFFER ]; 

                        aux = 16;

                        if( ( y + 1 ) == RES_Y  )
                        {
                            iY = 0;
                        }
                    }

                    // int cR = ((bi.getRGB(i, j) & 0x00FF0000) >> 16);
                    // int cG = ((bi.getRGB(i, j) & 0x0000FF00) >> 8);
                    // int cB = ((bi.getRGB(i, j) & 0x000000FF) >> 0);
                    Color cor = new Color(bi.getRGB(x, y));

                    buffer[aux++] = (byte) cor.getRed(); // R
                    buffer[aux++] = (byte) cor.getGreen(); // G
                    buffer[aux++] = (byte) cor.getBlue(); // B
                    buffer[aux++] = (byte) cor.getAlpha();// A
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
