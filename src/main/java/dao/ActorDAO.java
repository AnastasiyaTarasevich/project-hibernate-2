package dao;

import entitites.Actor;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;


public class ActorDAO extends GenericDAO<Actor>{
    public ActorDAO( SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
