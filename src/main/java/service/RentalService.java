package service;

import dao.RentalDAO;
import entitites.Customer;
import entitites.Rental;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

@AllArgsConstructor
public class RentalService {
    private final SessionFactory sessionFactory;
    private final RentalDAO rentalDAO;

    public void customerReturnInventoryToStore()
    {
        try(Session session=sessionFactory.getCurrentSession())
        {
            session.beginTransaction();
           Rental rental= rentalDAO.getAnyUnreturnedRental();
           rental.setReturnDate(LocalDateTime.now());
           rentalDAO.save(rental);
            session.getTransaction().commit();
        }
    }
}
