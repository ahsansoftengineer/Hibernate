package com.telesko.types;

import java.util.List;

import javax.persistence.*;

// import javax.persistence.OneToMany;
@Entity
public class Department {
  @Id
  public int id;
  public String name;
  @OneToMany(mappedBy = "department") // This is the Reference of Employee.department
  public List<Employee> employees;
  public Department(){}
  public Department(int id, String name, List<Employee> employees){
    this.id = id;
    this.name = name;

    this.employees = employees;
  }
  // @ManyToMany
}