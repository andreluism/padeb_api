/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import Model.Municipio;
import Model.Regiao;
import Model.UF;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author José Alexandre
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration acR = new AnnotationConfiguration().configure("hbnR.cfg.xml");
                acR.addAnnotatedClass(Regiao.class);
                sessionFactory = acR.configure("hbnR.cfg.xml").buildSessionFactory();
                
                AnnotationConfiguration acUF = new AnnotationConfiguration().configure("hbnUF.cfg.xml");
                acUF.addAnnotatedClass(UF.class);
                sessionFactory = acUF.configure("hbnUF.cfg.xml").buildSessionFactory();
                
                AnnotationConfiguration acMun = new AnnotationConfiguration().configure("hbnMun.cfg.xml");
                acMun.addAnnotatedClass(Municipio.class);
                sessionFactory = acMun.configure("hbnMun.cfg.xml").buildSessionFactory();
                
                
//                Ativar os schemas
//                SchemaExport seR = new SchemaExport(acR);
//                seR.create(true, true);
//                SchemaExport seUF = new SchemaExport(acUF);
//                seUF.create(true, true);
//                SchemaExport seMun = new SchemaExport(acMun);
//                seMun.create(true, true);
                

            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

            return sessionFactory;

        } else {
            return sessionFactory;
        }
        
    }

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
    }

}
