package ru.geekbrains.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.geekbrains.dto.CustomerOrderStatistics;
import ru.geekbrains.model.Customer;
import ru.geekbrains.model.Order;
import ru.geekbrains.model.OrderDetails;
import ru.geekbrains.model.OrderDetailsKey;
import ru.geekbrains.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CustomerDao {
    SessionFactory sessionFactory;

    CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer findCustomerById(Long id) {
        Customer customer;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            customer = session.get(Customer.class, id);
            session.getTransaction().commit();
        }
        return customer;
    }

    public List<Order> getOrders(Long id) {
        List<Order> orders;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            orders = session.createQuery("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.customer.id = :id", Order.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
        }
        return orders;
    }

    public void addOrder(Customer customer, HashMap<Product, Integer> products) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();

            List<OrderDetails> orderDetails = new ArrayList<>();
            Order order = new Order();

            for (Product product : products.keySet()) {
                OrderDetails orderDetails1 = new OrderDetails(
                        order,
                        product,
                        products.get(product),
                        product.getPrice()
                );
                orderDetails1.setEntryId(new OrderDetailsKey(
                        order.getId(),
                        product.getId()
                ));
                orderDetails.add(orderDetails1);
            }

            order.setCustomer(customer);
            order.setOrderDetails(orderDetails);

            session.save(order);

            session.getTransaction().commit();
        }
    }

    // Получить количество товаров, приобретенных покупателем за всё время (из всех заказов)
    public List<CustomerOrderStatistics> getProductStatistics(Long id) {
        List<CustomerOrderStatistics> productStatistics;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
                        productStatistics = session.createQuery(
                                            """
                                            SELECT new ru.geekbrains.dto.CustomerOrderStatistics(p, SUM(od.count))
                                            FROM OrderDetails od
                                            INNER JOIN Product p
                                            ON p = od.product
                                            WHERE od.order IN (SELECT o.id
                                                               FROM Order o
                                                               WHERE o.customer.id = :id)
                                            GROUP BY p""", CustomerOrderStatistics.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
        }
        return productStatistics;
    }
}
