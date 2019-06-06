package lab7;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Hotel numFind(int id, List<Hotel> num){
        Iterator<Hotel> myItr = num.iterator();
        while(myItr.hasNext()){
            Hotel t = myItr.next();
            if(t.getIndex() == id){
                return t;
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean autoMode = false;
        for(int i = 0;i < args.length;i++){
            if(args[i].equals("-auto")){
                autoMode = true;
            }
        }
        List<Hotel> numList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        if(autoMode){
            try {
                scan = new Scanner(new BufferedInputStream(new FileInputStream("automode.txt")));
            }catch(FileNotFoundException ex){
                System.err.println("������������ ����� �� ��������!");
                scan = new Scanner(System.in);
            }
        }
        int command,index;
        while(true){
            if(autoMode){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){

                }
            }
            System.out.println("1 - ������ �����");
            System.out.println("2 - �������� �����");
            System.out.println("3 - ����������� �������� ������ ������");
            System.out.println("4 - ���������� ��� ������������� �����");
            System.out.println("5 - �������� ������ � XML-����");
            System.out.println("6 - ������� ������ � XML-�����");
            System.out.println("7 - �������� ������ � ����(TXT)");
            System.out.println("8 - ������� ������ � TXT-�����");
            System.out.println("9 - �����");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("������ �������� ��� :");
                    scan.nextLine();
                    Hotel num1 = new Hotel();
                    num1.setPassport(scan.nextLine());
                    numList.add(num1);
                    System.out.println("����� ������ � ����������� ������ '"+num1.getPassport()+"' ������ � ��������: "+num1.getIndex());
                    break;
                case 2:
                    System.out.println("������ ������ ������, ��� ��������:");
                    index = scan.nextInt();
                    Iterator<Hotel> myItr = numList.iterator();
                    boolean isFound = false;
                    while(myItr.hasNext()){
                        Hotel t = myItr.next();
                        if(t.getIndex() == index){
                            myItr.remove();
                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound){
                        System.err.println("������ �������� �� ��������!");
                    }
                    else{
                        System.out.println("������� ��������!");
                    }
                    break;
                case 3:
                    Iterator<Hotel> myItr1 = numList.iterator();
                    while(myItr1.hasNext()){
                        Hotel t = myItr1.next();
                        System.out.println(t.toString());
                    }
                    break;
                case 4:
                    System.out.println("������ ������ ������, ��� ������ ���������� ��� �����: ");
                    index = scan.nextInt();
                    Hotel temp = numFind(index,numList);
                    if(temp != null){
                        System.out.println("����� ��������!");
                        boolean exit = false;
                        while(!exit) {
                            if(autoMode){
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                }catch(InterruptedException e){

                                }
                            }
                            System.out.println(temp.toString());
                            System.out.println("1 - ������ ���� ���������:");
                            System.out.println("2 - ������ ���� ���������:");
                            System.out.println("3 - ������ ���� � ������� ���� ������:");
                            System.out.println("4 - ������ ������� ���������:");
                            System.out.println("5 - ����������� �� ��������� ����:");
                            command  = scan.nextInt();
                            switch(command){
                                case 1:
                                    System.out.println("������ ���� ���� ���������:");
                                    scan.nextLine();
                                    temp.setData_p(scan.nextLine());
                                    System.out.println("������ ������!");
                                    break;
                                case 2:
                                    System.out.println("������ ���� ���� ���������:");
                                    scan.nextLine();
                                    temp.setData_v(scan.nextLine());
                                    System.out.println("������ ������!");
                                    break;
                                case 3:
                                System.out.println("�������������� ������:");
                                scan.nextLine();
                                Nomer nom = new Nomer();
                                System.out.println("��� ������:");
                                nom.setClass1(scan.nextLine());
                                System.out.println("�-�� ����:");
                                nom.setPlace1(scan.nextLine());
                                temp.addNomer(nom);
                                System.out.println("������ ������!");
                                break;
                                case 4:
                                    System.out.println("������ ���� ������� ���������:");
                                    scan.nextLine();
                                    temp.setReason(scan.nextLine());
                                    System.out.println("������ ������!");
                                    break;
                                case 5:
                                    exit = true;
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("������� �� ��������!");
                    }
                    break;
                case 5:
                    XMLEncoder encoder;
                    try {
                        encoder = new XMLEncoder(
                                new BufferedOutputStream(
                                        new FileOutputStream(FileManager.getPath(scan).toString())));
                        encoder.writeObject(numList);
                        encoder.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    XMLDecoder decoder = new XMLDecoder(
                            new BufferedInputStream(
                                    new FileInputStream(FileManager.getPath(scan).toString())));
                    numList =(ArrayList<Hotel>) decoder.readObject();
                    decoder.close();
                break;

                case 7:
                    FileOutputStream outputStream = new FileOutputStream(FileManager.getPath(scan).toString());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(numList);
                    objectOutputStream.close();
                break;

                case 8:
                    FileInputStream fileInputStream = new FileInputStream("E:\\LAB_3\\7.txt");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    numList = (ArrayList<Hotel>)objectInputStream.readObject();
                    objectInputStream.close();
                    break;
                case 9:
                    System.exit(0);
                    break;

            }
        }
    }

}