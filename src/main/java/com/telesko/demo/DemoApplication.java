package com.telesko.demo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    Configuration cfg =
    new Configuration().configure().addAnnotatedClass(Employee.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    // When ever you are changing in the Database youu have to follow the 
    // Acid Properties (Atomicity, Consistency, Isolation and Durability)
    // Which means All of this things should be the part of Transactions.
    Session session  = factory.openSession();
    Transaction transaction = session.beginTransaction();
    // 1. Add Employee
    // Employee employee = new Employee(2, "Ahsan", "Male");
    // session.save(employee);

    // 2. Get Single Employee
    // Employee employee = (Employee) session.get(Employee.class, 1);

    // 3. Embeddable Employee + Address 
    Employee employee = new Employee(3, "Sumaya", "Female", new Address("6th", "Karachi", 74900));
    session.save(employee);

    transaction.commit();
    System.out.println(employee.toString());
  }
}
