package ru.geekbrains.lesson2.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.geekbrains.lesson2.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

@Component
public class AddProductAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            var annotation = field.getAnnotation(AddProduct.class);
            if (annotation != null) {
                int count = annotation.count();
                field.setAccessible(true);
                ArrayList<Product> products = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    products.add(new Product(
                            i,
                            String.format("Product%d", i),
                            new Random().nextFloat() * 1000
                    ));
                }
                ReflectionUtils.setField(field, bean, products);
            }
        }
        return null;
    }
}
