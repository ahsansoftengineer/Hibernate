package com.telesko.school;

public class Alien {
  private int id;
  private String name;
  private String gender;
  Alien(){}
  Alien(int id, String name, String gender){
    this.id = id;
    this.name = name;
    this.gender = gender;
  }
  @Override
  public String toString(){
    return " Alien { " + id +  " : " + name + " : " + gender + " }";
  }
}
