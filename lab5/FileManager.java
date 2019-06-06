package lab5;

import java.io.File;
import java.util.*;

public class FileManager {

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
                System.err.println("���� �������� �� ����! ������� �� ���!");
            }
            System.out.println("������ ��� ����. ���� �� ����������� � ������� �����, �� �������� '/stop'");
            System.out.println("'/prev' - ����������� �����.");
            choice = scan.nextLine();
            if(choice.equals("/stop")){
                return path;
            }
            if(choice.equals("/prev")){
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
                System.err.println("���� �������� ��� ����� �� ����!");
            }
            System.out.println("������ ��� ����.");
            System.out.println("'/prev' - ����������� �����.");
            choice = scan.nextLine();
            if(choice.equals("/prev")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }
        }
    }
}

