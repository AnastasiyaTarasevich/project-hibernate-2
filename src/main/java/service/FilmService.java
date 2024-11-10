package service;

import dao.ActorDAO;
import dao.CategoryDAO;
import dao.FilmDAO;
import dao.LanguageDAO;
import entitites.*;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FilmService
{
    private final FilmDAO filmDAO;
    private final ActorDAO actorDAO;
    private final LanguageDAO languageDAO;
    private final SessionFactory sessionFactory;
    private final CategoryDAO categoryDAO;

    public void addNewFilm()
    {
        try(Session session= sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            Language language = languageDAO.getItems(0, 7).stream().unordered().findAny().get();
            Set<Actor> actors =  actorDAO.getItems(0, 2).stream().collect(Collectors.toSet());

            Set<Category> categories=  categoryDAO.getItems(0, 1).stream().collect(Collectors.toSet());
            Film film=new Film();
            film.setActors(actors);
            film.setLanguage(language);
            film.setCategories(categories);
            film.setTitle("Big film");
            film.setReleaseYear(Year.of(LocalDateTime.now().getYear()));
            film.setRentalDuration((byte) 4);
            film.setRentalRate(BigDecimal.TEN);
            film.setRating(Rating.R);
            film.setReplacementCost(BigDecimal.valueOf(66));
            filmDAO.save(film);
            session.getTransaction().commit();
        }
    }
}
