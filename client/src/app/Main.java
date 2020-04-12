package app;

public class Main {

    public static void main(String[] args) {
        Client.connect();

        Worker w = new Worker();

        w.setVisible( true );

        while( true )
        {
            w.receive();
            w.repaint();
        }
    }
}