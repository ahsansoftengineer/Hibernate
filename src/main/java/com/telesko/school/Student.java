package com.telesko.school;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Student {
  @Id
  private int id;
  private String name;
  @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
  public List<Laptop> laptop = new ArrayList<Laptop>();
  public Student(){}
  public Student(int id, String name){
    this.id = id;
    this.name = name;
  }
  @Override
  public String toString(){
    return " Student : { " + id + " : " + name + " : " + laptop.toString()  + " }"; 
  } 
}
