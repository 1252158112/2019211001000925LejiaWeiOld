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
    public Connection dbConn=null;
    @Override
    public void init() throws ServletException {
        super.init();
//        try {
//            Class.forName(getServletConfig().getServletContext().getInitParameter("driver"));
//            dbConn= DriverManager.getConnection(getServletConfig().getServletContext().getInitParameter("url"),getServletConfig().getServletContext().getInitParameter("Username"),getServletConfig().getServletContext().getInitParameter("Password"));
//            System.out.println(dbConn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        dbConn=(Connection)getServletContext().getAttribute("con");
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
//                writer.println("Login success\nwelcome,"+username);
                req.setAttribute("id",resultDb.getInt("id"));
                req.setAttribute("username",resultDb.getString("username"));
                req.setAttribute("password",resultDb.getString("password"));
                req.setAttribute("email",resultDb.getString("mail"));
                req.setAttribute("gender",resultDb.getString("sex"));
                req.setAttribute("birthDate",resultDb.getString("birth"));
                req.getRequestDispatcher("userInfo.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if(!find) {
            //            writer.println("Wrong username or password");
            req.setAttribute("massage","username or Password Error!!!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
