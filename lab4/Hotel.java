package lab4;

public class Hotel {
    private static int counter = 0;
    private int index;
    private String passport;
    private String data_p;
    private String data_v;
    public String class1;
    public String place1;
    private String reason;

    public Hotel() {
        index = counter++;
    }

    public static void cleanHotel() {
        counter = 0;
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
    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }


    ///////////////////////////////////////////////////////////////////////////////////////
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
        str.append("Паспортні дані: ").append(passport).append("\n");
        str.append("Індекс: ").append(index).append("\n");
        str.append("Дата поселення: ").append(data_p).append("\n");
        str.append("Дата виселення: ").append(data_v).append("\n");
        str.append("Номер: ").append("\n");
        str.append("       ").append("Клас: ").append(class1).append("\n");
        str.append("       ").append("К-ть місць: ").append(place1).append("\n");
        str.append("Причина поселення:").append(reason).append("\n");
        //int count = 0;
        str.append("-----------------------------------------");
        return str.toString();
    }
}
