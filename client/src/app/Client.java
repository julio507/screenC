package app;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static final int PORTA = 5000;
    public static final int TAM_BUFFER = 1615;

    private static DatagramSocket clientSocket;

    public static DatagramSocket getInstance()
    {
        try
        {
            if( clientSocket == null )
            {
                clientSocket = new DatagramSocket();
            }
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }
        
        return clientSocket;
    }

    public static void connect()
    {
        try
        {
            byte[] bufferSaida = new byte[TAM_BUFFER];

            InetAddress IpServidor = InetAddress.getByName("127.0.0.1");

            DatagramPacket sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, IpServidor, PORTA);
            getInstance().send(sendPacket);

            DatagramPacket response = new DatagramPacket( bufferSaida, bufferSaida.length, IpServidor, PORTA );
            getInstance().receive( response );

            System.out.println( "connected" + sendPacket.getAddress() );
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static byte[] receive()
    {
        byte[] bufferEntrada = new byte[TAM_BUFFER];

        try {
            DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            getInstance().receive(receivePacket);

            bufferEntrada = receivePacket.getData();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }

        return bufferEntrada;
    }
}