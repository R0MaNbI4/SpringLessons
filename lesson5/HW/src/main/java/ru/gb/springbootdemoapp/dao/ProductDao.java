package ru.gb.springbootdemoapp.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.dto.Product;

import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDao {
    SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(long id) {
        Product product;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> products;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            products = session
                    .createQuery("SELECT p FROM Product p")
                    .getResultList();
            session.getTransaction().commit();
        }
        return products;
    }

    public void deleteById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM Product WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> findByParameters(HashMap<String, Object> parameters) {
        List<Product> products;
        ArrayList<Predicate> predicates = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cr = cb.createQuery(Product.class);
            Root<Product> root = cr.from(Product.class);
            for (String parameter : parameters.keySet()) {
                String name = StringUtils.uncapitalize(parameter.substring(3));
                if (parameter.startsWith("min")) {
                    predicates.add(cb.ge(root.get(name), (Number) parameters.get(parameter)));
                } else if (parameter.startsWith("max")) {
                    predicates.add(cb.le(root.get(name), (Number) parameters.get(parameter)));
                }
            }
            products = session.createQuery(
                    cr.select(root).where(predicates.toArray(new Predicate[0])))
                    .getResultList();
            session.getTransaction().commit();
        }
        return products;
    }
}
