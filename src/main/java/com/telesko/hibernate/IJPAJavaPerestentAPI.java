package com.telesko.hibernate;

import javax.persistence.*;
import com.telesko.school.Teacher;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IJPAJavaPerestentAPI {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    
    System.out.println(getTeacher(em));
    // System.out.println(saveTeacher(em));
  }
  private static Teacher getTeacher(EntityManager em){
    return em.find(Teacher.class, 101);
  }
  private static Teacher saveTeacher(EntityManager em){
    Teacher teacher1 = new Teacher(101, "Ayesha", "Female", 90);
    em.getTransaction().begin();
    em.persist(teacher1);
    em.getTransaction().commit();
    return teacher1;

  }
}
