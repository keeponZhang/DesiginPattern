package com.ciwong.reflect;

import android.util.Log;

public class Person {
    private int age;
    private String name;
    private COLOR color;

    public static enum COLOR{WHITE,BLACK,YELLOW}
    public Person(){
 
    }
    private Person(int age, String name){
        this.age = age;
        this.name = name;
    }
 
    private Person(Integer age, String name){
        this.age = age;
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean testInvoke(Integer age,String name){
        Log.e("qijian","得到参数age:"+age +"   name:"+name);
        return true;
    }
}