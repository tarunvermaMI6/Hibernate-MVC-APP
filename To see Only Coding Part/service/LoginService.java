package service;

import java.util.ArrayList;//collection, to store fetched data
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.helpingclass;
import model.User;

public class LoginService {

    public boolean authenticateUser(String userId, String password) {
        User user = getUserByUserId(userId);//calling function to get userid to authenticateUser          
        if(user!=null && user.getUserId().equals(userId) && user.getPassword().equals(password)){
                           //getter and setter method in java beans
		   return true;
        }else{ 
            return false;
        }
    }

    public User getUserByUserId(String userId) {
        Session session = helpingclass.openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("Select from User where userId='"+userId+"'");
            user = (User)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    
    public List<User> getListOfUsers(){ //fetching data and storing in list
        List<User> list = new ArrayList<User>();
        Session session = helpingclass.openSession();
        Transaction tx = null;        
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from User").list();                        
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
