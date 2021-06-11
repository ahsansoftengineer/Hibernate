package com.telesko.school;

import javax.persistence.*;

@Entity
public class Laptop {
  @Id
  private int id;
  private String name;
  @ManyToOne
  public Student student;
  public Laptop(){}
  public Laptop(int id, String name){
    this.id = id;
    this.name = name;
  }
  @Override
  public String toString(){
    return " Laptop : { " + id + " : " + name + " }"; 
  }

}
