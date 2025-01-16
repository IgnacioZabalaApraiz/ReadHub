/**
 * 
 */
/**
 * 
 */
module ReadHub {
    requires java.sql;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens modeloHibernate to org.hibernate.orm.core;
    exports modeloHibernate;
}
