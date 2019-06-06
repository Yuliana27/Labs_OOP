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
                System.err.println("���� ����� �� ����! ������� �� ���!");
            }
            System.out.println("������ ��� ����. ���� �� ����������� � ������� �����, �� �������� '/����'");
            System.out.println("'/����������� �����' - ����������� �� ��������� ����.");
            choice = scan.nextLine();
            if(choice.equals("/����")){
                return path;
            }
            if(choice.equals("/����������� �����")){
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
                System.err.println("���� ����� ��� ����� �� ����!");
            }
            System.out.println("������ ��� ����.");
            System.out.println("'/����������� �����' - ����������� �����.");
            choice = scan.nextLine();
            if(choice.equals("/����������� �����")){
                path = dir.getParent();
            }
            else {
                path += "\\" + choice;
            }
        }
    }
}

