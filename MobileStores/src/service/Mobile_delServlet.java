package service;

import dao.MobileDao;
import dao.MobileRealizeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class Mobile_delServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int mid = Integer.parseInt(req.getParameter("mid"));
        MobileDao mobileDao = new MobileRealizeDao();
        if(mobileDao.del(mid)) {
            resp.getWriter().print("{\"msg\":\"删除成功！！！\"}");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
