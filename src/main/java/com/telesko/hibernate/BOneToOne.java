package com.telesko.hibernate;

import com.telesko.types.Address;
import com.telesko.types.Computer;
import com.telesko.types.Employee;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 4. Embedable + OneToOne
@SpringBootApplication
public class BOneToOne {
  public static void main(String[] args) {
    // In the Configuration We could have as many class as we want
    Configuration cfg =
      new Configuration().configure()
        .addAnnotatedClass(Employee.class)
        .addAnnotatedClass(Computer.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session  = factory.openSession();
    Transaction transaction = session.beginTransaction();
    Computer computer = new Computer(1, "7th", "HP");
    Employee employee = new Employee(
      1, "Rukhsana", "Female",
      new Address("8th Floor", "Karachi", 74900),
      computer
    );
    session.save(computer);
    session.save(employee);
    transaction.commit();
    // System.out.println(employee.toString());
  }
}
