package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();
			// create a course
			Course tempCourse = new Course("PacMan - How to Score One Million points");
			// save the course...
			System.out.println("saving the course...");
			session.save(tempCourse);
			System.out.println("saved the course" + tempCourse);
			// create the students
			Student tempStudent1 = new Student("Thasir", "Ahmed", "ahmedshaik93@gmail.com");
			Student tempStudent2 = new Student("John", "Doe", "john@gmail.com");
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			// save the students
			System.out.println("\n saving the studnets...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("saved students: " + tempCourse.getStudents());
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
