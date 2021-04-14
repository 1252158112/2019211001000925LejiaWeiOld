package com.LeJiawei.week3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = {"/register"},loadOnStartup = 1)
public class RegisterServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String mail=request.getParameter("email");
        String sex=request.getParameter("sex");
        String birth=request.getParameter("birth");
        PrintWriter writer=response.getWriter();
//        writer.println("<br>");
//        writer.println("<br>username:"+username);
//        writer.println("<br>password:"+password);
//        writer.println("<br>email:"+mail);
//        writer.println("<br>gender:"+sex);
//        writer.println("<br>birthDate:"+birth);
//        System.out.println("gotted");
        String[][] ret=new String[100][6];
        int cnt=0;
        try {
//            System.out.println("try cn");
            Statement createDbStatement = dbConn.createStatement();
            String dbRequire1="insert into usertable(username,password,mail,sex,birth) values('"+username+"','"+password+"','"+mail+"','"+sex+"','"+birth+"')";
//            System.out.println(dbRequire1);
            createDbStatement.executeUpdate(dbRequire1);
            String dbRequire="select * from usertable";
            ResultSet resultDb=createDbStatement.executeQuery(dbRequire);
            while(resultDb.next()) {
                ret[cnt][0]=resultDb.getObject(1).toString().trim();
                ret[cnt][1]=resultDb.getObject(2).toString().trim();
                ret[cnt][2]=resultDb.getObject(3).toString().trim();
                ret[cnt][3]=resultDb.getObject(4).toString().trim();
                ret[cnt][4]=resultDb.getObject(5).toString().trim();
                ret[cnt++][5]=resultDb.getObject(6).toString().trim();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("login.jsp");
//        writer.println("<table border=\"1\">");
//        writer.println("<tr><td>ID</td><td>UserName</td><td>Password</td><td>Email</td><td>Gender</td><td>Birthdate</td></tr>");
//        for(int i=0;i<cnt;i++) {
//            for(int j=0;j<6;j++) {
//                writer.println("<td>"+ret[i][j]+"</td>");
//            }
//            writer.println("</tr>");
//        }
//        writer.println("</table>");
    }
}

