    package com.telesko.school;

    import java.util.*;
    import javax.persistence.*;

    // import org.hibernate.annotations.Cache;
    // import org.hibernate.annotations.CacheConcurrencyStrategy;

    @Entity
    // @Cacheable
    // @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    public class Student {
      @Id
      private int id;
      private String name;
      @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
      public List<Laptop> laptop = new ArrayList<Laptop>();
      public Student(){}
      public Student(int id, String name){
        this.id = id;
        this.name = name;
      }
      @Override
      public String toString(){
        return " Student : { " + id + " : " + name + " : " + laptop.toString()  + " }"; 
      } 
    }
