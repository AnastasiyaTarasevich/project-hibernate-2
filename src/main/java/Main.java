import dao.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;
import entitites.*;
import service.CustomerService;
import service.FilmService;
import service.InventoryService;
import service.RentalService;

public class Main
{
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    private final  CustomerService customerService;
    private final RentalService rentalService;
    private final InventoryService inventoryService;
    private final SessionFactory sessionFactory;
    private final FilmService filmService;
    public Main()
    {
        Properties properties = getProperties();
        sessionFactory=new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

            actorDAO=new ActorDAO(sessionFactory);
          addressDAO=new AddressDAO(sessionFactory);
          categoryDAO=new CategoryDAO(sessionFactory);
          cityDAO=new CityDAO(sessionFactory);
          countryDAO=new CountryDAO(sessionFactory);
          customerDAO=new CustomerDAO(sessionFactory);
          filmDAO=new FilmDAO(sessionFactory);
          filmTextDAO=new FilmTextDAO(sessionFactory);
          inventoryDAO=new InventoryDAO(sessionFactory);
          paymentDAO=new PaymentDAO(sessionFactory);
          rentalDAO=new RentalDAO(sessionFactory);
          staffDAO=new StaffDAO(sessionFactory);
          storeDAO=new StoreDAO(sessionFactory);
          languageDAO=new LanguageDAO(sessionFactory);
          customerService=new CustomerService(sessionFactory, storeDAO, cityDAO, addressDAO, customerDAO);
        inventoryService=new InventoryService(sessionFactory,filmDAO,storeDAO,inventoryDAO,rentalDAO,paymentDAO);
          rentalService=new RentalService(sessionFactory,rentalDAO);
          filmService=new FilmService(filmDAO,actorDAO,languageDAO,sessionFactory,categoryDAO);

    }

    private static Properties getProperties() {
        Properties properties=new Properties();
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER,"com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL,"jdbc:p6spy:mysql://localhost:3306/hibernate-2");
        properties.put(Environment.USER,"root");
        properties.put(Environment.PASS,"666246+");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
        properties.put(Environment.HBM2DDL_AUTO,"validate");
        return properties;
    }

    public static void main(String[] args) {
        Main main=new Main();

        Customer newCustomer = main.customerService.createCustomer();
        main.rentalService.customerReturnInventoryToStore();
        main.inventoryService.customerRentInventory(newCustomer);
        main.filmService.addNewFilm();

    }
}
