package com.LeJiawei.controller;

import com.LeJiawei.dao.ProductDao;
import com.LeJiawei.model.Category;
import com.LeJiawei.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShopServlet", value="/shop")
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category=new Category();
        try {
            List<Category> categoryList=category.findAllCategory((Connection)getServletContext().getAttribute("con"));
            request.setAttribute("categoryList",categoryList);
            ProductDao productDao =new ProductDao();
            List<Product> productList=null;
            if(request.getParameter("categoryId")==null) {
                productList=productDao.findAll((Connection)getServletContext().getAttribute("con"));
            }
            else {
                int categoryId= Integer.parseInt(request.getParameter("categoryId"));
                productList=productDao.findByCategoryId(categoryId,(Connection)getServletContext().getAttribute("con"));

            }
            request.setAttribute("productList",productList);
            String path="/WEB-INF/views/shop.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
