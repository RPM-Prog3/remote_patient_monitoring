import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns={""},loadOnStartup = 1)
public class Server_Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do get - sends to app");
        resp.getWriter().write("We provide live simulation of patient's vital signs for monitoring!");
        String serv_path = req.getServletPath();
        System.out.println(serv_path);
        System.out.println(req);
        System.out.println(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do post - app sends to server");
        System.out.println(req);
        System.out.println(resp);
    }
}