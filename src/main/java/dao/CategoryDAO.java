package dao;

import entitites.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends GenericDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class,sessionFactory);
    }
}
