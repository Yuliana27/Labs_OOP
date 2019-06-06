package lab6;

public class Count extends Thread {

    static int cCounter;
    @Override
    public void run() {
        cCounter = 0;
        for (String myString : Main.DATA)
        {
            if(myString.contains("c"))
            {
                cCounter++;
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
