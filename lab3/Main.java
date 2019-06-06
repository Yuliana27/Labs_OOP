package lab34;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;

public class Main {
    public static Hotel numFind (int index, ArrayList<Hotel> num){
        Iterator<Hotel> myItr = num.iterator();
        while(myItr.hasNext()){
            Hotel t = myItr.next();
            if(t.getIndex() == index){
                return t;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Hotel> numList = new ArrayList<>();
        ArrayList <Nomer> nomer =new ArrayList<>();

        int command,index;
        while(true){
            System.out.println("1 - додати номер");
            System.out.println("2 - видалити номер");
            System.out.println("3 - переглянути поточний список номерів");
            System.out.println("4 - інформація про заброньований номер");
            System.out.println("5 - записати список номерів у файл");
            System.out.println("6 - витягти список номерів з файлу");
            System.out.println("7 - вийти");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("Введіть паспортні дані людини:");
                    scan.nextLine();
                    Hotel num1 = new Hotel();
                    num1.setPassport(scan.nextLine());
                    numList.add(num1);
                    System.out.println("Номер '"+num1.getPassport()+"' додано з індексом: "+num1.getIndex());
                    break;
                case 2:
                    System.out.println("Введіть індекс номеру, щоб видалити:");
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
                        System.err.println("Такого номеру не знайдено!");
                    }
                    else{
                        System.out.println("Номер видалено із списку!");
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
                    System.out.println("Введіть індекс номеру, щоб додати інформацію про нього: ");
                    index = scan.nextInt();
                    Hotel temp = numFind(index,numList);
                    if(temp != null){
                        System.out.println("Номер знайдено!");
                        boolean exit = false;
                        while(!exit) {
                            System.out.println(temp.toString());
                            System.out.println("1 - Змінити дату поселення:");
                            System.out.println("2 - Змінити дату виселення:");
                            System.out.println("3 - Змінити клас і кількість місць номеру:");
                            System.out.println("4 - Змінити причину поселення:");
                            System.out.println("5 - Повернутись до головного меню:");
                            command  = scan.nextInt();
                            switch(command){
                                case 1:
                                    System.out.println("Введіть нову дату поселення:");
                                    scan.nextLine();
                                    temp.setData_p(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 2:
                                    System.out.println("Введіть нову дату виселення:");
                                    scan.nextLine();
                                    temp.setData_v(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 3:
                                    System.out.println("Номер :");
                                    scan.nextLine();
                                    Nomer nom = new Nomer();
                                    System.out.println("Клас номеру:");
                                    nom.setClass1(scan.nextLine());
                                    System.out.println("К-ть місць:");
                                    nom.setPlace1(scan.nextLine());
                                    //System.out.println(class2);
                                    //System.out.println(place2);
                                    //temp1.setPlace1(scan.nextLine());
                                    temp.addNomer(nom);
                                    System.out.println("Успішно змінено!");
                                    break;

                                case 4:
                                    System.out.println("Введіть нову причину поселення:");
                                    scan.nextLine();
                                    temp.setReason(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 5:
                                    exit = true;
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Номер не знайдено!");
                    }
                    break;
                case 5:
                    FileOutputStream fos;
                    FileOutputStream fos1;
                    System.out.println("Введіть назву файлу:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("Виберіть потрібну папку, щоб зберегти файл:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try
                        {
                            if(newFile.createNewFile())
                                System.out.println("Файл '"+file_name+"' створено!");
                        }
                        catch(IOException ex){
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(path);
                        fos1 = new FileOutputStream(path);
                    }catch(FileNotFoundException ex){
                        System.err.println("Файл не знайдено!");
                        break;
                    }

                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
                    encoder.writeObject((Integer)numList.size());
                    for(Hotel one : numList){
                        encoder.writeObject(one);
                    }
                    encoder.close();
                    XMLEncoder encoder1 = new XMLEncoder(new BufferedOutputStream(fos1));
                    encoder1.writeObject((Integer)nomer.size());
                    for(Nomer one1 : nomer){
                        encoder1.writeObject(one1);
                    }
                    encoder1.close();
                    System.out.println("Успішно записано!");
                    break;
                case 6:
                    System.out.println("1 - Створити новий список, не зберігаючи попередній");
                    System.out.println("2 - Додати до поточного списку");
                    command = scan.nextInt();
                    switch(command){
                        case 1:
                            System.out.println("Виберіть файл:");
                            scan.nextLine();
                            String path_ = FileManager.selectFile(scan);
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(path_);
                            }catch(FileNotFoundException ex){
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
                            Integer size = (Integer) decoder.readObject();
                            numList = new ArrayList<>() ;
                            Hotel.cleanHotel();
                            for (int i = 0; i < size; i++) {
                                numList.add((Hotel) decoder.readObject());
                            }
                            decoder.close();

                            break;
                        case 2:
                            System.out.println("Виберіть файл:");
                            scan.nextLine();
                            String path__ = FileManager.selectFile(scan);
                            FileInputStream fis_;
                            try {
                                fis_ = new FileInputStream(path__);
                            }catch(FileNotFoundException ex){
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
                    System.exit(0);
                    break;

            }
        }
    }

}