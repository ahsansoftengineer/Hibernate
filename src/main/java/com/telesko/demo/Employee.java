package com.telesko.demo;

import javax.persistence.*;

// There are 3 Layers (Class Name, Entity Name, Table Name)
@Entity // Here we Change Entity Name
@Table(name="Employee") // Here we Change Table Name
public class Employee { 
  @Id // Preventing from Duplication
  private int id;
  @Column(name="name") // Changing the Column Name
  private String name;
  private String gender;
  @Transient // Don't Include the in the Database Table
  private String calculateAge;
  public Employee(int id, String name, String gender) {
    this.id = id;
    this.name = name;
    this.gender = gender;
  }
  public Employee(String name, String gender) {
    this.name = name;
    this.gender = gender;
  }
  @Override // By Default toString HashCode for Class
  public String toString(){
    return id + " : " + name + " : " + gender; 
  }

}
