package service;

import dao.MobileDao;
import dao.MobileRealizeDao;
import entity.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javafx.scene.input.KeyCode.M;

@WebServlet("/Search")
public class Search_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String mname = req.getParameter("mname");
        MobileDao mobileDao = new MobileRealizeDao();
        resp.getWriter().print(JsonUtils.toJson(mobileDao.getMobileByName(mname)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
