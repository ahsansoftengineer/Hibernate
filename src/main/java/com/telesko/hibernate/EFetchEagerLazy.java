package com.telesko.hibernate;

import com.telesko.school.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EFetchEagerLazy {
  public static void main(String[] args) {
    // save();
    fetch();
  }

  public static void fetch() {
    Configuration cfg = new Configuration().configure()
      .addAnnotatedClass(Student.class)
      .addAnnotatedClass(Laptop.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session = factory.openSession();
    session.beginTransaction();
    // Default Behaviour
    Student student = session.get(Student.class, 2);

    System.out.println(student.toString());
    session.getTransaction().commit();
  }

  public static void save() {
    // In the Configuration We could have as many class as we want
    Laptop laptop1 = new Laptop(1, "HP");
    Laptop laptop2 = new Laptop(2, "Dell");
    Laptop laptop3 = new Laptop(3, "Lenovo");
    Laptop laptop4 = new Laptop(4, "Toshiba");
    Laptop laptop5 = new Laptop(5, "Honda");

    Student student1 = new Student(1, "Ahsan");
    Student student2 = new Student(2, "Asim");
    Student student3 = new Student(3, "Mobin");
    Student student4 = new Student(4, "Yousuf");

    // Setting Student to Laptop
    laptop1.student = student1;
    laptop2.student = student2;
    laptop3.student = student3;
    laptop4.student = student1;
    // Entity Framework Options
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Student.class)
        .addAnnotatedClass(Laptop.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    // Saving Student
    session.save(student1);
    session.save(student2);
    session.save(student3);
    session.save(student4);

    // Saving Laptop
    session.save(laptop1);
    session.save(laptop2);
    session.save(laptop3);
    session.save(laptop4);
    session.save(laptop5);
    transaction.commit();
  }
}
