package service;

import dao.AddressDAO;
import dao.CityDAO;
import dao.CustomerDAO;
import dao.StoreDAO;
import entitites.Address;
import entitites.City;
import entitites.Customer;
import entitites.Store;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@AllArgsConstructor

public class CustomerService {
    private final SessionFactory sessionFactory;
    private final StoreDAO storeDAO;
    private final CityDAO cityDAO;
    private final AddressDAO addressDAO;
    private final CustomerDAO customerDAO;
    public Customer createCustomer()
    {
        try(Session session=sessionFactory.getCurrentSession())
        {
            session.beginTransaction();
            Store store= storeDAO.getItems(0,1).get(0);
            City city=cityDAO.getByName("Kragujevac");
            Address address=new Address();
            address.setAddress("Indep street,23");
            address.setPhone("999-23-45");
            address.setCity(city);
            address.setDistrict("kjfdo");
            addressDAO.save(address);
            Customer customer=new Customer();
            customer.setIsActive(true);
            customer.setEmail("nt@gmail.com");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName("vasya");
            customer.setLastName("pupkin");
            customerDAO.save(customer);
            session.getTransaction().commit();

            return customer;
        }

    }
}
