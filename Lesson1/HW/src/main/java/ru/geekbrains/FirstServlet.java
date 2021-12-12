package ru.geekbrains;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet {
    int count = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("count") != null) {
            count = Integer.parseInt(req.getParameter("count"));
        }
        for (int i = 1; i <= count; i++) {
            Product product = new Product(i, (float) (Math.random() * 1000), "title" + i);
            resp.getWriter().println(String.format("<p>Title: %s<br>Price: %.2f",
                    product.getTitle(), product.getCost()));
        }
    }
}
