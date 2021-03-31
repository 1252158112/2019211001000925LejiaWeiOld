package com.LeJiawei.week5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.applet.Applet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    public Connection dbConn;
    @Override
    public void init() throws ServletException {
        try {
            Class.forName(getServletConfig().getServletContext().getInitParameter("driver"));
            dbConn= DriverManager.getConnection(getServletConfig().getServletContext().getInitParameter("url"),getServletConfig().getServletContext().getInitParameter("Username"),getServletConfig().getServletContext().getInitParameter("Password"));
            System.out.println(dbConn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Statement createDbStatement = null;
        PrintWriter writer=resp.getWriter();
        boolean find=false;
        try {
            createDbStatement = dbConn.createStatement();
            String dbRequire="select * from usertable where username='"+username+"' and password='"+password+"'";
            System.out.println(dbRequire);
            ResultSet resultDb=createDbStatement.executeQuery(dbRequire);
            if(resultDb.next()) {
                find=true;
                writer.println("Login success\nwelcome,"+username);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if(!find)
            writer.println("Wrong username or password");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
