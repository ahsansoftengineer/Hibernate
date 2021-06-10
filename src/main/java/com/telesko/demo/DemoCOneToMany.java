package com.telesko.demo;

import java.util.*;
import com.telesko.types.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 5. Embedable + OneToOne + OneToMany
@SpringBootApplication
public class DemoCOneToMany {
  public static void main(String[] args) {
    // In the Configuration We could have as many class as we want
    Configuration cfg =
      new Configuration().configure()
        .addAnnotatedClass(Department.class)
        .addAnnotatedClass(Employee.class)
        .addAnnotatedClass(Computer.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session  = factory.openSession();
    Transaction transaction = session.beginTransaction();

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

    List<Employee> employees = new ArrayList<Employee>();
    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee3);
    Department department = new Department(1, "HR",employees);
    // Saving Computer
    session.save(computer1);
    session.save(computer2);
    session.save(computer3);
    // Saving Employee
    session.save(employee1);
    session.save(employee2);
    session.save(employee3);
    // Saving Department
    session.save(department);
    transaction.commit();
    // System.out.println(department.toString());
  }
}
