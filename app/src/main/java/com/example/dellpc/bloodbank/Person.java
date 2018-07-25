package com.example.dellpc.bloodbank;

public class Person {
    private String name;
    private String blood;
    private String address;
    public Person(String name, String blood, String address){
        this.name=name;
        this.blood=blood;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress(){ return address; }

    public void setAddress(String address){this.address=address;}
}
