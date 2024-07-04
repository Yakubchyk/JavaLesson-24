package lesson24.servlet;

import com.google.gson.Gson;
import lesson24.servlet.model.Operation;
import lesson24.servlet.service.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class OperationServlet extends HttpServlet {

    private final OperationService operationService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double num1 = Double.parseDouble(req.getParameter("num1"));
        double num2 = Double.parseDouble(req.getParameter("num2"));
        String type = req.getParameter("type");
        Operation operation = new Operation(num1, num2, type);
        Operation result = operationService.getResult(operation);

        String formatted = "Result %s".formatted(result.getResult());
        //resp.getWriter().println(formatted);

        resp.setContentType("application/json");
        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson(result));

    }
}
