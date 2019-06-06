package lab22;

import java.io.*;
import java.util.Iterator;

    public class MyContainer implements Iterable<String>, Serializable {
    private String[] data;
    private int currentLength = 0;

    public void add(String s) {
        data[currentLength++] = s;
    }

    public String toString(){
        if (currentLength == 0)
            return null;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < currentLength-1; i++) {
            result.append(data[i]);
        }
        result.append(data[currentLength-1]);
        return new String(result);
    }

    void clear() {
        for (int i = 0; i < currentLength; i++) {
            data[i] = null;
        }
        currentLength = 0;
    }

    boolean remove(String string) {
        for (int i = 0; i < currentLength; i++) {
            if (string.equals(data[i])) {
                currentLength--;
                if (currentLength - i >= 0) System.arraycopy(data, i + 1, data, i, currentLength - i);
                data[currentLength] = null;
                return true;
            }
        }
        return false;
    }

    int size() {
        return currentLength;
    }

    boolean contains(String string) {
        for (int i = 0; i < currentLength; i++)
            if (string.equals(data[i]))
                return true;
        return false;
    }

    int maxSize() {
        return data.length;
    }

    public Iterator<String> iterator() {
        return new MyIterator(data, currentLength);
    }

    boolean serialize(String filename) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println("smth goes wrong with IO");
            return false;
        }
    }

    boolean deserialize(String filename) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return false;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            MyContainer temp = (MyContainer) ois.readObject();
            if (temp.currentLength > this.data.length) {
                System.out.println("too large container");
                return false;
            }
            this.data = temp.data;
            this.currentLength = temp.currentLength;
            return true;
        } catch (IOException e) {
            System.out.println("smth goes wrong with IO");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("wrong file format");
            return false;
        }
    }

    MyContainer(int length) {
        if (length > 0) {
            data = new String[length];
        }
        else
            throw new ExceptionInInitializerError();
    }
}
