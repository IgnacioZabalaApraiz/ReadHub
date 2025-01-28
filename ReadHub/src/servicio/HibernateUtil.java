package servicio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modeloHibernate.Usuario;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    // Configura Hibernate
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                                 .addAnnotatedClass(Usuario.class)  // Agregar tu clase de modelo
                                                 .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Obtiene la sesión actual
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // Cierra la sesión f
    public static void close() {
        sessionFactory.close();
    }
}
