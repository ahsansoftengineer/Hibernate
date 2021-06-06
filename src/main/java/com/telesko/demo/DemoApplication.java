package com.telesko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
    // System.out.println("Working...");
    Employee emp = new Employee(3, "Farzana", "Female");
    System.out.println(emp.toString());
      // System.out.println("Project Started...");
      // SpringApplication.run(LearnApplication.class, args);
      Configuration cfg =
       new Configuration().configure().addAnnotatedClass(Employee.class);
      SessionFactory factory = cfg.buildSessionFactory(); // Deprecated
      Session session  = factory.openSession();
      // When ever you are changing in the Database youu have to follow the 
      // Acid Properties (Atomicity, Consistency, Isolation and Durability)
      // Which means All of this things should be the part of Transactions.
      Transaction transaction = session.beginTransaction();
      session.save(emp);
      transaction.commit();
	}

}
