package service;

import dao.*;
import entitites.*;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
public class InventoryService {
    private final SessionFactory sessionFactory;
    private final FilmDAO filmDAO;
    private final StoreDAO storeDAO;
    private final InventoryDAO inventoryDAO;
    private final RentalDAO rentalDAO;
    private final PaymentDAO paymentDAO;


    public void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Film film=filmDAO.getFirstAvaliableFilmForRent();
            Inventory inventory=new Inventory();
            Store store=storeDAO.getItems(0,1).get(0);
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff=store.getManagerStaff();

            Rental rental=new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment=new Payment();
            payment.setRental(rental);
            payment.setStaff(staff);
            payment.setAmount(BigDecimal.valueOf(66));
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            paymentDAO.save(payment);
            session.getTransaction().commit();
        }
    }

}
