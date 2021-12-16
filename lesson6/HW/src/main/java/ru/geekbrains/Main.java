package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.configuration.AppConfig;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.CustomerService;
import ru.geekbrains.service.ProductService;

import javax.naming.Context;
import java.util.HashMap;

public class Main{
    private static ProductService productService;
    private static CustomerService customerService;

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        productService = context.getBean(ProductService.class);
        customerService = context.getBean(CustomerService.class);

        System.out.println("Найти продукт с ID = 1");
        System.out.println(productService.findProductById(1L));

        System.out.println("\nНайти покупателя с ID = 1");
        System.out.println(customerService.findCustomerById(1L));


        System.out.println("\nДобавить заказ (2 молока, 1 хлеб) покупателю с ID = 1");
        HashMap<Product, Integer> products = new HashMap<>();
        products.put(productService.findProductById(1L), 2);
        products.put(productService.findProductById(2L), 1);
        customerService.addOrder(
                customerService.findCustomerById(1L),
                products
        );

        System.out.println("\nПолучить все заказы покупателя с ID = 1");
        customerService.getOrders(1L).forEach(System.out::println);

        System.out.println("\nПолучить статистику по купленным товаром (во всех заказах) покупателя с ID = 1");
        customerService.getProductStatistic(1L).forEach(System.out::println);

        System.out.println("\nПолучить список покупателей, которые купили товар с ID = 1 и в каком количестве");
        productService.getProductCustomers(1L).forEach(System.out::println);
    }

    /*
        Найти продукт с ID = 1
        Hibernate: select product0_.id as id1_3_0_, product0_.price as price2_3_0_, product0_.title as title3_3_0_ from product product0_ where product0_.id=?
        Product{id=1, title='Молоко', price=60}

        Найти покупателя с ID = 1
        Hibernate: select customer0_.id as id1_0_0_, customer0_.name as name2_0_0_ from customer customer0_ where customer0_.id=?
        Customer{id=1, name='Андрей}

        Добавить заказ (2 молока, 1 хлеб) покупателю с ID = 1
        Hibernate: select product0_.id as id1_3_0_, product0_.price as price2_3_0_, product0_.title as title3_3_0_ from product product0_ where product0_.id=?
        Hibernate: select product0_.id as id1_3_0_, product0_.price as price2_3_0_, product0_.title as title3_3_0_ from product product0_ where product0_.id=?
        Hibernate: select customer0_.id as id1_0_0_, customer0_.name as name2_0_0_ from customer customer0_ where customer0_.id=?
        Hibernate: insert into customer_order (customer_id) values (?)
        Hibernate: select orderdetai_.order_id, orderdetai_.product_id, orderdetai_.product_count as product_3_2_, orderdetai_.price as price4_2_ from order_details orderdetai_ where orderdetai_.order_id=? and orderdetai_.product_id=?
        Hibernate: select orderdetai_.order_id, orderdetai_.product_id, orderdetai_.product_count as product_3_2_, orderdetai_.price as price4_2_ from order_details orderdetai_ where orderdetai_.order_id=? and orderdetai_.product_id=?
        Hibernate: insert into order_details (product_count, price, order_id, product_id) values (?, ?, ?, ?)
        Hibernate: insert into order_details (product_count, price, order_id, product_id) values (?, ?, ?, ?)
        Hibernate: update product set price=?, title=? where id=?
        Hibernate: update product set price=?, title=? where id=?

        Получить все заказы покупателя с ID = 1
        Hibernate: select orders1_.id as id1_1_, orders1_.customer_id as customer2_1_ from customer customer0_ inner join customer_order orders1_ on customer0_.id=orders1_.customer_id where customer0_.id=?
        Hibernate: select customer0_.id as id1_0_0_, customer0_.name as name2_0_0_ from customer customer0_ where customer0_.id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Hibernate: select orderdetai0_.order_id as order_id1_2_0_, orderdetai0_.product_id as product_2_2_0_, orderdetai0_.order_id as order_id1_2_1_, orderdetai0_.product_id as product_2_2_1_, orderdetai0_.product_count as product_3_2_1_, orderdetai0_.price as price4_2_1_, product1_.id as id1_3_2_, product1_.price as price2_3_2_, product1_.title as title3_3_2_ from order_details orderdetai0_ inner join product product1_ on orderdetai0_.product_id=product1_.id where orderdetai0_.order_id=?
        Order{id=1, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}
        Order{id=2, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}
        Order{id=3, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}
        Order{id=4, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}
        Order{id=5, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}
        Order{id=6, customer=Customer{id=1, name='Андрей}, orderDetails=[OrderDetails{product=Product{id=1, title='Молоко', price=60}, count=2, price=60}, OrderDetails{product=Product{id=2, title='Хлеб', price=50}, count=1, price=50}]}

        Получить статистику по купленным товаром (во всех заказах) покупателя с ID = 1
        Hibernate: select product1_.id as col_0_0_, sum(orderdetai0_.product_count) as col_1_0_ from order_details orderdetai0_ inner join product product1_ on (product1_.id=orderdetai0_.product_id) where orderdetai0_.order_id in (select order2_.id from customer_order order2_ where order2_.customer_id=?) group by product1_.id
        Hibernate: select product0_.id as id1_3_0_, product0_.price as price2_3_0_, product0_.title as title3_3_0_ from product product0_ where product0_.id=?
        Hibernate: select product0_.id as id1_3_0_, product0_.price as price2_3_0_, product0_.title as title3_3_0_ from product product0_ where product0_.id=?
        ProductStatistics{name='Молоко', count=12}
        ProductStatistics{name='Хлеб', count=6}

        Получить список покупателей, которые купили товар с ID = 1 и в каком количестве
        Hibernate: select customer1_.id as col_0_0_, sum(orderdetai0_.product_count) as col_1_0_ from order_details orderdetai0_ inner join customer customer1_ on (customer1_.id in (select order2_.customer_id from customer_order order2_ inner join customer customer3_ on order2_.customer_id=customer3_.id where order2_.id=orderdetai0_.order_id)) where orderdetai0_.product_id=? group by customer1_.id
        Hibernate: select customer0_.id as id1_0_0_, customer0_.name as name2_0_0_ from customer customer0_ where customer0_.id=?
        ProductCustomer{customer=Андрей, count=12}
     */
}
