package lab34;

import java.util.ArrayList;
import java.io.Serializable;

public class Hotel implements Serializable {
    private static int counter = 0;
    private int index;
    private String passport;
    private String data_p;
    private String data_v;
    private String reason;
    public ArrayList <Nomer> nomers;

    public Hotel() {
        nomers = new ArrayList<>();
        index = counter++;
    }

    public static void cleanHotel() {
        counter = 0;
    }

    public void addNomer(Nomer nom){
        nomers.add(nom);
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getData_p() {
        return data_p;
    }

    public void setData_p(String data_p) {
        this.data_p = data_p;
    }

    public String getData_v() {
        return data_v;
    }

    public void setData_v(String data_v) {
        this.data_v = data_v;
    }
    //////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------------\n");
        str.append("�������� ���: ").append(passport).append("\n");
        str.append("������: ").append(index).append("\n");
        str.append("���� ���������: ").append(data_p).append("\n");
        str.append("���� ���������: ").append(data_v).append("\n");
        str.append("������� ���������:").append(reason).append("\n");
        int count = 0;
        for(Nomer nom : nomers){
            str.append("\t").append(nom.toString()).append("\n");
        }
        str.append("-----------------------------------------");
        return str.toString();
    }
}

