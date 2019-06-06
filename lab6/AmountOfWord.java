package lab6;

public class AmountOfWord extends Thread {

    static int ANDcounter;
    @Override
    public void run() {
        ANDcounter = 0;
        String search = "OOP";
        for (String myString : Main.DATA)
        {
            if (myString.contains(search))
            {
                ANDcounter++;
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
