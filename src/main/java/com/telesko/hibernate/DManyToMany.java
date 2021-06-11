package com.telesko.hibernate;

import com.telesko.types.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 6. Embedable + OneToOne + OneToMany + ManyToMany
@SpringBootApplication
public class DManyToMany {
  public static void main(String[] args) {
    // Before Starting Keep in mind the following Parts
    // First Create all the Objects and then Provide the objects to Parent and then Save all the Objects Individually
    // 1. Creating Department Object
    Department department = new Department(1, "HR");
    // Creating Computer Object
    Computer computer1  =  new Computer(1, "7th", "HP");
    Computer computer2  =  new Computer(2, "8th", "Lenovo");
    Computer computer3  =  new Computer(3, "9th", "Dell");
    // Creating Employee Object
    Employee employee1 = new Employee(
      1, "Rafiqan", "Female",
      new Address("7th", "Karachi", 74900)
    );
    Employee employee2 = new Employee(
      2, "Ahsan", "Male",
      new Address("6th", "Hyderabad", 74900)
    );
    Employee employee3 = new Employee(
      3, "Abdul Sami", "Male",
      new Address("8th", "Dubai", 74900)
    );
    // Creating Skill Objects 
    Skill skill1 = new Skill(1, "Java", "10-10-2021");
    Skill skill2 = new Skill(2, "PHP", "01-02-2021");
    Skill skill3 = new Skill(3, "Lareval", "10-05-2021");

    // 1-* Department is not Responsible for Employee due to mappedBy
    // department.employee.add(employee1);
    // department.employee.add(employee2);
    // department.employee.add(employee3);

    // *-1 Setting Department to Employee (because it has to set id)
    employee1.department = department;
    employee2.department = department;
    employee3.department = department;

    // *-* Setting employee Object to Skills (because it has to set id for both)
    skill1.employee.add(employee1);
    skill2.employee.add(employee2);
    skill2.employee.add(employee3);
    skill3.employee.add(employee3);

    // *-* Employee not responsible for Skill due to mappedBy
    // employee1.skill.add(skill1);
    // employee2.skill.add(skill2);
    // employee3.skill.add(skill2);
    // employee3.skill.add(skill3);

    // 1-1 Employee is responsible for computer because it has to set it 
    employee1.computer = computer1;
    employee2.computer = computer2;
    employee3.computer = computer3;
    
    // In the Configuration We could have as many class as we want
    Configuration cfg =
    new Configuration().configure()
      .addAnnotatedClass(Department.class)
      .addAnnotatedClass(Skill.class)
      .addAnnotatedClass(Employee.class)
      .addAnnotatedClass(Computer.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session  = factory.openSession();
    Transaction transaction = session.beginTransaction();
    // Saving Department
    session.save(department);

    // Saving Employee
    session.save(employee1);
    session.save(employee2);
    session.save(employee3);

    // Saving Skills
    session.save(skill1);
    session.save(skill2);
    session.save(skill3);

    // Saving Computer
    session.save(computer1);
    session.save(computer2);
    session.save(computer3);

    // If You want to Print then you have to Uncomment the Unnessary Part for ORM
    System.out.println(department.toString());
    transaction.commit();

  }
}
