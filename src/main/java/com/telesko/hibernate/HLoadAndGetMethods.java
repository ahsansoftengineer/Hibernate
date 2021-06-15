package com.telesko.hibernate;

import com.telesko.school.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HQL is work on Class not on SQL Table and then hibernate Translate that into Native Database Language. HQL is the same for all type of Databases.
@SpringBootApplication
public class HLoadAndGetMethods {

  public static void main(String[] args) {
    // save();
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Teacher.class);
    SessionFactory factory = cfg.buildSessionFactory();
    Session session = factory.openSession();
    session.beginTransaction();

    // hibernateGet(session);
    hibernateLoad(session);

    session.getTransaction().commit();
  }

  // Get Method return Null Object and doesn't throw Exception
  // Get Method hit database when it calls
  public static void hibernateGet(Session session) {
    Teacher teacher = session.get(Teacher.class, 101); // Giving you actual Object
    System.out.println(teacher); // Hitting Database
  }
    // Get Method not return Null Object instead throw Exception
    // Load Method doesn't excute until the object is utilize
    public static void hibernateLoad(Session session) {
      Teacher teacher = session.load(Teacher.class, 101); // Giving you proxy Object
      System.out.println(teacher); // Not hitting Database
    }
}