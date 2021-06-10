package com.telesko.demo;

import java.util.*;

import com.telesko.types.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 6. Embedable + OneToOne + OneToMany + ManyToMany
@SpringBootApplication
public class DemoDManyToMany {
  public static void main(String[] args) {
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
      // Before Starting Keep in mind the following Parts
      // First Create all the Objects and then Provide the objects to Parent and then Save all the Objects Individually
      Computer computer1  =  new Computer(1, "7th", "HP");
      Computer computer2  =  new Computer(2, "8th", "Lenovo");
      Computer computer3  =  new Computer(3, "9th", "Dell");
      Employee employee1 = new Employee(
        1, "Rafiqan", "Female",
        new Address("7th", "Karachi", 74900),
       computer1
      );
      Employee employee2 = new Employee(
        2, "Ahsan", "Male",
        new Address("6th", "Hyderabad", 74900),
        computer2
      );
      Employee employee3 = new Employee(
        3, "Abdul Sami", "Male",
        new Address("8th", "Dubai", 74900),
        computer3
      );
      // Creating Objects & List of Skills 
      Skill skill1 = new Skill(1, "Java", "10-10-2021");
      Skill skill2 = new Skill(2, "PHP", "01-02-2021");
      Skill skill3 = new Skill(3, "Lareval", "10-05-2021");


      List<Employee> employees = new ArrayList<Employee>();
      employees.add(employee1);
      employees.add(employee2);
      employees.add(employee3);
      // Setting employee Object to Skills
      skill1.employee.add(employee1);
      skill2.employee.add(employee2);
      skill2.employee.add(employee3);
      skill3.employee.add(employee3);
      // Setting Skill Object to Employee
      employee1.skill.add(skill1);
      employee2.skill.add(skill2);
      employee3.skill.add(skill2);
      employee3.skill.add(skill3);
      // Creating Department and Setting Employees to Department 
      Department department = new Department(1, "HR",employees);
      // Setting Department to Employee
      employee1.department = department;
      employee2.department = department;
      employee3.department = department;
      
      // Saving Department
      session.save(department);
      // Saving Computer
      session.save(computer1);
      session.save(computer2);
      session.save(computer3);
      // Saving Employee
      session.save(employee1);
      session.save(employee2);
      session.save(employee3);

      // Saving Skills
      session.save(skill1);
      session.save(skill2);
      session.save(skill3);
      transaction.commit();
      // System.out.println(department.toString());
    }
  }
