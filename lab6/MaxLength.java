package lab6;

public class MaxLength extends Thread {

    static String MaxLWord;
    @Override
    public void run() {
        MaxLWord = "";
        for (String check : Main.DATA)
        {
            if (check.length() > MaxLWord.length())
            {
                MaxLWord = check;
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


