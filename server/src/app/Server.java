package app;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static final int PORTA = 5000;
    public static final int TAM_BUFFER = 15 + ( WorkerBlockCapture.BLOCK_X * WorkerBlockCapture.BLOCK_Y * 4 );

    private static DatagramSocket serverSocket;

    private static InetAddress ipCliente;
    private static int portaCliente;

    public static DatagramSocket getInstance()
    {
        try
        {
            if( serverSocket == null )
            {
                serverSocket = new DatagramSocket(PORTA);
            }
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return serverSocket;
    }

    public static void connect()
    {
        try
        {
            while( ipCliente == null )
            {
                byte[] bufferEntrada = new byte[TAM_BUFFER];

                DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                getInstance().receive(receivePacket);

                getInstance().send( receivePacket );

                ipCliente = receivePacket.getAddress();
                portaCliente = receivePacket.getPort();

                System.out.println( "conected" + ipCliente.toString() );
            }
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }

    public static void send( byte[] output )
    {
        try
        {
            DatagramPacket sendPacket = new DatagramPacket(output, output.length, ipCliente, portaCliente);
            getInstance().send(sendPacket);
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

}