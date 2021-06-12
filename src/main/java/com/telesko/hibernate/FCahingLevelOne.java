package com.telesko.hibernate;

import com.telesko.school.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FCahingLevelOne {
  public static void main(String[] args) {
    // save();
    firstLevelCacheSession();
    // Both are not working due to Hibernate Version Conflicts 
    // secondLevelCacheSession(); 
    // secondLevelCacheWithQuery();
  }

  public static void firstLevelCacheSession() {
    Configuration cfg = new Configuration().configure()
      .addAnnotatedClass(Student.class)
      .addAnnotatedClass(Laptop.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    Session session = factory.openSession();
    session.beginTransaction();
    // Default Behaviour
    Student student = session.get(Student.class, 2);
    Student student1 = session.get(Student.class, 2);

    System.out.println(student.toString());
    System.out.println(student1.toString());
    session.getTransaction().commit();
  }
  
  public static void secondLevelCacheSession() {
    // Undermentioned Dependencies Required for working with ehcache for second level
    // net.sf.ehcache ehcache
    // org.hibernate hibernate-ehcache
    Configuration cfg = new Configuration().configure()
      .addAnnotatedClass(Student.class)
      .addAnnotatedClass(Laptop.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    // Session 1
    Session session1 = factory.openSession();
    session1.beginTransaction();
    Student student1 = session1.get(Student.class, 1);
    System.out.println(student1.toString());
    session1.getTransaction().commit();
    session1.close();

    // Session 2
    Session session2 = factory.openSession();
    session2.beginTransaction();
    Student student2 = session2.get(Student.class, 2);
    System.out.println(student2.toString());
    session2.getTransaction().commit();
    session2.close();
  }

  public static void secondLevelCacheWithQuery() {
    // Undermentioned Dependencies Required for working with ehcache for second level
    // net.sf.ehcache ehcache
    // org.hibernate hibernate-ehcache
    Configuration cfg = new Configuration().configure()
      .addAnnotatedClass(Student.class)
      .addAnnotatedClass(Laptop.class);
    SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
    // Session 1
    Session session1 = factory.openSession();
    session1.beginTransaction();
    // Student student1 = session1.get(Student.class, 1);
    Student student1 =  session1.createQuery("from Student where id = 1", Student.class).setCacheable(true).uniqueResult();
    
    System.out.println(student1.toString());
    session1.getTransaction().commit();
    session1.close();

    // Session 2
    Session session2 = factory.openSession();
    session2.beginTransaction();
    // Student student2 = session2.get(Student.class, 2);
    Student student2 =  session1.createQuery("from Student where id = 1", Student.class).setCacheable(true).uniqueResult();
    
    System.out.println(student2.toString());
    session2.getTransaction().commit();
    session2.close();
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
