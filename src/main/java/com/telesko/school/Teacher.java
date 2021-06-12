package com.telesko.school;

import javax.persistence.*;

@Entity
public class Teacher {
  @Id
  private int id;
  private String name;
  private String gender;
  private int assesment;
  public Teacher(){}
  public Teacher(int id, String name, String gender, int assesment){
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.assesment = assesment;
  }
  @Override
  public String toString(){
    return "Teacher : { " + id + " : " + name + " : " + gender + " : " + assesment + " }"; 
  }
}
