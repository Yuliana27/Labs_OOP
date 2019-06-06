package lab6;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> DATA = new ArrayList<>();
    private static long startTime;
    private static long stopTime;
    private static long elapsedTime;
    private static long maxTime;
    private static long startTime2;
    private static long stopTime2;
    public static long time2;

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        System.out.println("Введіть максимальний можливий час: ");
        maxTime = input.nextLong() * 1000;

        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String sCurrentLine;

            while ((sCurrentLine = reader.readLine()) != null) {
               DATA.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Старт");
        System.out.println("<-ПАРАЛЕЛЬНА ОБРОБКА->" + "\n" +
                "-----------------------------------------");

        AmountOfWord first = new AmountOfWord();
        MaxLength second = new MaxLength();
        Count third = new Count();

        startTime = System.currentTimeMillis();
        first.start();
        second.start();
        third.start();
        while (first.isAlive() || second.isAlive() || third.isAlive()) {
            try {
                first.join();
                second.join();
                third.join();
            } catch (InterruptedException e) {

            }
        }


        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        if(elapsedTime>maxTime)
        {
            System.err.println("Перевищено часовий ліміт");
            System.exit(0);
        }
        System.out.println("Результат №1: "+AmountOfWord.ANDcounter+"\n"+"Результат №2: "+MaxLength.MaxLWord+"\n"+"Результат №3: "+Count.cCounter+"\n"+"\n"+"Час(сек): "+elapsedTime/1000+"\n" +
                "\n<-ПОСЛІДОВНА ОБРОБКА->\n"+"-----------------------------------------");

        startTime2 = System.currentTimeMillis();
        second.run();
        first.run();
        third.run();
        stopTime2 = System.currentTimeMillis();
        time2 = stopTime2 - startTime2;

        if(elapsedTime>maxTime)
        {
            System.err.println("Перевищено часовий ліміт");
            System.exit(1);
        }
        System.out.println("Результат №1: "+AmountOfWord.ANDcounter+"\n"+"Результат №2: "+MaxLength.MaxLWord+"\n"+"Результат №3: "+Count.cCounter+"\n"+"\n"+"Час(сек): "+time2/1000+"\n"+
                "-----------------------------------------");
    }
}