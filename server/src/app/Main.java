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
            }
        }
        
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}