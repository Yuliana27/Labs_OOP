package lab8;

import java.io.Serializable;

public class Nomer implements Serializable {
    public String class1;
    public String place1;



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

    public String toString() {
        return new String("Клас номеру: " + class1 + ", К-ть місць: " +place1 );
    }
}