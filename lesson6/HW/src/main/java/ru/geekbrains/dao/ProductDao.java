package ru.geekbrains.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.geekbrains.dto.ProductCustomer;
import ru.geekbrains.model.Product;

import java.util.List;

@Component
public class ProductDao {
    SessionFactory sessionFactory;

    ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findProductById(Long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<ProductCustomer> getProductCustomers(Long id) {
        List<ProductCustomer> productCustomers;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            productCustomers = session.createQuery(
                    "SELECT new ru.geekbrains.dto.ProductCustomer(" +
                                     "c," +
                                     "SUM(od.count)) " +
                                "FROM OrderDetails od " +
                          "INNER JOIN Customer c " +
                          "ON c.id IN (SELECT o.customer " +
                                        "FROM Order o " +
                                       "WHERE o.id = od.order) " +
                               "WHERE od.product.id = :id " +
                            "GROUP BY c")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
        }
        return productCustomers;
    }
}
