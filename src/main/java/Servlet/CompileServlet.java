package Servlet;

import Compile.Answer;
import Compile.Question;
import Compile.Task;
import Dao.ProblemDAO;
import Exception.CodeInValidException;
import Exception.ProblemNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enity.Problem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @title: CompileServlet
 * @Author Xu
 * @Date: 27/10/2022 下午 9:37
 * @Version 1.0
 */
@WebServlet("/compile")
public class CompileServlet extends HttpServlet {
    static class CompileRequest {
        public int id;
        public String code;
    }

    static class CompileResponse {
        // 约定 error 为 0 表示编译运行 ok, error 为 1 表示编译出错, error 为 2 表示运行异常(用户提交的代码异常了), 3 表示其他错误
        public int error;
        public String reason;
        public String stdout;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompileRequest compileRequest = null;
        CompileResponse compileResponse = new CompileResponse();
        try {
            resp.setStatus(200);
            resp.setContentType("application/json;charset=utf8");
            String body = readBody(req);
            compileRequest = objectMapper.readValue(body, CompileRequest.class);
            ProblemDAO problemDAO = new ProblemDAO();
            Problem problem = problemDAO.selectOne(compileRequest.id);
            if (problem == null) {
                throw new ProblemNotFoundException();
            }
            String testCode = problem.getTestCode();
            String requestCode = compileRequest.code;
            String finalCode = mergeCode(requestCode, testCode);
            if (finalCode == null) {
                throw new CodeInValidException();
            }
            Task task = new Task();
            Question question = new Question();
            question.setCode(finalCode);
            Answer answer = task.compileAndRun(question);
            compileResponse.error = answer.getError();
            compileResponse.reason = answer.getReason();
            compileResponse.stdout = answer.getStdout();
        } catch (ProblemNotFoundException e) {
            // 处理题目没有找到的异常
            compileResponse.error = 3;
            compileResponse.reason = "没有找到指定的题目! id=" + compileRequest.id;
        } catch (CodeInValidException e) {
            compileResponse.error = 3;
            compileResponse.reason = "提交的代码不符合要求!";
        } finally {
            String respString = objectMapper.writeValueAsString(compileResponse);
            resp.getWriter().write(respString);
        }
    }

    private static String mergeCode(String requestCode, String testCode) {
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1) {
            return null;
        }
        String subString = requestCode.substring(0, pos);
        return subString + "}";
    }

    private static String readBody(HttpServletRequest req) {
        int contentLength = req.getContentLength();
        byte[] bytes = new byte[contentLength];
        try (InputStream inputStream = req.getInputStream()) {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}