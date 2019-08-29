package com.authservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate5.SessionFactoryUtils;


import com.login.model.User;


public class AuthService {

	private static Logger log = Logger.getLogger(AuthService.class);
	 @SuppressWarnings( { "unchecked", "deprecation" } )
	    public boolean findUser(String uname, String upwd) {
	        log.info("Checking the user in the database");
	        boolean isValidUser = false;
	        SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
	        Session session = factory.getCurrentSession();
	        System.out.println(" Recieved Username: "+uname+" Password: "+upwd);
	        try {
	        	session.beginTransaction();
	        	List<User> theUser = session.createQuery("from User  where name='"+uname+"' and password='"+upwd+"'").getResultList();
	        	
	            	if(theUser !=null && theUser.size()>0) {
	            		log.info("ID: "+theUser.get(0).getId()+" Name: "+theUser.get(0).getName()+" password: "+theUser.get(0).getPassword());
	            		isValidUser = true;
	            		System.out.println("got the user");
	            	}
	        } catch(Exception e) {
	            isValidUser = false;
	            log.error("An error occurred while fetching the user details from the database", e);    
	            System.out.println("Error in CAtch :"+e);
	        }
	        finally{
	        	session.close();
	        }
	        return isValidUser;
	    }
}
