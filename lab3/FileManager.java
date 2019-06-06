package lab34;

import java.io.File;
import java.util.*;

public interface FileManager {
    public static String selectDir(Scanner scan){
        boolean end = false;
        String path = "E:\\";
        String choice;
        while(!end){
            File dir = new File(path);
            if(dir.isDirectory()){
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println(item.getName());
                    }
                }
            }
            else{
                System.err.println("Такої папки не існує! Виберіть ще раз!");
            }
            System.out.println("Зробіть свій вибір. Якщо ви знаходитесь в потрібній папці, то напишіть '/стоп'");
            System.out.println("'/повернутись назад' - повернутись на попередній крок.");
            choice = scan.nextLine();
            if(choice.equals("/стоп")){
                return path;
            }
            if(choice.equals("/повернутись назад")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }

        }
        return null;
    }
    public static String selectFile(Scanner scan){
        String path = "E:\\";
        String choice;
        while(true){
            File dir = new File(path);
            if(dir.isDirectory()){
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        System.out.println(item.getName() + "\t Dir");
                    }
                    else{
                        System.out.println(item.getName() + "\t File");
                    }
                }
            }
            else if(dir.isFile()){
                return path ;
            }
            else{
                System.err.println("Такої папки або файлу не існує!");
            }
            System.out.println("Зробіть свій вибір.");
            System.out.println("'/повернутись назад' - повернутись назад.");
            choice = scan.nextLine();
            if(choice.equals("/повернутись назад")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }
        }
    }
}

