package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;


public class GetCourseandReviewsDemo {

	public static void main(String[] args) {
		
		//create session factory ( created only once that produces all sessions )
		
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.buildSessionFactory();
		
		//create session
		
		Session session = sessionFactory.getCurrentSession();

		try {
			
			
			//begin the transaction 
			session.beginTransaction();
			
			//get the course
			int id = 12;
			Course tempCourse = session.get(Course.class,id);
			
			System.out.println("Course onject: "+tempCourse);
			
			//PRINT COURSE REVIEWS
			System.out.println("Course reviews: "+tempCourse.getReviews());
			
			//commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}

	}

}
