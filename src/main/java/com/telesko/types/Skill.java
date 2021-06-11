package com.telesko.types;

import java.util.*;

import javax.persistence.*;

@Entity
public class Skill {
  @Id
  private int id;
  private String name;
  private String date;
  @ManyToMany()
  public List<Employee> employee = new ArrayList<Employee>();
  public Skill(){}
  public Skill(int id, String name, String date){
    this.id = id;
    this.name = name;
    this.date = date;
  }
  public Skill(int id, String name, String date, List<Employee> employees){
    this.id = id;
    this.name = name;
    this.date = date;
    this.employee = employees;
  }
  @Override
  public String toString(){
    return " Skill : { " + id + " : " + name + " : " + date + " }";
  }
}
