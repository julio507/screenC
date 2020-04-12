package app;

public class Main 
{
    public static void main(String[] args) {
        Server.connect();

        while( true )
        {
            WorkerBlockCapture.capture();
        }        
    }
}