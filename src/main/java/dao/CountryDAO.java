package dao;

import entitites.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends GenericDAO<Country>{
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class,sessionFactory);
    }
}
