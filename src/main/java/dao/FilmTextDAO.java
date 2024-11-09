package dao;

import entitites.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends GenericDAO<FilmText>{

    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class,sessionFactory);
    }
}
