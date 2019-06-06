package lab4;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Hotel numFind(int index, ListContainer<Hotel> num) {
        Iterator<Hotel> myItr = num.iterator();
        while (myItr.hasNext()) {
            Hotel t = myItr.next();
            if (t.getIndex() == index) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        boolean autoMode = false;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-auto")) {
                    autoMode = true;
                }
            }
            ListContainer<Hotel> numList = new ListContainer<>();
            Scanner scan = new Scanner(System.in);
            if (autoMode) {
                try {
                    scan = new Scanner(new BufferedInputStream(new FileInputStream( "E:\\LAB_3\\1.txt")));
                } catch (FileNotFoundException ex) {
                    System.err.println("������������ ����� �� ��������!");
                    scan = new Scanner(System.in);
                }
        }
        int index;
        while (true) {
            if (autoMode) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
            System.out.println("1 - ������ �����");
            System.out.println("2 - �������� �����");
            System.out.println("3 - ����������� �������� ������ ������");
            System.out.println("4 - ���������� ��� ������������� �����");
            System.out.println("5 - �������� ������ ������ � ����");
            System.out.println("6 - ������� ������ ������ � �����");
            System.out.println("7 - ������ ����� �� ��������");
            System.out.println("8 - �����");
            byte command = scan.nextByte();
            switch (command) {
                case 1:
                    System.out.println("������ �������� ��� ������:");
                    scan.nextLine();
                    Hotel num = new Hotel();
                    num.setPassport(scan.nextLine());
                    numList.add(num);
                    System.out.println("����� '"+num.getPassport()+"' ������ � ��������: "+num.getIndex());
                    break;
                case 2:
                    System.out.println("������ ������ ������, ��� ��������:");
                    index = scan.nextInt();

                    if(!numList.remove(index)){
                        System.err.println("������ ������ �� ��������!");
                    }
                    else{
                        System.out.println("����� �������� �� ������!");
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
                    if (temp != null) {
                        System.out.println("����� ��������!");
                        boolean exit = false;
                        while (!exit) {
                            System.out.println(temp.toString());
                            System.out.println("1 - ������ ���� ���������:");
                            System.out.println("2 - ������ ���� ���������:");
                            System.out.println("3 - ������ ���� � ������� ���� ������:");
                            System.out.println("4 - ������ ������� ���������:");
                            System.out.println("5 - ����������� �� ��������� ����:");
                            command = scan.nextByte();
                            switch (command) {
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
                                    System.out.println("������ ����� ���� :");
                                    scan.nextLine();
                                    temp.setClass1(scan.nextLine());

                                    System.out.println("������ ���� �-�� ���� :");
                                    temp.setPlace1(scan.nextLine());
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
                    } else {
                        System.out.println("����� �� ��������!");
                    }
                    break;
                case 5:
                    FileOutputStream fos;
                    System.out.println("----������ ����� �����:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----������� ������� �����, ��� �������� ����:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("���� '" + file_name + "' ��������!");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        System.err.println("���� �� ��������!");
                        break;
                    }
                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
                    encoder.writeObject(numList.size());
                    for (Hotel one : numList) {
                        encoder.writeObject(one);
                    }
                    encoder.close();
                    System.out.println("������ ��������!");
                    break;
                case 6:
                    System.out.println("1 - �������� ����� ������,�� ��������� ���������)");
                    System.out.println("2 - ������ ����� �� ��������� ������");
                    command = scan.nextByte();
                    switch (command) {
                        case 1:
                            System.out.println("������� ����:");
                            scan.nextLine();
                            String path_ = FileManager.selectFile(scan);
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(path_);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
                            Integer size = (Integer) decoder.readObject();
                            numList = new ListContainer<>();
                            Hotel.cleanHotel();
                            for (int i = 0; i < size; i++) {
                               numList.add((Hotel) decoder.readObject());
                            }
                            decoder.close();

                            break;
                        case 2:
                            System.out.println("������� ����:");
                            scan.nextLine();
                            String path__ = FileManager.selectFile(scan);
                            FileInputStream fis_;
                            try {
                                fis_ = new FileInputStream(path__);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(fis_));
                            Integer size_ = (Integer) decoder_.readObject();
                            for (int i = 0; i < size_; i++) {
                                numList.add((Hotel) decoder_.readObject());
                            }
                            decoder_.close();
                            break;
                    }
                    break;
                case 7:
                    System.out.println("������ ������ ������:");
                    index = scan.nextInt();
                    Hotel tem = numFind(index, numList);
                    if (tem != null){
                        System.out.println("����� � ��������  " + index + " - ��\n" + tem); }
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}

