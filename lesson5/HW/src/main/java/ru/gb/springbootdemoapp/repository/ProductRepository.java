package ru.gb.springbootdemoapp.repository;



import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.dao.ProductDao;
import ru.gb.springbootdemoapp.dto.Filter;
import ru.gb.springbootdemoapp.dto.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepository {
    ProductDao productDao;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public void deleteProductById(int id) {
        productDao.deleteById(id);
    }

    public Product getProductById(int id) {
        return productDao.findById(id);
    }

    public List<Product> getFiltered(Filter filter) {
        return productDao.findByParameters(getMappedParameters(filter));
    }

    public void save(Product product) {
        productDao.saveOrUpdate(product);
    }

    private HashMap<String, Object> getMappedParameters(Filter filter) {
        Method[] methods = filter.getClass().getDeclaredMethods();
        HashMap<String, Object> parameters = new HashMap<>();
        for (Method m : methods)
        {
            if (m.getName().startsWith("get"))
            {
                Object value = 0;
                try {
                    value = m.invoke(filter);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                parameters.put(StringUtils.uncapitalize(m.getName().substring(3)), value);
            }
        }
        return parameters;
    }
}
