/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explorarobot;

import java.awt.Robot;

public class PegaPixel {

    public static void main(String[] args) {

        final int BLOCK_X = 20;
        final int BLOCK_Y = 20;

        int buffer[] = new int[BLOCK_X * BLOCK_Y * 3]; // * 3 = cores RGB

        try {
            int aux = 0;

            Robot robot = new Robot();

            for (int i = 0; i < BLOCK_Y; i++) {
                for (int j = 0; j < BLOCK_X; j++) {

                    buffer[aux++] = robot.getPixelColor(i, j).getRed();
                    buffer[aux++] = robot.getPixelColor(i, j).getGreen();
                    buffer[aux++] = robot.getPixelColor(i, j).getBlue();

                    System.out.print("(" + robot.getPixelColor(i, j).getRed() + " " + robot.getPixelColor(i, j).getGreen() + " " + robot.getPixelColor(i, j).getBlue() + ")");
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
