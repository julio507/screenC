package app;

public class Main 
{
    public static void main(String[] args) 
    {
        Server.connect();

        try
        {
            while( true )
            {
                WorkerBlockCapture.capture();

                Thread.sleep( 5000 );
            }
        }
        
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}