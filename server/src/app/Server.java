package app;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;

public class Server {

    public static final int PORTA = 5000;
    public static final int TAM_BUFFER = 1024;

    public static void main(String[] args) {
        try {

            DatagramSocket serverSocket = new DatagramSocket(PORTA);
            byte[] bufferEntrada = new byte[TAM_BUFFER];
            byte[] bufferSaida = new byte[TAM_BUFFER];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                serverSocket.receive(receivePacket);
/*
                String recebido = new String(receivePacket.getData());
                recebido = recebido.substring( 0, receivePacket.getLength() );

                System.out.println("Recebido: " + recebido);
*/
                InetAddress ipCliente = receivePacket.getAddress(); // Quem me enviou os dados ???
                int portaCliente = receivePacket.getPort();                 // Que porta usou ?

                String mensagem = "404";

                bufferSaida = mensagem.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipCliente, portaCliente);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}