package Servlet;

import Dao.ProblemDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import enity.Problem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: ProblemServlet
 * @Author Xu
 * @Date: 27/10/2022 下午 9:31
 * @Version 1.0
 */
@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf8");
        ProblemDAO problemDAO = new ProblemDAO();
        String id = req.getParameter("id");
        if(id == null || "".equals(id)){
            List<Problem> list = problemDAO.selectAll();
            String respString = objectMapper.writeValueAsString(list);
            resp.getWriter().write(respString);
        }else{
            Problem problem = problemDAO.selectOne(Integer.parseInt(id));
            String respString = objectMapper.writeValueAsString(problem);
            resp.getWriter().write(respString);
        }
    }
}