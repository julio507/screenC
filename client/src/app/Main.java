package app;

public class Main {

    public static void main(String[] args) 
    {
        Worker w = new Worker();

        w.setVisible( true );
        
        Client.connect();
        
        try
        {
            while( true )
            {
                w.receive();
                w.repaint();

                Thread.sleep( 5000 );
            }
        }

        catch( Exception e)
        {
            e.printStackTrace();
        }
    }
}