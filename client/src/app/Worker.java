package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Worker extends JFrame {

    byte buffer[] = null;

    Worker() {

        setSize(320, 200);
        setTitle("Mostrador");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
                // int i = 255; byte b = (byte) i; <- -1
                // r g b a
                Color cor = new Color(buffer[aux++] & 0xFF, buffer[aux++] & 0xFF, buffer[aux++] & 0xFF,
                        buffer[aux++] & 0xFF);
                g2.setColor(cor);
                g2.drawRect(offset + i * scale, offset + j * scale, scale, scale);
            }
        }
    }

    public void receive() {
        final int BLOCK_X = 20;
        final int BLOCK_Y = 20;

        buffer = new byte[BLOCK_X * BLOCK_Y * 4]; // a r g b (a=alpha)

        buffer = Client.receive();
    }
}
