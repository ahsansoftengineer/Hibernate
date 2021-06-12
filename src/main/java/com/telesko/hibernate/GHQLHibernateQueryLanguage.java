package com.telesko.hibernate;

import java.util.List;
import java.util.Random;

import com.telesko.school.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HQL is work on Class not on SQL Table and then hibernate Translate that into Native Database Language. HQL is the same for all type of Databases.
@SpringBootApplication
public class GHQLHibernateQueryLanguage {

  public static void main(String[] args) {
    // save();
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Teacher.class);
    SessionFactory factory = cfg.buildSessionFactory();
    Session session = factory.openSession();
    session.beginTransaction();
    // hibernateQueryFromWhere(session);
    // hibernateQuerySelectSingle(session);
    // hibernateQuerySelectMultiple(session);
    // hibernateQuerySelectSingleResult(session);
    // hibernateQueryParameters(session);
    hibernateMap(session);

    session.getTransaction().commit();
  }

  // SQL FROM, WHERE CLAUSE
  public static void hibernateQueryFromWhere(Session session) {
    String sqlQuery = "FROM Teacher";
    sqlQuery = "FROM Teacher WHERE assesment > 70 AND gender = 'Male'";
    List<Teacher> teachers = session.createQuery(sqlQuery, Teacher.class).list();
    for (Teacher teacher : teachers) {
      System.out.println(teacher);
    }
  }

  // SQL SELECT for Single Row
  public static void hibernateQuerySelectSingle(Session session) {
    String sqlQuery = "FROM Teacher";
    sqlQuery = "SELECT name, assesment, gender FROM Teacher WHERE id = 1";
    Query teachers = session.createQuery(sqlQuery);
    Object[] teacherObject = (Object[]) teachers.uniqueResult();
    for (Object teacher : teacherObject) {
      System.out.println(teacher);
    }
  }

  // SQL SELECT for Multiple Row
  public static void hibernateQuerySelectMultiple(Session session) {
    String sqlQuery = "SELECT t.name, t.assesment, t.gender FROM Teacher t WHERE t.id > 90";
    Query teachersQuery = session.createQuery(sqlQuery);
    List<Object[]> teacherObjectList = (List<Object[]>) teachersQuery.list();
    for (Object[] teacherObjects : teacherObjectList) {
      System.out.println(teacherObjects[0] + " : " + teacherObjects[1] + " : " + teacherObjects[2]);
    }
  }

  // SQL SELECT for Selecting Single Item Result
  public static void hibernateQuerySelectSingleResult(Session session) {
    Query teachersQuery = session
      .createQuery("SELECT sum(assesment) FROM Teacher");
    Long totalAssesment = (Long) teachersQuery.uniqueResult();
    System.out.println(totalAssesment);
  }
  // SQL SELECT for Multiple Row
  public static void hibernateQueryParameters(Session session) {
    Query teachersQuery = session
      .createQuery("SELECT sum(assesment) FROM Teacher WHERE ID > :a")
      .setParameter("a", 75);
    Long totalAssesment = (Long) teachersQuery.uniqueResult();
    System.out.println(totalAssesment);
  }
  // SQL SELECT for Multiple Row
  public static void hibernateMap(Session session) {
    Query<Teacher> teachersQuery = session
      .createQuery("FROM Teacher WHERE assesment > 90", Teacher.class); 
    List<Teacher> teachers = teachersQuery.list();
    for(Teacher teacher : teachers){
      System.out.println(teacher);
      // Not Working
      // Map mapTeacher = (Map)teacher;
      // System.out.println(mapTeacher.get("name") + " : " + mapTeacher.get("assesment"));
    }
  }
  public static void save(Session session) {
    // In the Configuration We could have as many class as we want
    Random random = new Random();

    // Saving Laptop
    session.save(new Teacher(51, "Maria", "Female", 10));
    session.save(new Teacher(52, "Kimi", "Female", 20));
    session.save(new Teacher(53, "Ahsan", "Male", 30));
    session.save(new Teacher(54, "Ali", "Male", 40));
    session.save(new Teacher(55, "Arishba", "Female", 50));
    for (int i = 56; i <= 100; i++) {
      int marks = random.nextInt(100);
      if (marks % 2 == 0) {
        session.save(new Teacher(i, "Student" + i, "Male", marks));
      } else {
        session.save(new Teacher(i, "Student" + i, "Female", marks));
      }
    }
  }
}