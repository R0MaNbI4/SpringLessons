package ru.geekbrains.lesson2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;
import ru.geekbrains.lesson2.consoleinput.Command;
import ru.geekbrains.lesson2.consoleinput.ConsoleInputHandler;

import javax.naming.Context;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        Cart cart = context.getBean(Cart.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Scanner scanner = new Scanner(System.in);
        ConsoleInputHandler input = new ConsoleInputHandler();
        while(true) {
            if (input.validate(scanner.nextLine())) {
                switch (input.getCommand()) {
                    case ADD:
                        cart.addProduct(input.getId(), input.getCount());
                        System.out.println(String.format("Added %d \"%s\"",
                                input.getCount(),
                                productRepository.getProduct(input.getId()).getTitle()));
                        if (cart.getProductCount(input.getId()) > input.getCount()) {
                            System.out.println("Total: " + cart.getProductCount(input.getId()));
                        }
                        break;
                    case DELETE:
                        cart.deleteProduct(input.getId(), input.getCount());
                        System.out.println(String.format("Deleted %s \"%s\"",
                                (input.getCount() < cart.getProductCount(input.getId()) ? input.getCount() : "all"),
                                productRepository.getProduct(input.getId()).getTitle()));
                        if (input.getCount() < cart.getProductCount(input.getId())) {
                            System.out.println("Total: " + cart.getProductCount(input.getId()));
                        }
                        break;
                    case PRINT: {
                        cart.printProducts();
                        break;
                    }
                    case RESET: {
                        cart = context.getBean(Cart.class);
                        System.out.println("Successful");
                        break;
                    }
                    case HELP: {
                        System.out.println("ADD id [count]\nDELETE id [count]\nPRINT [count]\nRESET");
                        break;
                    }
                }
            } else {
                if (!input.isValidCommand()) {
                    System.out.println("Invalid command. Use HELP to see available commands");
                } else if (!input.isValidId()) {
                    System.out.println("Invalid ID");
                } else if (!input.isValidCount()) {
                    System.out.println("Invalid count");
                }
            }
        }
    }
}
