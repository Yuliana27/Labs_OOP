package lab22;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static Command Code = new Command();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<MyContainer> listOfContainers = new ArrayList<>();
        String text = null;
        byte command;
        while (true) {
            System.out.println("|||||||||||||||||");
            System.out.println("1 - make container");
            System.out.println("2 - fill container");
            System.out.println("3 - clear container");
            System.out.println("4 - show container");
            System.out.println("5 - container menu");
            System.out.println("6 - exit");
            if (!in.hasNextInt()) {
                System.out.println("Unknown command");
                System.exit(0);
            } else {
                command = in.nextByte();
                switch (command) {
                    case 1:
                        System.out.print("enter container's max length: ");
                        listOfContainers.add(new MyContainer(in.nextInt()));
                        System.out.println("success! your container's key is " + listOfContainers.size()  );
                        break;
                    case 2:
                        System.out.print("enter container's key: ");
                        int index = in.nextInt();
                        if (index > listOfContainers.size() || index < 0) {
                            System.out.println("error! not found");
                            break;
                        }
                        System.out.println("Enter " + listOfContainers.get(index - 1).maxSize() + " strings using enter:");
                        in.nextLine();
                        for (int i = 0, n = listOfContainers.get(index - 1).maxSize(); i < n; i++) {
                            listOfContainers.get(index - 1).add(in.nextLine());
                        }
                        System.out.println(text);
                        text = String.valueOf(listOfContainers);
                        System.out.println(text);
                        break;
                    case 3:
                        System.out.print("enter container's key: ");
                        int delIndex = in.nextInt();
                        if (delIndex > listOfContainers.size() || delIndex < 1) {
                            System.out.println("error! not found");
                            break;
                        }
                        listOfContainers.get(delIndex - 1).clear();
                        System.out.println("done!");
                        break;
                    case 4:
                        System.out.print("enter container's key: ");
                        int showIndex = in.nextInt();
                        if (showIndex > listOfContainers.size() || showIndex < 1) {
                            System.out.println("error! not found");
                            break;
                        }
                        System.out.println(listOfContainers.get(showIndex - 1).toString());
                        break;
                    case 5:
                        System.out.print("enter container's key: ");
                        int menuIndex = in.nextInt();
                        if (menuIndex > listOfContainers.size() || menuIndex < 1) {
                            System.out.println("error! not found");
                            break;
                        }
                        System.out.println("|||||||||||||||||");
                        System.out.println("1 - result");
                        System.out.println("2 - size");
                        System.out.println("3 - iterate (for each)");
                        System.out.println("4 - iterate (while)");
                        System.out.println("5 - remove element");
                        System.out.println("6 - add element");
                        System.out.println("7 - check if container includes string");
                        System.out.println("8 - serialize");
                        System.out.println("9 - deserialize");
                        System.out.println("10 - return");
                        command = in.nextByte();
                        switch (command) {
                            case 1:
                                Code.Function(text);
                                System.out.println("\n");
                                break;
                            case 2:
                                System.out.println(listOfContainers.get(menuIndex - 1).size());
                                break;

                            case 3:
                                for (String i : listOfContainers.get(menuIndex-1)) {
                                    System.out.println(i);
                                }
                                break;

                            case 4:
                                MyIterator i = (MyIterator) listOfContainers.get(menuIndex-1).iterator();
                                while (i.hasNext()) {
                                    System.out.println(i.next());
                                }
                                break;

                            case 5:
                                System.out.println("enter element:");
                                in.nextLine();
                                listOfContainers.get(menuIndex-1).remove(in.nextLine());
                                break;

                            case 6:
                                System.out.println("enter element:");
                                in.nextLine();
                                listOfContainers.get(menuIndex-1).add(in.nextLine());
                                break;

                            case 7:
                                System.out.println("enter string to check if container includes it:");
                                in.nextLine();
                                System.out.println(listOfContainers.get(menuIndex-1).contains(in.nextLine()));
                                break;

                            case 8:
                                System.out.println("enter file: ");
                                in.nextLine();
                                listOfContainers.get(menuIndex-1).serialize(in.nextLine());
                                break;

                            case 9:
                                System.out.println("enter file: ");
                                in.nextLine();
                                listOfContainers.get(menuIndex-1).deserialize(in.nextLine());
                                break;

                            case 10:
                                break;
                        }
                        break;
                    case 6:
                        System.out.println("bye!");
                        System.exit(0);
                    default:
                        System.out.println("command not found");
                }
            }
        }
    }
}
