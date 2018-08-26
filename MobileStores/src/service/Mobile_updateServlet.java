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
@WebServlet("/updates")
public class Mobile_updateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        MobileDao mobileDao = new MobileRealizeDao();
        int mid = Integer.parseInt(req.getParameter("mid"));
        resp.getWriter().print(JsonUtils.toJson(mobileDao.getMobileById(mid)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        MobileDao mobileDao = new MobileRealizeDao();
        int mid = Integer.parseInt(req.getParameter("mid"));
        String mname = req.getParameter("mname");
        float mprice = Float.parseFloat(req.getParameter("mprice"));
        String ram = req.getParameter("ram");
        String brief = req.getParameter("brief");
        if (mobileDao.update(mid,mname,mprice,ram,brief)){
            resp.getWriter().print("{\"msg\":\"更新成功\"}");
        }
    }
}
