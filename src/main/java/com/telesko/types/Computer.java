package com.telesko.types;

import javax.persistence.*;

// This will Create OneToOne Relation Ship
@Entity
public class Computer {
  @Id
  public int id;
  public String generation;
  public String model;
  @ManyToOne
  public Employee employee;
  public Computer(){}
  public Computer(int id, String generation, String model){
    this.id = id;
    this.generation = generation;
    this.model = model;
  }
  @Override
  public String toString(){
    return id + " : " + generation + " : " + model;
  }
}
