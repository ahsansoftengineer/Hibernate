package com.telesko.demo;

import javax.persistence.*;

// There are 3 Layers (Class Name, Entity Name, Table Name)
@Entity // Here we Change Entity Name
@Table(name="Employee") // Here we Change Table Name
public class Employee { 
  @Id // Preventing from Duplication
  // @GeneratedValue(strategy = GenerationType.TABLE) // Auto Generating Ids
  private int id;
  @Column(name="name") // Changing the Column Name
  private String name;
  private String gender;
  private Address address;
  @Transient // Don't Include the in the Database Table
  private String calculateAge;
  public Employee(){
    // This Compulsory for Hibernate while Loading Data
  }
  // For Auto Increment ID
  public Employee(String name, String gender) {
    // Incase if you marked 
    this.name = name;
    this.gender = gender;
  }
  // Without Auto Increment ID
  public Employee(int id, String name, String gender) {
    this.id = id;
    this.name = name;
    this.gender = gender;
  }
  // For @Embadable 
  public Employee(int id, String name, String gender, Address address) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
  }
  @Override // By Default toString HashCode for Class
  public String toString(){
    return id + " : " + name + " : " + gender + " : Address [ " + address.toString() + " ]"; 
  }

}
