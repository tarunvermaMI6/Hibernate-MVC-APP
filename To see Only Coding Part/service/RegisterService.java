package service;
import org.hibernate.Query;// for hql
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.helpingclass;//to access helpingclass func
import model.User;// to access java bean class
public class RegisterService { 
	
public boolean register(User user){//passing obj of user class called from registercontroller
	 Session session = helpingclass.openSession();//from helpingclass
	 if(isUserExists(user)){ 
		 return false;// calling function	
	 }
	 Transaction tx = null;	
	 try {
		 tx = session.getTransaction();
		 tx.begin();
		 session.saveOrUpdate(user);		
		 tx.commit();
	 } catch (Exception e) {
		 if (tx != null) {
			 tx.rollback();
		 }
		 e.printStackTrace();
	 } finally {
		 session.close();
	 }	
	 return true;
}

public boolean isUserExists(User user){//checking existeance of user 
	 Session session = helpingclass.openSession();
	 boolean result = false;
	 Transaction tx = null;
	 try{
		 tx = session.getTransaction();//fetching data
		 tx.begin();
		 Query query = session.createQuery("Select from User where userId='"+user.getUserId()+"'");
		                             //getuserid getter and setter method in user.java
		 User u = (User)query.uniqueResult();//hql method uniqueResult
		 tx.commit(); //to exactly fire up the query
		 if(u!=null) result = true;//user doesnt exist
	 }catch(Exception ex){
		 if(tx!=null){
			 tx.rollback(); 
		 }
	 }finally{
		 session.close();
	 }
	 return result;//if every thing fine it goes back to registercontroller
}
}