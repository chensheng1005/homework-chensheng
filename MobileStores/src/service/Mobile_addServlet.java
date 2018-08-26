package service;

import dao.MobileDao;
import dao.MobileRealizeDao;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class Mobile_addServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String mname = req.getParameter("mname");
        float mpeice = Float.parseFloat(req.getParameter("mprice"));
        String ram = req.getParameter("ram");
        String brief = req.getParameter("brief");

        MobileDao mobileDao = new MobileRealizeDao();
        if(mobileDao.add(mname,mpeice,ram,brief)){
            resp.getWriter().print("{\"msg\":\"添加成功！！！\"}");
        }
    }
}
