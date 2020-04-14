package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Worker extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    byte buffer[] = null;

    Worker() {
        setExtendedState( JFrame.MAXIMIZED_BOTH );
        setSize(300, 500);
        setTitle("Mostrador");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        if( buffer != null )
        {
            Graphics2D g2 = (Graphics2D) g;

            int scale = 1;
            int aux = 16;

            int startX = convertByteArrayToInt( new byte[] { buffer[0], buffer[1], buffer[2], buffer[3] } );
            int startY = convertByteArrayToInt( new byte[] { buffer[4], buffer[5], buffer[6], buffer[7] } );

            int endX = convertByteArrayToInt( new byte[] { buffer[8], buffer[9], buffer[10], buffer[11] } );
            int endY = convertByteArrayToInt( new byte[] { buffer[12], buffer[13], buffer[14], buffer[15] } );

            if( endY > startY )
            {
                endY = startY + endY;
            }

            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    if( aux + 4 > Client.TAM_BUFFER )
                    {
                        break;
                    }

                    if( y > 1080 )
                    {
                        y = 0;
                    }

                    // porque & 0xFF --> byte -128 a 127 ....
                    // int i = 255; byte b = (byte) i; <- -1
                    // r g b a
                    Color cor = new Color(buffer[aux++] & 0xFF, buffer[aux++] & 0xFF, buffer[aux++] & 0xFF, buffer[aux++] & 0xFF);
                    g2.setColor( cor );
                    g2.drawRect( x + 40, y + 40, scale, scale);
                }
            }
        }
    }

    public void receive() {
        buffer = Client.receive();
    }

    private static int convertByteArrayToInt(byte[] data){
        if (data == null || data.length != 4)
        {
            return 0x0;
        } 
        
        return (int)( // NOTE: type cast not necessary for int
            (0xff & data[0]) << 24  |
            (0xff & data[1]) << 16  |
            (0xff & data[2]) << 8   |
            (0xff & data[3]) << 0
        );
    }
}
