package com.telesko.types;
import java.util.ArrayList;
import java.util.List;

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
  @Transient // Don't Include the in the Database Table
  private String calculateAge;

  private Address address; // This will Save Address in the Employee Table
  @OneToOne // This will Create Two Different Table
  private Computer computer;
  @ManyToOne // Here We don't required mappedby Parameter it will be done by Department
  public Department department;
  @ManyToMany(mappedBy = "employee")
  public List<Skill> skill = new ArrayList<Skill>();
  // Constructors
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
  // For @Embadable Address
  public Employee(int id, String name, String gender, Address address) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
  }
  // For @OneToOne with Skills
  public Employee(int id, String name, String gender, Address address, Computer computer) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.computer = computer;
  }
  // For @ManyToMany
  public Employee(int id, String name, String gender, Address address, Computer computer, List<Skill> skills) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.computer = computer;
    this.skill = skills;
  }

  @Override // By Default toString HashCode for Class
  public String toString(){
    return id + " : " + name + " : " + gender + " : Address [ " + address.toString() + " ]"
    + " : Computer [ " + computer.toString() + " ]" + " : Skills [ " + skill.toString() + " ]"; 
  }
}
