package cuongtran;

import java.io.Serializable;

public class Student implements Serializable {
    private int number;
    private String name, klass, dob, province;

    public Student(int number, String name, String klass, String dob, String province) {
        this.number = number;
        this.name = name;
        this.klass = klass;
        this.dob = dob;
        this.province = province;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "| " + this.number + "\t" + this.name + "\t" + this.klass + "\t" + this.dob + "\t" + this.province + " |";
    }
}
