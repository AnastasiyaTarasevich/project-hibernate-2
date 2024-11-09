package dao;

import entitites.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends GenericDAO<Film>{
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class,sessionFactory);
    }
}
