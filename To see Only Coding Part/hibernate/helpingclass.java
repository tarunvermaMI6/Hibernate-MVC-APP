package hibernate;

import org.hibernate.Session; //to create session
import org.hibernate.SessionFactory;//interface hv db details pulled from cfg.xml
import org.hibernate.Transaction;//to execute acid properties
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;//hold manage provide access to service
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;//configure cfg
import org.hibernate.service.ServiceRegistry;

public class helpingclass {

    private static  SessionFactory sessionFactory;

    static {
        try {
            ServiceRegistry reg=new StandardServiceRegistryBuilder().configure("Hibernate/hibernate.cfg.xml").build();
            Metadata meta=new MetadataSources(reg).getMetadataBuilder().build();
    
           SessionFactory sf=meta.getSessionFactoryBuilder().build();
        
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}