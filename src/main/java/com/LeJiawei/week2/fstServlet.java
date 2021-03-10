package com.LeJiawei.week2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;


public class fstServlet extends HttpServlet {

    public fstServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            response.getWriter().write("Name:LeJiawei\n");
            response.getWriter().write("ID:2019211001000925\n");
            response.getWriter().write("Date and Time:"+new Date().toString());
        }catch(Exception e) {
            response.getWriter().write(e+"");
        }
    }
}
