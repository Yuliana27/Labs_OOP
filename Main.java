package lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Function fun = new Function();
        Menu menu = new Menu();
        // ініціалізація всіх початкових змінних
        Scanner in = new Scanner(System.in);
        String input = null;//те,що будемо вводити
        String command;
        int count_sentences = 0;//змінна, що відповідає за кількість речень(елементів масиву sentences)
        boolean debug = false;
        StringBuilder []sentences = new StringBuilder[10];//створюємо масив з реченнями

        // перевірка аргументів командного рядка
        if(args != null && args.length == 1){
            if(args[0].equals("-d")){
                debug = true;
            }
            menu.Menu1(args);
        }

        // обробка команд
        do{
            command = in.nextLine();

            if(command.equals("a")){
                System.out.print("Введіть вхідні дані [текст <Enter>]: ");
                input = in.nextLine();
                //ділимо введений текст на окремі речення

                StringBuilder one_sent = new StringBuilder();
                for(int i = 0; i<input.length(); i++){
                    //(всі елементи до крапки вважаються реченням і їх додаємо в масив)
                    if(input.charAt(i) != '.') one_sent.append(input.charAt(i));

                    else if(input.charAt(i) == '.') {
                        sentences[count_sentences] = one_sent;
                        count_sentences++;
                        one_sent = new StringBuilder();
                    }
                }

            }
            //коли напишемо b на екрані побачимо текст, що розбитий на окремі речення
            if(command.equals("b")){
                if(input != null){
                    for(int k=0; k<count_sentences; k++){
                        System.out.println(sentences[k].toString());
                    }
                }
                else{
                    System.out.println("Відсутні вхідні дані");
                }
            }

            if(command.equals("c")) {

                    fun.Code(input, count_sentences, sentences,debug);
            }

        }
        // d - просто завершує програму
        while (!command.equals("d"));

        System.exit(0);
    }
}